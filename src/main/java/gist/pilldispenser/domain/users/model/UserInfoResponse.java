package gist.pilldispenser.domain.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse{
    private Long id;
    private LocalDateTime connectedAt;
    private String email;
    private String nickname;
    private String profileImage;

    public static UsersRequest toMemberRequest(UserInfoResponse userInfo) {
        return new UsersRequest(userInfo.getEmail(),
                String.valueOf(userInfo.getId()),
                userInfo.getProfileImage(),
                userInfo.getNickname());
    }
}
