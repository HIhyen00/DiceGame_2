package account.service;

import account.entity.Account;
import account.repository.AccountRepository;
import account.repository.AccountRepositoryImpl;
import utility.KeyboardInput;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static AccountServiceImpl instance;

    private final AccountRepository accountRepository;

    private AccountServiceImpl() {
        this.accountRepository = AccountRepositoryImpl.getInstance();
    }

    public static AccountServiceImpl getInstance() {
        if( instance == null ) {
            instance = AccountServiceImpl.getInstance();
        }
        return instance;
    }

    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    public int register() {
        System.out.println("회원가입");
        while(true) {
            String userId = KeyboardInput.getStringInput("아이디를 입력하세요: ");
            String password = KeyboardInput.getStringInput("비밀번호를 입력하세요: ");

            if( userId.isEmpty() || password.isEmpty() ) {
                System.out.println("ID와 password가 입력되지 않았습니다. 다시 입력하세요.");
            } else if( password.length() < MIN_PASSWORD_LENGTH ) {
                System.out.println("비밀번호가 너무 짧습니다. 다시 입력하세요. (4글자 이상)");
            } else {
                Account account = new Account(userId, password);
                return accountRepository.save(account);
            }
        }
    }

    @Override
    public long signIn() {
        System.out.println("로그인");

        while(true) {
            String userId = KeyboardInput.getStringInput("ID : ");
            String password = KeyboardInput.getStringInput("password : ");

            Optional<Account> maybeAccount = accountRepository.findByUsername(userId);

            if(maybeAccount.isPresent()) {
                Account account = maybeAccount.get();
                if(account.getPassword().equals(password)) {
                    System.out.println("로그인 성공");
                    return account.getId();
                }
            }

            System.out.println("ID나 password를 잘못입력했습니다.");
        }

    }
}
