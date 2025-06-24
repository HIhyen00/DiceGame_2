package report.repository;

import account.entity.User;
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
    public boolean saveReport(ReportEntity report) {
        User user = report.getUser();
        Long id = user.getId();

        // 기존 기록이 없는 경우 새 리스트 생성
        List<ReportEntity> reportList = reportMap.get(id);
        if(reportList == null) {
            reportList = new ArrayList<>();
            reportMap.put(id, reportList);
        }

        reportList.add(report);
        return true;
    }

    // 특정 Player의 기록 조회를 위한 메서드
    @Override
    public List<ReportEntity> getReports(User user) {
        List<ReportEntity> reports = reportMap.get(user.getId());
        return reports != null? reports : new ArrayList<>();
    }
}
