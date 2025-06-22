import account.entity.User;
import account.repository.UserRepository;
import account.repository.UserRepositoryImpl;
import account.service.UserService;
import account.service.UserServiceImpl;
import account.service.UserService;
import account.service.UserServiceImpl;
import dice.entity.Dice;
import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import dice.utility.DiceGenerator;
import utility.KeyboardInput;

import java.util.Optional;

public class Main {

/*
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl.getInstance();

        System.out.println("== 회원가입 ==");
        String userId = KeyboardInput.getStringInput("ID 입력: ");
        String password = KeyboardInput.getStringInput("비밀번호 입력 (4자 이상): ");
        String nickname = KeyboardInput.getStringInput("닉네임 입력: ");

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
    }*/
}
        /*
        Home.main(args);

        // 회원가입
        UserService userService = UserServiceImpl.getInstance();
        userService.registerUser();
        userService.registerUser();

        // 로그인
        Long accountIdToken  = userService.signIn(); // 로그인 유저
        Long opponentId = userService.signIn();

        // 주사위를 굴림
        DiceService diceService = DiceServiceImpl.getInstance();
        Dice playerDice = diceService.diceRoll(accountIdToken); // 로그인 유저 주사위 굴린거
        Dice opponentDice = diceService.diceRoll(opponentId); // 로그인 안한 상대 유저 주사위 굴린거
        int opponentScore = opponentDice.getSum();

        //스킬조건에 만족하면 실행
        int result = diceService.skillDIceRoll(playerDice, opponentScore);
        // 상대 점수 설정!! , 상대방도 skill 주사위를 쓸 수 있음
        System.out.println("로그인 한 다이스 값 : " + result);
        // opponent 의 다이스 값도 필요
        */



