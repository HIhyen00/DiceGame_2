package account.service;

import account.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(String userId, String password, String nickname);
    Optional<User> findUserByUserId(String userId);
}
