package account.repository;

import account.entity.Account;

import java.util.Optional;

public interface AccountRepository {
    int save(Account account);
    Optional<Account> findByUsername(String userId);
}
