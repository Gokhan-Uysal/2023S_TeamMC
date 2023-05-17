package app.domain.models.entities;

public class UserEntity extends BaseEntity {
    public String username;
    public int highScore;

    public UserEntity(int id, String username, int highScore) {
        super(id);
        this.username = username;
        this.highScore = highScore;
    }
}
