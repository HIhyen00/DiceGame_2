package account.service;

import account.entity.User;
import account.repository.UserRepository;
import account.repository.UserRepositoryImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User registerUser(String userId, String password, String nickname) {
        System.out.println("\n 사용자 등록 시도: " + userId);

        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            System.out.println("사용자 등록 실패: '" + userId + "'는 이미 있는 ID입니다.");
            return null;
        }

        try {
            User newUser = new User(userId, password, nickname);
            userRepository.save(newUser);
            System.out.println("사용자 등록 성공: " + newUser.getUserId());
            return newUser;
        } catch (IllegalArgumentException e) {
            System.out.println("사용자 등록 실패: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        System.out.println("\n 사용자 ID로 조회 요청: " + userId);
        return userRepository.findByUserId(userId);
    }
}
