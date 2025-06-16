package report.service;

import report.entity.ReportEntity;
import report.repository.ReportRepository;
import report.repository.ReportRepositoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository = ReportRepositoryImpl.getInstance();
    private final UserRepository userRepository = UserRepositoryImpl.getInstance();;

    // 게임 결과 요약
    @Override
    public void summaryBattleReport(long accountId, List<ReportEntity> reports) {
        if (reports == null || reports.isEmpty()) {
            System.out.println("유효한 데이터가 없습니다. 게임을 먼저 진행하세요.");
            return;
        }

        System.out.println("[게임 결과 요약]");

        // 순서대로 2명씩 묶어서 출력
        for (int i = 0; i < reports.size(); i += 2) {

            ReportEntity p1 = reports.get(i);
            ReportEntity p2 = reports.get(i + 1);

            System.out.println((i / 2 + 1) + "라운드");

            printPlayerReport(p1);
            printPlayerReport(p2);

            if (p1.isWinner()) {
                System.out.println(p1.getUser().getNickname() + "님의 승리!");
            } else if (p2.isWinner()) {
                System.out.println(p2.getUser().getNickname() + "님의 승리!");
            } else {
                System.out.println("무승부!");
            }
            System.out.println();
        }
    }

    private void printPlayerReport(ReportEntity player) {
        User user = player.getUser();
        String nickname = "알 수 없음";  // 기본값
        if (user != null) {
            nickname = user.getNickname();
        }

        System.out.print(nickname + "님 → ");
        System.out.print("Dice1: " + player.getDice1() + ", Dice2: " + player.getDice2());

        if (player.isUsedSkill()) {
            System.out.print(", SkillDice: " + player.getSkillDice());
            System.out.print(" → 스킬 발동: " + player.getSkillEffect());
        }

        System.out.println(" → 총점: " + player.getDiceResultNum());
    }

    // Player의 게임 히스토리를 조회
    @Override
    public void userBattleReport(long accountId) {
        User user = userRepository.findById(accountId).orElse(null);
        if (user == null) {
            System.out.println("해당 ID의 사용자를 찾을 수 없습니다.");
            return;
        }

        List<ReportEntity> reports = reportRepository.getReports(user);
        if(reports == null || reports.isEmpty()){
            System.out.println("게임 히스토리가 없습니다.");
            return;
        }

        long winCount = 0;
        long drawCount = 0;
        long loseCount = 0;

        for (ReportEntity report : reports) {
            switch (report.getResult()) {
                case WIN: winCount++; break;
                case DRAW: drawCount++; break;
                case LOSE: loseCount++; break;
            }
        }

        long totalBattleCount = reports.size();
        double winRate = (double) winCount / totalBattleCount * 100;

        System.out.println(accountId + "님의 게임 히스토리를 조회합니다.");
        System.out.println(
                winCount + "승 " +
                        drawCount + "무 " +
                        loseCount + "패 " +
                        "(승률 : " + String.format("%.2f", winRate) + "%)");
    }
}