package gist.pilldispenser.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.Tuple;
import gist.pilldispenser.common.utils.RedisUtils;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.domain.entity.QDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepositoryCustomImpl;
import gist.pilldispenser.notification.domain.NotificationTemplate;
import gist.pilldispenser.users.domain.entity.QUsers;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationHelper {

    private final ObjectMapper objectMapper;
    private final RedisUtils redisUtils;
    private final RestTemplate restTemplate;
    private final OAuthService oAuthService;
    private final UserDrugInfoRepositoryCustomImpl userDrugInfoRepositoryCustomImpl;

    @Value("${kakao.msg-uri}")
    private String MESSAGE_URI;

    // 지정 시간에 카카오톡 메시지 API를 호출
    public String sendNotification(Routine routine) throws IOException {
        Tuple tuple = userDrugInfoRepositoryCustomImpl.findUserDrugInfoAndDrugInfoAndUserByRoutineId(routine.getId());

        assert tuple != null;
        DrugInfo drugInfo = tuple.get(QDrugInfo.drugInfo);
        Users users = tuple.get(QUsers.users);

        String accessToken = getKakaoToken(users.getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("template_object", notificationTemplate(routine, drugInfo));

        HttpEntity<MultiValueMap<String,String>> kakaoMessageRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                MESSAGE_URI, HttpMethod.POST, kakaoMessageRequest, String.class);

        return response.getBody();
    }

    // 요청에 따라 카카오톡 메시지 API를 호출
    public String sendPillNotification(String email, boolean isPillTaken) throws IOException {
        String accessToken = getKakaoToken(email);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("template_object", pillTakenNotificationTemplate(isPillTaken));

        HttpEntity<MultiValueMap<String,String>> kakaoMessageRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                MESSAGE_URI, HttpMethod.POST, kakaoMessageRequest, String.class);

        return response.getBody();
    }

    // 약 복용 알림 메시지를 작성
    private String notificationTemplate(Routine routine, DrugInfo drugInfo) throws JsonProcessingException {
        NotificationTemplate.LinkList linkList = NotificationTemplate.LinkList.builder()
                .webUrl("http://api.pillsogood.shop")
                .mobileWebUrl("http://api.pillsogood.shop")
                .build();

        String message = drugInfo.getName()
                +" "+routine.getDailyDosage()+"개 복용 시간입니다.";

        NotificationTemplate customizedMessage = NotificationTemplate.builder()
                .objectType("text")
                .text(message)
                .link(linkList)
                .buttonTitle("사이트 확인하기")
                .build();

        return objectMapper.writeValueAsString(customizedMessage);
    }

    // 약 복용 확인 알림 메시지 템플릿을 작성
    private String pillTakenNotificationTemplate(boolean isPillTaken) throws JsonProcessingException {
        NotificationTemplate.LinkList linkList = NotificationTemplate.LinkList.builder()
                .webUrl("http://api.pillsogood.shop")
                .mobileWebUrl("http://api.pillsogood.shop")
                .build();

        NotificationTemplate customizedMessage = NotificationTemplate.builder()
                .objectType("text")
                .link(linkList)
                .buttonTitle("복용 기록 확인하기")
                .build();

        if (isPillTaken) {
            customizedMessage = customizedMessage.toBuilder()
                    .text("약 복용이 확인되었습니다.")
                    .build();
        } else {
            customizedMessage = customizedMessage.toBuilder()
                    .text("약이 복용되지 않았습니다. 복용 확인 부탁드립니다.")
                    .build();
        }
        return objectMapper.writeValueAsString(customizedMessage);

    }

    public String getCronExpression(Routine routine) {
        LocalTime time = routine.getTime();
        return  "0 "+time.getMinute()+" "+time.getHour()+" ? * "+routine.getDays().getCronName();
    }

    // 카카오톡 메시지 API 호출에 필요한 액세스 토큰 받아옴
    // 액세스 토큰이 만료된 경우, 리프레시 토큰으로 새로 발급
    // 리프레시 토큰도 없으면 예외 발생, 카카오 로그인을 다시 실행
    private String getKakaoToken(String email) throws IOException {
        String key = "access_token_" + email;
        if (redisUtils.keyExists(key)){
            return redisUtils.findToken(key);
        } else {
            // access token 만료 시
            String refreshKey = "refresh_token_" + email;
            if (redisUtils.keyExists(refreshKey)){
                // refresh token이 남아있으면 재발급
                log.info("reissuing tokens");
                oAuthService.reissueTokens(email);
                return redisUtils.findToken(key);
            } else {
                // refresh token도 없으면 카카오 로그인 다시
                throw new RuntimeException("tokens nonexistent, login again");
            }
        }
    }
}
