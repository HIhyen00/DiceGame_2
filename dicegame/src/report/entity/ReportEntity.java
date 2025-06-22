package report.entity;

import account.entity.User;

public class ReportEntity {
    private User user;                  // User 객체 활용
//    private long gameId;                // 어떤 Player와 어떤 Player가 겨뤘는지 확인할 수 있는 id
    private long gameRoundNum;          // 게임 라운드 숫자
    private int dice1;                  // 1번 주사위 숫자
    private int dice2;                  // 2번 주사위 숫자
    private int skillDice;              // 스킬 주사위 숫자
    private int diceResultNum;          // 주사위 총합
    private boolean usedSkill;          // 스킬 사용 여부
    private String skillEffect;         // 스킬 효과 (ex : 점수 훔침, 즉사)
    private long totalBattleCount;      // 총 배틀 수
    private long winCount;              // 승리 수
    private long loseCount;             // 패배 수
    private GameResult result;          // 게임 결과

    public ReportEntity(User user, long gameRoundNum, int dice1, int dice2, int skillDice, int diceResultNum, boolean usedSkill, String skillEffect, long totalBattleCount, long winCount, long loseCount, GameResult result) {
        this.user = user;
//        this.gameId = gameId;
        this.gameRoundNum = gameRoundNum;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.skillDice = skillDice;
        this.diceResultNum = diceResultNum;
        this.usedSkill = usedSkill;
        this.skillEffect = skillEffect;
        this.totalBattleCount = totalBattleCount;
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.result = result;
    }

    public User getUser() {
        return user;
    }

//    public long getGameId() {
//        return gameId;
//    }

    public long getGameRoundNum() {
        return gameRoundNum;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public int getSkillDice() {
        return skillDice;
    }

    public int getDiceResultNum() {
        return diceResultNum;
    }

    public boolean isUsedSkill() {
        return usedSkill;
    }

    public String getSkillEffect() {
        return skillEffect;
    }

    public long getTotalBattleCount() {
        return totalBattleCount;
    }

    public long getWinCount() {
        return winCount;
    }

    public long getLoseCount() {
        return loseCount;
    }

    public GameResult getResult() {
        return result;
    }

    public boolean isWinner() {
        return this.result == GameResult.WIN;
    }
}