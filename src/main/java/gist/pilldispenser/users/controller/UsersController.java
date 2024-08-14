package gist.pilldispenser.users.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.users.converter.UsersConverter;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.domain.model.UserInfoResponse;
import gist.pilldispenser.users.domain.model.UsersHardwareNoRequest;
import gist.pilldispenser.users.domain.model.UsersResponse;
import gist.pilldispenser.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "사용자 정보 관리 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersConverter usersConverter;

    @Operation(summary = "로그인", description = "카카오계정으로 서비스에 사용자 정보를 연동합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 로그인되었습니다"),
//            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
//            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @PostMapping("/login")
    public ResponseEntity<UsersResponse> login(@RequestBody UserInfoResponse userInfo){
        Users user = usersService.loginUser(
                UserInfoResponse.toMemberRequest(userInfo));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usersService.issueAccessToken(user));

        return new ResponseEntity<>(usersConverter.toResponse(user), headers, HttpStatus.OK);
    }

    @Operation(summary = "사용자 정보 조회", description = "사용자의 정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping("/me")
    public ResponseEntity<UsersResponse> userInfo(
            @Parameter(description = "사용자 정보") @AuthenticationPrincipal UsersDetails usersDetails){
        UsersResponse response = usersConverter.toResponse(
                usersService.getUserInfo(usersDetails)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "하드웨어 시리얼 넘버 등록", description = "사용자 계정에 하드웨어 시리얼 넘버를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 업데이트 되었습니다."),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @PutMapping("/hardware-no")
    public ResponseEntity<Void> hardwareNo(@AuthenticationPrincipal UsersDetails usersDetails,
                                           @RequestBody UsersHardwareNoRequest request){
        usersService.updateHardwareNo(usersDetails.getUsername(), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}