package account.repository;

import account.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private static AccountRepositoryImpl instance;

    private AccountRepositoryImpl() {}

    public static AccountRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new AccountRepositoryImpl();
        }
        return instance;
    }

    private static final Map<Integer, Account> accountHashMap = new HashMap<>();

    @Override
    public int save(Account account) {
        System.out.println("Saving account: " + account);
        int accountUniqueId = account.getId();
        accountHashMap.put(accountUniqueId, account);
        return accountUniqueId;
    }

    @Override
    public Optional<Account> findByUsername(String userId) {
        return accountHashMap.values().stream()
                .filter(account -> account.getUserId().equals(userId))
                .findFirst();
    }

}
