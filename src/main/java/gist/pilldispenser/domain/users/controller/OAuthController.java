package gist.pilldispenser.domain.users.controller;

import gist.pilldispenser.domain.users.model.KakaoUserInfoResponse;
import gist.pilldispenser.domain.users.model.OAuthTokenResponse;
import gist.pilldispenser.domain.users.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/login/oauth2/code/kakao")
    public KakaoUserInfoResponse kakaoLogin(@RequestParam("code") String code) throws IOException {
        log.info("code: "+code);
        OAuthTokenResponse tokenResponse = oAuthService.getKakaoToken(code);
        String accessToken = tokenResponse.getAccessToken();
        KakaoUserInfoResponse kakaoUserInfo = oAuthService.kakaoUserInfo(accessToken);

        return kakaoUserInfo;
    }
}