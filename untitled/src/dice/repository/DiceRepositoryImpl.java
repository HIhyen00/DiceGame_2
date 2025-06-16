package dice.repository;

import dice.entity.DiceGame;

import java.util.HashMap;
import java.util.Map;

public class DiceRepositoryImpl implements DiceRepository {
    private static DiceRepositoryImpl instance;

    private DiceRepositoryImpl() {}

    public static DiceRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceRepositoryImpl();
        }
        return instance;
    }
    private static final Map<Long, DiceGame> diceGameHashMap = new HashMap<>();

    @Override
    public long save(DiceGame game) {
        long gameId = game.getId();
        diceGameHashMap.put(gameId, game);
        return gameId;
    }
}
