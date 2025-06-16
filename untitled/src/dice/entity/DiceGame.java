package dice.entity;

import player.entity.Player;

public class DiceGame {
    private static long idCounter = 1;

    private long id;
    private long winner;
    private Player player1;
    private Player player2;

    public DiceGame(Player player1, Player player2, long winner) {
        this.id = idCounter++;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public long getId() { return id; }
}
