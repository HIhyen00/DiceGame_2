package report.service;

import report.entity.ReportEntity;

import java.util.List;

public interface ReportService {

    // 게임 결과 요약
    int summaryBattleReport(long accountId, List<ReportEntity> reports);

    // 게임 히스토리 확인하기
    int userBattleReport(long accountId);

}