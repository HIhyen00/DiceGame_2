package dice.repository;

import dice.entity.DiceGame;

public interface DiceRepository {
    long save(DiceGame game);
}
