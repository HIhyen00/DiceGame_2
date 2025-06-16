package dice.service;

import account.repository.AccountRepository;
import dice.entity.DiceGame;
import dice.repository.DiceRepository;
import player.entity.Player;
import player.repository.PlayerRepository;
import player.service.PlayerService;
import utility.KeyboardInput;

import java.util.*;

public class DiceServiceImpl implements DiceService {

    private static DiceServiceImpl instance;

    private AccountRepository accountRepository;
    private PlayerService playerService;
    private DiceRepository diceRepository;

    public DiceServiceImpl() {
        this.accountRepository = accountRepository;
        this.playerService = playerService;
        this.diceRepository = diceRepository;
    }

    public static DiceServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceServiceImpl();
        }
        return instance;
    }

    @Override
    public int rollDice () {
        Random random = new Random();
        int score = random.nextInt(6)+1;

        return score; //저장
    }

    @Override
    public long play() {
        long winner = 0L;
        boolean player1Skill = false;
        boolean player2Skill = false;
        List<Integer> player1Scores = new ArrayList<>();
        List<Integer> player2Scores = new ArrayList<>();

        long player1Id = playerService.register();
        String player1Name = accountRepository.findById(player1Id).get().getUserId();

        long player2Id = playerService.register();
        String player2Name = accountRepository.findById(player2Id).get().getUserId();

        // 일반 주사위 2번
        for (int i = 1; i <= 2; i++) {
            System.out.println("---" + i + " 번째 주사위..🎲---");

            KeyboardInput.getStringInput(player1Name + "님 주사위를 굴리세요! (→ enter)");
            int p1Score = rollDice();
            System.out.println("  : " + p1Score);
            player1Scores.add(p1Score);
            if (i == 1 && p1Score % 2 == 0) player1Skill = true;

            KeyboardInput.getStringInput(player2Name + "님 주사위를 굴리세요! (→ enter)");
            int p2Score = rollDice();
            System.out.println("  : " + p2Score);
            player2Scores.add(p2Score);
            if (i == 1 && p2Score % 2 == 0) player2Skill = true;
        }

        // 스킬 주사위
        if (player1Skill || player2Skill) {
            System.out.println("---세 번째 skill 주사위..🎲---");

            if (player1Skill) {
                KeyboardInput.getStringInput(player1Name + "님 주사위를 굴리세요! (→ enter)");
                int skillScore = rollDice();
                System.out.println("  : " + skillScore);
                player1Scores.add(skillScore);
                switch (skillScore) {
                    case 3 -> player1Scores.set(0, player1Scores.get(0) + sum(player2Scores));
                    case 4 -> player1Scores.clear();
                    default -> {}
                }
            }

            if (player2Skill) {
                KeyboardInput.getStringInput(player2Name + "님 주사위를 굴리세요! (→ enter)");
                int skillScore = rollDice();
                System.out.println("  : " + skillScore);
                player2Scores.add(skillScore);
                switch (skillScore) {
                    case 3 -> player2Scores.set(0, player2Scores.get(0) + sum(player1Scores));
                    case 4 -> player2Scores.clear();
                    default -> {}
                }
            }
        }

        int player1Total = sum(player1Scores);
        int player2Total = sum(player2Scores);

        if (player1Total > player2Total) {
            winner = player1Id;
        } else if (player2Total > player1Total) {
            winner = player2Id;
        }

//        printResultTable(player1Name, player2Name, player1Scores, player2Scores, player1Total, player2Total);

        Player player1 = new Player(player1Id, player1Scores, player1Total);
        Player player2 = new Player(player2Id, player2Scores, player2Total);
        DiceGame diceGame = new DiceGame(player1, player2, winner);
        return diceRepository.save(diceGame);
    }

    @Override
    public int sum(List<Integer> scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

//    @Override
//    public void printResultTable(String p1Name, String p2Name, List<Integer> p1Scores, List<Integer> p2Scores, int p1Total, int p2Total) {
//        System.out.println("\n===== 주사위 게임 결과 =====");
//        System.out.printf("%-5s%-15s%-15s%n", "주사위", p1Name, p2Name);
//        for (int i = 0; i < 2; i++) {
//            System.out.printf("%-5d%-15d%-15d%n", i + 1,
//                    p1Scores.size() > i ? p1Scores.get(i) : 0,
//                    p2Scores.size() > i ? p2Scores.get(i) : 0);
//        }
//
//        if (p1Scores.size() > 2 || p2Scores.size() > 2) {
//            System.out.printf("%-5d%-15s%-15s%n", 3,
//                    p1Scores.size() > 2 ? String.valueOf(p1Scores.get(2)) : "-",
//                    p2Scores.size() > 2 ? String.valueOf(p2Scores.get(2)) : "-");
//        }
//
//        System.out.println();
//        System.out.printf("Total Score → %s: %d, %s: %d%n", p1Name, p1Total, p2Name, p2Total);
//
//        if (p1Total > p2Total) {
//            System.out.println(p1Name + "님이 승리하였습니다!");
//        } else if (p2Total > p1Total) {
//            System.out.println(p2Name + "님이 승리하였습니다!");
//        } else {
//            System.out.println("무승부입니다!");
//        }
//    }
}
