import account_hi.entity.Account;
import account_hi.repository.AccountRepository;
import account_hi.repository.AccountRepositoryImpl;
import utility.KeyboardInput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account account = new Account("아이디", "비밀번호");

        AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
        accountRepository.save(account);

        while (true) {
            System.out.println("1. 마이페이지");
            System.out.println("2. 주사위 게임\n");
            System.out.println("0. 종료");

            String input = KeyboardInput.getStringInput("실행 하고 싶은 번호를 입력하세요 : ");

            try {
                if(Integer.parseInt(input) == 1){
                    // 마이페이지로...
                }else if(Integer.parseInt(input) == 2){
                    // 주사위게임 페이지로...
                }else if(Integer.parseInt(input) == 0){
                    System.out.println("종료합니다.");
                    System.exit(0);
                }
            }catch (NullPointerException e){
                System.out.println("입력된 값이 없습니다.");
            }catch (Exception e){
                System.out.println("잘 못 입력 하였습니다.");
            }

        }
    }

//    public class Mypage() {
//        System.out.println("1. 로그인");
//        System.out.println("2. 배틀 레포트 조회");
//        System.out.println("0. 종료");
//    }

    public class GamePage() {
        // game page
    }
}
