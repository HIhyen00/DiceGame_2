package account.repository;

import account.entity.User;

import java.util.Optional;

public interface UserRepository {
    int save(User user);
    Optional<User> findByUserId(String userId);
    Optional<User> findById(Integer id);
}
