package dice.entity;

public class SkillDice {
    private static long idCounter = 1;

    private int skillValue; // 주사위 눈금의 결과값
    private long skillDiceId; //스킬 주사위 고유 아이디

    public SkillDice(int skillValue) {
        this.skillDiceId = idCounter++;
        this.skillValue = skillValue;
    }

    public int getSkillValue() {
        return skillValue;
    }

    public long getSkillDiceId() {
        return skillDiceId;
    }

    @Override
    public String toString() {
        return "SkillDice{" +
                "스킬 주사위 Id=" + skillDiceId +
                ", 스킬 주사위 값=" + skillValue +
                '}';
    }
}
