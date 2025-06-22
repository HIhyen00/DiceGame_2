package dice.entity;

public class SkillDice {
    private static long idCounter = 1;

    private int skillValue; // 주사위 눈금의 결과값
    private int totalValue;
    private long skillDiceId; //스킬 주사위 고유 아이디
    private boolean isDeath;

    public SkillDice(int skillValue,int totalValue, boolean isDeath) {
        this.skillDiceId = idCounter++;
        this.skillValue = skillValue;
        this.totalValue = totalValue;
        this.isDeath = isDeath;
    }

    public int getSkillValue() {
        return skillValue;
    }
    public long getSkillDiceId() {
        return skillDiceId;
    }
    public int getTotalValue() { return totalValue; }
    public boolean isDeath() { return isDeath; }

    public String getEffect() {
        if(skillValue == 3) return "상대 점수 강탈";
        if(skillValue == 4) return "즉사";
        return "---";
    }
    @Override
    public String toString() {
        return  "skill주사위 =" + skillValue +
                "\n총 합 = " + totalValue;
    }
}
