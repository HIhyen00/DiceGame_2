import account.service.AccountService;
import account.service.AccountServiceImpl;
import dice.entity.Dice;
import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import util.DiceGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println(DiceGenerator.getDiceNumber());
        // 회원가입
        AccountService accountService = AccountServiceImpl.getInstance();
        accountService.register();
        accountService.register();

        // 로그인
        Integer accountIdToken  = accountService.signIn(); // 로그인 유저
        Integer opponentId = (accountIdToken == 1) ? 2 : 1; // 로그인 안한 상대 유저

        // 주사위를 굴림
        DiceService diceService = DiceServiceImpl.getInstance();
        Dice playerDice = diceService.diceRoll(accountIdToken ); // 로그인 유저 주사위 굴린거
        Dice opponentDice = diceService.diceRoll(opponentId); // 로그인 안한 상대 유저 주사위 굴린거
        int opponentScore = opponentDice.getSum();

        //스킬조건에 만족하면 실행
        int result = diceService.skillDIceRoll(playerDice, opponentScore);
        System.out.println("로그인 한 다이스 값 : " + result);
    }
}
