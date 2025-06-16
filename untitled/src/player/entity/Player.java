package player.entity;

import java.util.List;

public class Player {
//    private final long player1Id;
//    private final long player2Id;
//
//    public Player(long player1Id, long player2Id) {
//        this.player1Id = player1Id;
//        this.player2Id = player2Id;
//    }
//
//    public long getPlayer1Id() { return player1Id; }
//
//    public long getPlayer2Id() { return player2Id; }
//
//    @Override
//    public String toString() {
//        return "(" + player1Id + ", " + player2Id + ")";
//    }
    private long playerId;
    private List<Integer> score;
    private int totalScore;


    public Player(long playerId, List<Integer> score, int totalScore) {
        this.playerId = playerId;
        this.score = score;
        this.totalScore = totalScore;
    }

    public long getPlayerId() { return playerId; }
    public List<Integer> getScore() { return score; }
    public int getTotalScore() { return totalScore; }

//    public void setScore(List<Integer> score) { this.score = score; }
//
//    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
}
