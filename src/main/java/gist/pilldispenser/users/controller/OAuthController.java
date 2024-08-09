package gist.pilldispenser.users.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.users.domain.model.OAuthTokenResponse;
import gist.pilldispenser.users.domain.model.UserInfoResponse;
import gist.pilldispenser.users.service.OAuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/login/oauth2/code/kakao")
    public ResponseEntity<UserInfoResponse> kakaoLogin(HttpServletRequest request,
                                                       @RequestParam("code") String code) throws IOException {
        boolean isLocal = request.getRequestURL().toString().contains("localhost");
        log.info("isLocal: " + isLocal+", code: "+code);

        try {
            OAuthTokenResponse tokenResponse = oAuthService.getKakaoToken(code, isLocal);
            UserInfoResponse userInfo = oAuthService.kakaoUserInfo(tokenResponse.getAccessToken());

            oAuthService.saveAccessTokenToRedis(tokenResponse, userInfo.getEmail());
            oAuthService.saveRefreshTokenToRedis(tokenResponse, userInfo.getEmail());
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/oauth2/token")
    public void kakaoTokenRenew(@AuthenticationPrincipal UsersDetails usersDetails) throws IOException {
        oAuthService.reissueTokens(usersDetails);
    }

}