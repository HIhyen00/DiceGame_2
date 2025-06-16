package report.repository;

import dice.entity.DiceGame;
import report.entity.Report;

public interface ReportRepository {
//    long save(Report report);
    DiceGame findGameById(long id);
}
