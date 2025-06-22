package account.service;

import account.entity.User;
import account.repository.UserRepository;
import account.repository.UserRepositoryImpl;
import utility.KeyboardInput;

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
    private final int PASSWORD_MIN_LENGTH = 4;

    @Override
    public User registerUser() {
        System.out.println("========== 회원가입 ==========");
        String userId = KeyboardInput.getStringInput("ID 입력: ");
        String password = KeyboardInput.getStringInput("비밀번호 입력 (4자 이상): ");
        int pwdLength = password.length();
        if(pwdLength < PASSWORD_MIN_LENGTH) {
            System.out.println("비밀 번호가 짧습니다. 다시 입력해주세요.");
            password = KeyboardInput.getStringInput("비밀번호 입력 (4자 이상): ");
            pwdLength = password.length();
        }
        String nickname = KeyboardInput.getStringInput("닉네임 입력: ");

        //System.out.println("\n 사용자 등록 시도: " + userId);
        Optional<User> existingUser = userRepository.findByUserId(userId);
        if (existingUser.isPresent()) {
            System.out.println("회원가입 실패: '" + userId + "'는 이미 있는 ID입니다.");
            return null;
        }

        try {
            User newUser = new User(userId, password, nickname);
            userRepository.save(newUser);
            System.out.println("🎉" + nickname + "님 회원가입 성공! 환영합니다~");
            return newUser;
        } catch (IllegalArgumentException e) {
            System.out.println("😢 회원가입 실패. 다시 시도해주세요.");
            return null;
        }
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        System.out.println("\n 사용자 ID로 조회 요청: " + userId);
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<Long> signIn() {
        int attempt = 1;
        while(attempt < 3) {
            System.out.println("========== 로그인 ==========");
            String userId = KeyboardInput.getStringInput("ID: ");
            String password = KeyboardInput.getStringInput("비밀번호: ");

            Optional<User> maybeUser = userRepository.findByUserId(userId);

            if(maybeUser.isPresent()) {
                User user = maybeUser.get();
                if(user.getPassword().equals(password)) {
                    System.out.println("🎉 로그인 성공! " + user.getNickname() + "님 환영합니다.");
                    return Optional.of(user.getId());
                }
            }
            System.out.println("❌ 틀렸습니다. 다시 입력해주세요.");
            attempt++;
        }
//        System.out.println("❌ 로그인에 실패하였습니다.");
        return Optional.empty();
    }
}
