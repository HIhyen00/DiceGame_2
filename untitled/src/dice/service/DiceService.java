package dice.service;

import dice.entity.DiceGame;

import java.util.List;

public interface DiceService {
    int rollDice();
    long play();
    int sum(List<Integer> scores);
//    void printResultTable(String p1Name, String p2Name, List<Integer> p1Scores, List<Integer> p2Scores, int p1Total, int p2Total);
}
