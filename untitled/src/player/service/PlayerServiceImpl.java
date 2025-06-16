package player.service;

import account.service.AccountService;
import player.entity.Player;
import player.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    private static PlayerServiceImpl instance;

    private PlayerRepository playerRepository;
    private AccountService accountService;

    private PlayerServiceImpl() {}

    public static PlayerServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlayerServiceImpl();
        }
        return instance;
    }


    @Override
    public long register() {
        System.out.println("--------로그인--------");
        long playerId = accountService.signIn();
        return playerId;
    }
}
