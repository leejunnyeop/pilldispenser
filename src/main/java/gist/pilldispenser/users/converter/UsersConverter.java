package gist.pilldispenser.users.converter;

import gist.pilldispenser.common.entity.enums.RoleType;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.domain.model.UsersRequest;
import gist.pilldispenser.users.domain.model.UsersResponse;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public Users toEntity(UsersRequest request, String password){
        return Users.builder()
                .email(request.email())
                .password(password)
                .profileImage(request.profileImage())
                .nickname(request.nickname())
                .roleType(RoleType.ROLE_USER)
                .hardwareNo(null)
                .build();
    }

    public UsersResponse toResponse(Users users){
        return UsersResponse.builder()
                .email(users.getEmail())
                .profileImage(users.getProfileImage())
                .nickname(users.getNickname())
                .hardwareNo(users.getHardwareNo())
                .build();
    }
}
