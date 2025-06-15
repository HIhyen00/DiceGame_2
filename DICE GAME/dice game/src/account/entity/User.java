package account.entity;

public class User {
    private static long nextId = 1;

    private final long id;
    private String userId;
    private String password;
    private String nickname;

    public User(String userId, String password, String nickname) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4글자 이상이어야 합니다.");
        }

        this.id = nextId++;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}