package dice.repository;

import dice.entity.Dice;
import dice.entity.SkillDice;

public interface DiceRepository {
    int save(Dice dice); // 일반 주사위 값
    int skillSave(SkillDice skillDice); //스킬 주사위 값
}
