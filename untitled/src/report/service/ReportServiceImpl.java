package report.service;

import dice.entity.DiceGame;

import java.awt.*;

public class ReportServiceImpl implements ReportService {
    private static ReportServiceImpl reportServiceImpl;
    private ReportServiceImpl() {}
    public static ReportServiceImpl getInstance() {
        if (reportServiceImpl == null) {
            reportServiceImpl = new ReportServiceImpl();
        }
        return reportServiceImpl;
    }

    @Override
    public long printResult(String p1Name, String p2Name, List<Integer> p1Scores, List<Integer> p2Scores, int p1Total, int p2Total) {
        return 0;
    }


}
