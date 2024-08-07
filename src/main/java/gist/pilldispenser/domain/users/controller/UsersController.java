package gist.pilldispenser.domain.users.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.domain.users.converter.UsersConverter;
import gist.pilldispenser.domain.users.entity.Users;
import gist.pilldispenser.domain.users.model.KakaoUserInfoResponse;
import gist.pilldispenser.domain.users.model.UserInfoResponse;
import gist.pilldispenser.domain.users.model.UsersResponse;
import gist.pilldispenser.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersConverter usersConverter;

    @PostMapping("/login")
    public ResponseEntity<UsersResponse> login(@RequestBody UserInfoResponse userInfo){
        Users user = usersService.loginUser(
                UserInfoResponse.toMemberRequest(userInfo));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + usersService.issueAccessToken(user));

        return new ResponseEntity<>(usersConverter.toResponse(user), headers, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UsersResponse> userInfo(@AuthenticationPrincipal UsersDetails usersDetails){
        UsersResponse response = usersConverter.toResponse(
                usersService.getUserInfo(usersDetails)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}