import account.entity.User;
import account.repository.UserRepository;
import account.repository.UserRepositoryImpl;
import account.service.UserService;
import account.service.UserServiceImpl;
import dice.entity.Dice;
import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import game.GameController;
import report.repository.ReportRepository;
import report.service.ReportService;
import utility.KeyboardInput;
import java.util.Optional;
import java.util.Scanner;

public class Home {
    private final UserService userService = UserServiceImpl.getInstance();
    private static UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static DiceService diceService;
    private static GameController gameController;
    private static ReportRepository reportRepository;
    private static ReportService reportService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Home home = new Home();

        while (true) {
            System.out.println("==== DiceGame에 오신 걸 환영합니다🎉 ====");
            System.out.println("1. 회원가입");
            System.out.println("2. 마이페이지");
            System.out.println("3. 주사위 게임");
            System.out.println("0. 종료");

            String input = KeyboardInput.getStringInput("실행 하고 싶은 숫자를 입력하세요 : ");
            int choice = 0;
            try {
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        home.registerPage();
                        break;
                    case 2:
                        home.myPage();
                        break;
                    case 3:
                        home.gamePage();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("0~3사이의 숫자를 입력해주세요");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            } catch (Exception e) {
                System.out.println("오류가 발생 하였습니다.");
                e.printStackTrace();
            }

        }
    }

    // 회원가입
    void registerPage() {
        userService.registerUser();
    }

    // 마이페이지
    void myPage() {
        Home home = new Home();
        Long loginUser = null;
        while (true) {
            System.out.println("======== MyPage📝 ========");
            System.out.println("1. 로그인");
            System.out.println("2. 배틀 레포트 조회");
            System.out.println("0. 종료");

            String input = KeyboardInput.getStringInput("실행 하고 싶은 숫자를 입력하세요 : ");
            int choice = 0;
            try {
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        long userId = home.loginPage();
                        if (userId == -1) {
                            System.out.println("❌ 로그인에 실패했습니다.");
                        } else {
                            loginUser = userId;
                        }
                        break;
                    case 2:
                        if(loginUser == null) {
                            System.out.println("로그인 후 사용할 수 있습니다.");
                        } else {
                            home.retrievePage();
                        }
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("0~2사이의 숫자를 입력해주세요");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            } catch (Exception e) {
                System.out.println("오류가 발생 하였습니다.");
                e.printStackTrace();
            }

        }
    }
    // 게임페이지
    void gamePage() {
        GameController gameController = new GameController();
        gameController.startGame();
//        UserService userService = UserServiceImpl.getInstance();
//        userService.registerUser();
//        userService.registerUser();
//
//        //로그인
//        Long accountIdToken  = userService.signIn(); // 로그인 유저
//        Long opponentId = userService.signIn();
//
//        // 주사위를 굴림
//        DiceService diceService = DiceServiceImpl.getInstance();
//        Dice playerDice = diceService.diceRoll(accountIdToken); // 로그인 유저 주사위 굴린거
//        int playerScore = playerDice.getSum();
//        Dice opponentDice = diceService.diceRoll(opponentId); // 로그인 안한 상대 유저 주사위 굴린거
//        int opponentScore = opponentDice.getSum();
//
//        //스킬조건에 만족하면 실행
//        int result1 = diceService.skillDIceRoll(playerDice, opponentScore);
//        int result2 = diceService.skillDIceRoll(opponentDice, playerScore);
//        // 상대 점수 설정!! , 상대방도 skill 주사위를 쓸 수 있음
//        System.out.println("player1의 주사위 값 : " + result1);
//        System.out.println("player2의 주사위 값 : " + result2);

    }

    // 로그인 페이지
     long loginPage() {
        Optional<Long> loginUser = userService.signIn();
        if(loginUser.isPresent()) {
            return loginUser.get();
        }
        return -1;
    }

    // 배틀레포트 조회 페이지
    void retrievePage() {
        String inputId = KeyboardInput.getStringInput("배틀 리포트를 조회할 Player의 아이디를 입력해 주세요 : ");

        Optional<User> user = userRepository.findByUserId(inputId);
        if(user.isPresent()) {
            long id = user.get().getId();
            reportService.userBattleReport(id);
        }
    }


    void mypage() {
        UserRepository userRepository = UserRepositoryImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();

        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받을 스캐너 객체 생성

        System.out.println("--- 마이페이지 시작 ---");

        while (true) { // 프로그램이 계속 실행되도록 무한 루프
            System.out.println("\n--- 메뉴 ---");
            System.out.println("1. 사용자 등록 (회원가입)");
            System.out.println("2. 사용자 조회 (ID로 찾기)");
            System.out.println("3. 프로그램 종료");
            System.out.print("메뉴를 선택하세요: ");

            String menuChoice = scanner.nextLine(); // 사용자 입력 받기

            switch (menuChoice) {
                case "1":
                    // 1. 사용자 등록 (회원가입)
                    System.out.println("\n--- 사용자 등록 ---");
                    System.out.print("사용할 아이디를 입력하세요: ");
                    String registerUserId = scanner.nextLine();
                    System.out.print("비밀번호를 입력하세요 (4자 이상): ");
                    String registerPassword = scanner.nextLine();
                    System.out.print("닉네임을 입력하세요: ");
                    String registerNickname = scanner.nextLine();

                    // UserService를 통해 사용자 등록 시도
                    User registeredUser = userService.registerUser();
                    if (registeredUser != null) {
                        System.out.println("🎉 회원가입 성공! " + registeredUser.getNickname() + "님 환영합니다.");
                    } else {
                        System.out.println("😢 회원가입 실패. 다시 시도해주세요.");
                    }
                    break;

                case "2":
                    // 2. 사용자 조회 (ID로 찾기)
                    System.out.println("\n--- 사용자 조회 ---");
                    System.out.print("조회할 사용자 아이디를 입력하세요: ");
                    String searchUserId = scanner.nextLine();

                    // UserService를 통해 사용자 조회 시도
                    Optional<User> foundUser = userService.findUserByUserId(searchUserId);
                    if (foundUser.isPresent()) {
                        System.out.println("✨ 사용자 정보를 찾았습니다: " + foundUser.get());
                    } else {
                        System.out.println("🔍 '" + searchUserId + "' 아이디를 가진 사용자를 찾을 수 없습니다.");
                    }
                    break;

                case "3":
                    // 3. 프로그램 종료
                    System.out.println("\n--- 프로그램을 종료합니다. ---");
                    scanner.close(); // 스캐너 닫기
                    return; // main 메서드 종료

                default:
                    System.out.println("⚠️ 잘못된 메뉴 선택입니다. 1, 2, 3 중 하나를 입력해주세요.");
                    break;
            }
        }
    }
}



