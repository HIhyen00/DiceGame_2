package report.repository;

import dice.entity.DiceGame;
import report.entity.Report;

public class ReportRepositoryImpl implements ReportRepository {
    private static ReportRepositoryImpl instance;
    private ReportRepositoryImpl() {}
    public static ReportRepository getInstance() {
        if (instance == null) {
            instance = new ReportRepositoryImpl();
        }
        return instance;
    }

    @Override
    public DiceGame findGameById(long id) {
        return null;
    }

//    @Override
//    public long save(Report report) {
//        return 0;
//    }
}
