package gist.pilldispenser.users.domain.model;

public record UsersRequest(
        String email,
        String password,
        String profileImage,
        String nickname
) {
}