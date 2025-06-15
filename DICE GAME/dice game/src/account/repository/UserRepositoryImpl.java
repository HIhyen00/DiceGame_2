package account.repository;

import account.entity.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;
    private static final Map<Integer, User> userHashMap = new HashMap<>();

    private UserRepositoryImpl() {}

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public int save(User user) {
        System.out.println("[UserRepository] 사용자 정보 저장 시도: " + user.getUserId());
        int userUniqueId = (int) user.getId();
        userHashMap.put(userUniqueId, user);
        System.out.println("[UserRepository] 사용자 정보 저장 완료: " + user.getUserId() + " (ID: " + userUniqueId + ")");
        return userUniqueId;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        System.out.println("[UserRepository] 사용자 ID로 조회 시도: " + userId);
        return userHashMap.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public Optional<User> findById(Integer id) {
        System.out.println("[UserRepository] 고유 ID로 조회 시도: " + id);
        return Optional.ofNullable(userHashMap.get(id));
    }
}