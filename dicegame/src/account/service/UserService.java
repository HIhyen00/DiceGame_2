package account.service;

import account.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser();
    Optional<User> findUserByUserId(String userId);
    Optional<Long> signIn();
}
