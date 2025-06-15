package dice.service;

import account.entity.Account;
import dice.entity.Dice;
import dice.entity.SkillDice;
import dice.repository.DiceRepository;
import dice.repository.DiceRepositoryImpl;
import util.DiceGenerator;

public class DiceServiceImpl implements DiceService {
    private static DiceServiceImpl instance;

    private final DiceRepository diceRepository;

    private DiceServiceImpl() {
        this.diceRepository = DiceRepositoryImpl.getInstance();
    }
    public static DiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceServiceImpl();
        }
        return instance;
    }

    @Override
    public Dice diceRoll(Integer accountIdToken) {
        System.out.println( "player" + accountIdToken + "이 주사위를 굴렸습니다!");
        int d1 = DiceGenerator.getDiceNumber();
        int d2 = DiceGenerator.getDiceNumber();

        Dice dice = new Dice(d1,d2);
        diceRepository.save(dice);
        return dice;
    }

    @Override
    public int skillDIceRoll(Dice dice, int opponentScore ) {
        if (dice.getFirstValue() % 2 == 0) {
            System.out.println( "조건이 만족하여 스킬 주사위가 발동됐습니다!");
            int total;
            int d3 = DiceGenerator.getDiceNumber();
            SkillDice skillDice = new SkillDice(d3);
            diceRepository.skillSave(skillDice);

            if (d3 == 3) {
                total = dice.getSum() + opponentScore;
                System.out.println("상대 점수 강탈!");
            } else if (d3 == 4) {
                System.out.println("즉사! 게임이 종료됩니다.");
                total = 0;
                // 여기서 메인 화면으로 돌아가도록 로직 수정 필요
            } else {
                total = dice.getSum();
                System.out.println("일반 주사위 합만 나옵니다.");
            }
            return total;
        }
        System.out.println("스킬 주사위 조건에 만족하기 않습니다.");
        return dice.getSum();

    }

}
