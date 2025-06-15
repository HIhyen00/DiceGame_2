package util;

import java.util.Random;

public class DiceGenerator {
    private static Random rand = new Random();

    //DiceGenerator를 생성자
    private DiceGenerator(){}

    public static int getDiceNumber() {
        return rand.nextInt(6)+1;
    }
}
