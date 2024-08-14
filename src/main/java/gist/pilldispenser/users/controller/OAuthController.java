package gist.pilldispenser.users.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.users.domain.model.OAuthTokenResponse;
import gist.pilldispenser.users.domain.model.UserInfoResponse;
import gist.pilldispenser.users.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.net.URL;

@Tag(name = "OAuth2", description = "OAuth2 관련 API")

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @Operation(summary = "카카오 OAuth2 ", description = "인가코드로 액세스토큰/카카오 유저 정보를 받아옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 정보를 전달하였습니다."),
//            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
//            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping("/login/oauth2/code/kakao")
    public ResponseEntity<UserInfoResponse> kakaoLogin(
            @Parameter(description = "카카오톡 연동 인가코드")@RequestParam("code") String code,
            @Parameter(description = "redirect-uri") @RequestParam("redirect_uri") String redirectUri) throws IOException {
        log.info("Kakao login oauth code: " + code);
        log.info("Kakao login oauth redirect uri: " + redirectUri);

        try {
            OAuthTokenResponse tokenResponse = oAuthService.getKakaoToken(code, redirectUri);
            UserInfoResponse userInfo = oAuthService.kakaoUserInfo(tokenResponse.getAccessToken());

            oAuthService.saveAccessTokenToRedis(tokenResponse, userInfo.getEmail());
            oAuthService.saveRefreshTokenToRedis(tokenResponse, userInfo.getEmail());
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "카카오 액세스 토큰 재발급", description = "카카오 액세스 토큰을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 토큰이 재발급되었습니다."),
//            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
//            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping("/oauth2/token")
    public void kakaoTokenRenew(
            @Parameter(description = "사용자 정보") @AuthenticationPrincipal UsersDetails usersDetails) throws IOException {
        oAuthService.reissueTokens(usersDetails.getUsername());
    }

}
