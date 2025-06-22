package game;

import account.entity.User;
import account.repository.UserRepository;
import account.repository.UserRepositoryImpl;
import account.service.UserService;
import account.service.UserServiceImpl;
import dice.entity.Dice;
import dice.entity.SkillDice;
import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import report.entity.GameResult;
import report.entity.ReportEntity;
import report.repository.ReportRepository;
import report.repository.ReportRepositoryImpl;
import report.service.ReportService;
import report.service.ReportServiceImpl;
import utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();
    private final DiceService diceService = DiceServiceImpl.getInstance();
    private final ReportRepository reportRepository = ReportRepositoryImpl.getInstance();
    private final ReportService reportService = ReportServiceImpl.getInstance();

    private final List<ReportEntity> gameReports = new ArrayList<>();

    private User user1;
    private User user2;

    public void startGame() {
        if(!loginPlayer()) return;

        long round = 1;
        while(true) {

            System.out.println("===== Round" + round + "시작 =====");
            playGame(round);


            String go = KeyboardInput.getStringInput("🎲 게임을 더 하시겠습니까? (y/n): ");
            if(go.equals("n")) {
                System.out.println("게임을 종료합니다.");
                break;
            }
            round++;
        }
    }

    public boolean loginPlayer() {
        Optional<Long> maybeUser1 = userService.signIn();
        Optional<Long> maybeUser2 = userService.signIn();

        if(maybeUser1.isEmpty() || maybeUser2.isEmpty()) {
            System.out.println("😥 로그인 실패로 게임을 시작할 수 없습니다");
            return false;
        }

        Long user1Id = maybeUser1.get();
        Long user2Id = maybeUser2.get();

        user1 = userRepository.findById(user1Id).get();
        user2 = userRepository.findById(user2Id).get();

        return true;
    }

    public void playGame(long round) {
        // 로그인
//        Optional<Long> maybeUser1 = userService.signIn();
//        Optional<Long> maybeUser2 = userService.signIn();
//
//        if(maybeUser1.isEmpty() || maybeUser2.isEmpty()) {
//            System.out.println("😥 로그인 실패로 게임을 시작할 수 없습니다");
//            return false;
//        }
//
//        Long user1Id = maybeUser1.get();
//        Long user2Id = maybeUser2.get();
//
//        User user1 = userRepository.findById(user1Id).get();
//        User user2 = userRepository.findById(user2Id).get();

        // 주사위 굴림
        Dice user1Dice = diceService.diceRoll(user1.getId());
        Dice user2Dice = diceService.diceRoll(user2.getId());

        // 스킬 주사위 굴림
        System.out.print("\nplayer1 ");
        SkillDice user1SkillDice = diceService.skillDIceRoll(user1Dice, user2Dice.getSum());
        System.out.print("\nplayer2 ");
        SkillDice user2SkillDice = diceService.skillDIceRoll(user2Dice, user1Dice.getSum());
        int user1Result = user1SkillDice.getTotalValue();
        int user2Result = user2SkillDice.getTotalValue();
        boolean user1Death = user1SkillDice.isDeath();
        boolean user2Death = user2SkillDice.isDeath();

        // 결과
        GameResult user1ResultEnum;
        GameResult user2ResultEnum;
        if(user1Death && !user2Death) {
            user1ResultEnum = GameResult.LOSE;
            user2ResultEnum = GameResult.WIN;
        } else if (!user1Death && user2Death) {
            user1ResultEnum = GameResult.WIN;
            user2ResultEnum = GameResult.LOSE;
        } else if (user1Result > user2Result) {
            user1ResultEnum = GameResult.WIN;
            user2ResultEnum = GameResult.LOSE;
        } else if (user1Result < user2Result) {
            user1ResultEnum = GameResult.LOSE;
            user2ResultEnum = GameResult.WIN;
        } else {
            user1ResultEnum = user2ResultEnum = GameResult.DRAW;
        }

        // 리포트 기록
        ReportEntity user1Report = new ReportEntity(
                user1, round,
                user1Dice.getFirstValue(), user1Dice.getSecondValue(),
                user1SkillDice.getSkillValue(), user1Result, user1Dice.getSkillUsed(), user1SkillDice.getEffect(), round,
                user1ResultEnum == GameResult.WIN ? 1 : 0,
                user1ResultEnum == GameResult.LOSE ? 1 : 0,
                user1ResultEnum
        );

        ReportEntity user2Report = new ReportEntity(
                user2, round,
                user2Dice.getFirstValue(), user2Dice.getSecondValue(),
                user2SkillDice.getSkillValue(), user2Result, user2Dice.getSkillUsed(), user2SkillDice.getEffect(), round,
                user2ResultEnum == GameResult.WIN ? 1 : 0,
                user2ResultEnum == GameResult.LOSE ? 1 : 0,
                user2ResultEnum
        );

        reportRepository.saveReport(user1Report);
        reportRepository.saveReport(user2Report);
        gameReports.add(user1Report);
        gameReports.add(user2Report);

        // 요약
        reportService.summaryBattleReport(user1.getId(), gameReports);
    }
}
