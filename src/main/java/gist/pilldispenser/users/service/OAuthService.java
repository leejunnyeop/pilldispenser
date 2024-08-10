package gist.pilldispenser.users.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gist.pilldispenser.common.utils.RedisUtils;
import gist.pilldispenser.users.domain.model.KakaoUserInfoResponse;
import gist.pilldispenser.users.domain.model.OAuthTokenResponse;
import gist.pilldispenser.users.domain.model.UserInfoResponse;
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
import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final RedisUtils redisUtils;

    @Value("${kakao.api-key}")
    private String API_KEY;
    @Value("${kakao.secret-key}")
    private String SECRET_KEY;
    @Value("${kakao.token-uri}")
    private String TOKEN_URI;
    @Value("${kakao.info-uri}")
    private String INFO_URI;

    // 카카오 인가코드로 카카오 액세스 토큰 발급
    public OAuthTokenResponse getKakaoToken(String authCode, String redirectUri) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", API_KEY);
        params.add("redirect_uri", redirectUri);
        params.add("code", authCode);
        params.add("client_secret", SECRET_KEY);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> token = restTemplate.exchange(
                TOKEN_URI, HttpMethod.POST, kakaoTokenRequest, String.class);

        OAuthTokenResponse tokenResponse = objectMapper.readValue(token.getBody(), OAuthTokenResponse.class);
        log.info("accessToken: "+tokenResponse.getAccessToken());

        return tokenResponse;
    }

    // 카카오 유저정보 가져오기
    public UserInfoResponse kakaoUserInfo(String accessToken) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        URL url = new URL(INFO_URI);

        HttpEntity<MultiValueMap<String,String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url.toString(), HttpMethod.GET, kakaoUserInfoRequest, String.class);

        KakaoUserInfoResponse userInfo = KakaoUserInfoResponse.fromJson(response.getBody(), objectMapper);
        return KakaoUserInfoResponse.fromKakaoUserInfoResponse(userInfo);
    }

    public void saveAccessTokenToRedis(OAuthTokenResponse tokenResponse, String email) {
        String accessTokenKey = "access_token_"+email;
        redisUtils.saveToken(accessTokenKey, tokenResponse.getAccessToken(),
                Long.valueOf(tokenResponse.getExpiresIn()));
    }

    public void saveRefreshTokenToRedis(OAuthTokenResponse tokenResponse, String email) {
        String refreshTokenKey = "refresh_token_"+email;
        redisUtils.saveToken(refreshTokenKey, tokenResponse.getRefreshToken(),
                Long.valueOf(tokenResponse.getRefreshTokenExpiresIn()));
    }

    // 카카오 토큰 재발급
    public void reissueTokens(String email) throws IOException {
        String refreshTokenKey = "refresh_token_"+email;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", API_KEY);
        params.add("refresh_token", redisUtils.findToken(refreshTokenKey));
        params.add("client_secret", SECRET_KEY);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        ResponseEntity<String> token = restTemplate.exchange(
                TOKEN_URI, HttpMethod.POST, kakaoTokenRequest, String.class);

        OAuthTokenResponse tokenResponse = objectMapper.readValue(token.getBody(), OAuthTokenResponse.class);

        if (tokenResponse.getRefreshToken() != null) {
            saveAccessTokenToRedis(tokenResponse, email);
            saveRefreshTokenToRedis(tokenResponse, email);
        } else {
            saveAccessTokenToRedis(tokenResponse, email);
        }
    }
}
