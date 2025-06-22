package dice.entity;

public class Dice {
    private static long idCounter = 1;

    private int firstValue; // 주사위 눈금의 결과값
    private int secondValue;
    private long diceId; //스킬 주사위 고유 아이디
    private int sum;
    private boolean skillUsed;

    public Dice(int firstValue, int secondValue) {
        this.diceId = idCounter++;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.sum = firstValue + secondValue;
    }

    public long getId() { return diceId; }

    public int getSecondValue() { return secondValue; }

    public int getFirstValue() { return firstValue; }

    public int getSum() { return sum; }

    public boolean getSkillUsed() {
        if (firstValue % 2 == 0) {
            skillUsed = true;
        }
        return skillUsed = false;
    }

    @Override
    public String toString() {
        return  "주사위1=" + firstValue +
                "\n주사위2=" + secondValue;
    }
}

