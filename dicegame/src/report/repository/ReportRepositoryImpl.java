package report.repository;

import report.entity.ReportEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//리포트 저장소 (히스토리 기능 등)
public class ReportRepositoryImpl implements ReportRepository {

    private static ReportRepositoryImpl instance;
    private static final Map<Long, List<ReportEntity>> reportMap = new HashMap<>();

    private ReportRepositoryImpl() {}
    public static ReportRepositoryImpl getInstance() {
        if(instance == null){
            instance = new ReportRepositoryImpl();
        }
        return instance;
    }

    // 게임 턴당 기록을 저장하는 메서드
    @Override
    public int saveReport(ReportEntity report) {
        long playerId = report.getPlayerId();

        // 기존 기록이 없는 경우 새 리스트 생성
        List<ReportEntity> reportList = reportMap.get(playerId);
        if(reportList == null) {
            reportList = new ArrayList<>();
            reportMap.put(playerId, reportList);
        }

        // 현재 기록 추가
        reportList.add(report);
        return 0;
    }

    // 특정 Player의 기록 조회를 위한 메서드
    @Override
    public List<ReportEntity> getReports(Account account) {
        long accountId = account.getId();
        List<ReportEntity> reports;
        if (reportMap.containsKey(accountId)) {
            reports = reportMap.get(accountId);
        } else {
            reports = new ArrayList<>();
        }
        return reports;
    }
}
