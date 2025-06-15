package dice.repository;

import dice.entity.Dice;
import dice.entity.SkillDice;

import java.util.HashMap;
import java.util.Map;

public class DiceRepositoryImpl implements DiceRepository {
    private static DiceRepositoryImpl instance;

    private DiceRepositoryImpl() {}

    public static DiceRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceRepositoryImpl();
        }
        return instance;
    }
    private static final Map<Integer, Dice> diceMap = new HashMap<Integer, Dice>();
    private static final Map<Integer, SkillDice> skillDiceMap = new HashMap<Integer, SkillDice>();

    @Override
    public int save(Dice dice) {
        System.out.println("일반 주사위 결과를 저장하는거야? "+dice);
        int diceUniqueId = (int) dice.getId();
        diceMap.put(diceUniqueId, dice);
        return diceUniqueId;
    }

    @Override
    public int skillSave(SkillDice skillDice) {
        System.out.println("스킬 주사위 결과를 저장하는거야? "+skillDice);
        int skillDiceUniqueId = (int) skillDice.getSkillDiceId();
        skillDiceMap.put(skillDiceUniqueId, skillDice);
        return skillDiceUniqueId;
    }
}
