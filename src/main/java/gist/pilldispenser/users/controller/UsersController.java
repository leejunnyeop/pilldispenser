package gist.pilldispenser.users.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.users.converter.UsersConverter;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.domain.model.UserInfoResponse;
import gist.pilldispenser.users.domain.model.UsersHardwareNoRequest;
import gist.pilldispenser.users.domain.model.UsersResponse;
import gist.pilldispenser.users.service.UsersService;
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

    @PutMapping("/hardware-no")
    public ResponseEntity<Void> hardwareNo(@AuthenticationPrincipal UsersDetails usersDetails,
                                                    @RequestBody UsersHardwareNoRequest request){
        usersService.updateHardwareNo(usersDetails.getUsername(), request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}