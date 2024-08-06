package gist.pilldispenser.domain.users.model;

public record UsersRequest(
        String email,
        String password,
        String profileImage,
        String nickname
) {
}