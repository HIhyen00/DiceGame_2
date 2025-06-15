package account.service;

import account.entity.Account;
import account.repository.AccountRepository;
import account.repository.AccountRepositoryImpl;
import util.KeyboardInput;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static AccountServiceImpl instance;
    private final AccountRepository accountRepository;
    public AccountServiceImpl() {
        this.accountRepository = AccountRepositoryImpl.getInstance();
    }
    public static AccountServiceImpl getInstance() {
        if(instance == null){
            instance = new AccountServiceImpl();
        }
        return instance;
    }

    private final int PASSWORD_MINIMUM_LENGTH = 4;

    @Override
    public int register() {
        System.out.println("회원 가입을 진행합니다!");
        String userId = KeyboardInput.getStringInput("아이디를 입력하세요: ");
        String password = KeyboardInput.getStringInput("비밀번호를 입력하세요: ");

        int passwordLength = password.length();
        while (passwordLength < PASSWORD_MINIMUM_LENGTH) {
            System.out.println("비밀 번호가 너무 짧습니다. 다시 작성해주세요.");
            password = KeyboardInput.getStringInput("비밀번호를 입력하세요: ");
            passwordLength = password.length();
        }

        Account account = new Account(userId, password);
        return accountRepository.save(account);
    }

    @Override
    public int signIn() {
        System.out.println("로그인을 진행합니다!");
        String userId = KeyboardInput.getStringInput("아이디를 입력하세요: ");
        String password = KeyboardInput.getStringInput("비밀번호를 입력하세요: ");

        //Optional<Account>: 결과가 있을 수도 있고, 없을 수도 있다는 뜻
        Optional<Account> maybeAccount = accountRepository.findByUserId(userId);

        while (maybeAccount.isEmpty()){
            System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다.");
            userId = KeyboardInput.getStringInput("아이디를 입력하세요: ");
            password = KeyboardInput.getStringInput("비밀번호를 입력하세요: ");

            maybeAccount = accountRepository.findByUserId(userId);
        }
        // .get()은 Optional 안에 진짜 값(예: Account) 이 들어 있을 때,
        //          그걸 꺼내서 사용하는 메서드입니다.
        Account account = maybeAccount.get();

        if(account.getPassword().equals(password)){
            System.out.println("로그인 성공");
            return (int) account.getId();
        }

        System.out.println("아이다 혹은 비밀번호를 잘못 입력했습니다.");
        return this.signIn();
    }
}
