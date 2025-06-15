package account.repository;

import account.entity.Account;
import account.service.AccountServiceImpl;

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

    private static final Map<Integer,Account> accountMap = new HashMap<Integer, Account>();

    @Override
    public int save(Account account) {
        System.out.println("계정 정보를 저장 하는거야? "+account);
        int accountUniqueId = (int) account.getId();
        accountMap.put(accountUniqueId, account);
        return accountUniqueId;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return Optional.ofNullable(accountMap.get(id));
    }

    /*
       Optional<Account>: 결과가 있을 수도, 없을 수도 있다는 걸 나타내는 Optional 타입.
       accountMap: 유저 정보를 담고 있는 Map (일종의 자료구조, 사전 같은 것)
       values(): Map 안의 값들만 가져옵니다 (Account 객체들)
       .stream(): 값을 하나씩 꺼내서 처리할 수 있게 스트림으로 바꿉니다
       .filter(...): 조건에 맞는 것만 골라냄.
       account -> ...: 람다식 (간단한 함수 표현)
       account.getUserId().equals(userId)
        => account라는 객체를 받아서 그 안의 getUserId() 결과가 userId랑 같은지 비교
        => 즉, 입력으로 받은 userId랑 같은 ID를 가진 Account만 선택하는 겁니다
        .findFirst(): 조건을 만족하는 첫 번째 Account 객체를 찾습니다.
                      못 찾으면 빈 값 (Optional.empty())을 반환합니다
     */
    @Override
    public Optional<Account> findByUserId(String userId) {
        return accountMap.values().stream()
                .filter(account -> account.getUserId().equals(userId))
                .findFirst();
    }

}
