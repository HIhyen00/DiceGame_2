package account.application;

import account.entity.User;
import account.repository.UserRepositoryImpl;
import account.service.UserService;
import java.util.Optional;
import java.util.Scanner; // 사용자 입력을 받기 위해 Scanner를 추가합니다.

public class Main {

    public static void main(String[] args) {
        // 사용자 저장소와 서비스 준비
        UserRepositoryImpl userRepository = UserRepositoryImpl.getInstance();
        UserService userService = new UserService(userRepository);

        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받을 스캐너 객체 생성

        System.out.println("--- 사용자 관리 프로그램 시작 ---");

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
                    User registeredUser = userService.registerUser(registerUserId, registerPassword, registerNickname);
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