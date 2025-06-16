package account.repository;

import account.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    int save(Account account);
    Optional<Account> findByUsername(String userId);
    Optional<Account> findById(long playerId);
}