package player.repository;

import player.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlayerRepositoryImpl implements PlayerRepository {
    private static PlayerRepositoryImpl instance;

    private PlayerRepositoryImpl() {
    }

    public static PlayerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PlayerRepositoryImpl();
        }
        return instance;
    }
}


