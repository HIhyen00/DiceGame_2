import account.entity.Account;
import account.repository.AccountRepository;
import account.repository.AccountRepositoryImpl;
import account.service.AccountService;
import account.service.AccountServiceImpl;
import dice.service.DiceService;
import utility.KeyboardInput;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static AccountService accountService;
    private static DiceService diceService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                        Main.registerPage();
                        break;
                    case 2:
                        Main.myPage();
                        break;
                    case 3:
                        Main.gamePage();
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
            }

        }
    }

    // 회원가입
    static void registerPage() {
        accountService.register();
    }
    // 마이페이지
    static void myPage() {
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
                        loginUser = Main.loginPage();
                        break;
                    case 2:
                        if(loginUser == null) {
                            System.out.println("로그인 후 사용할 수 있습니다.");
                        } else {
                            Main.retrievePage(loginUser);
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
            }

        }
    }
    // 게임페이지
    static void gamePage() {
        diceService.play();
    }
    // 로그인 페이지
    static long loginPage() {
        System.out.println("========== 로그인 ==========");
        return accountService.signIn();
    }
    // 배틀레포트 조회 페이지
    static void retrievePage(long accountId) {

    }



}


