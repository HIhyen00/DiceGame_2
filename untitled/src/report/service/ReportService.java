package report.service;

import dice.entity.DiceGame;
import java.util.List;

public interface ReportService {
    long register();
    long printResult(String p1Name, String p2Name, List<Integer> p1Scores, List<Integer> p2Scores, int p1Total, int p2Total);
}
