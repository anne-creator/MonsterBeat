package backend;

import java.io.IOException;

/**
 * player class
 * (constructor)create a new player
 * update player
 */
public class User {
    private String email;
    private String name;
    private String password;
    private int level1HighestScore;
    private int level2HighestScore;
    private int level3HighestScore;

    /** constructor to create a new player without scores */
    public User(String email, String name, String password)  {
        this.email = email;
        this.name = name;
        this.password = password;
        this.level1HighestScore = 0;
        this.level2HighestScore = 0;
        this.level3HighestScore = 0;
    }

    /** constructor to create a new player with scores */
    public User(String email, String name, String password, int level1HighestScore, int level2HighestScore, int level3HighestScore) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.level1HighestScore = level1HighestScore;
        this.level2HighestScore = level2HighestScore;
        this.level3HighestScore = level3HighestScore;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", level1HighestScore=" + level1HighestScore +
            ", level2HighestScore=" + level2HighestScore +
            ", level3HighestScore=" + level3HighestScore +
            '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel1HighestScore() {
        return level1HighestScore;
    }

    public void setLevel1HighestScore(int level1HighestScore) {
        this.level1HighestScore = level1HighestScore;
    }

    public int getLevel2HighestScore() {
        return level2HighestScore;
    }

    public void setLevel2HighestScore(int level2HighestScore) {
        this.level2HighestScore = level2HighestScore;
    }

    public int getLevel3HighestScore() {
        return level3HighestScore;
    }

    public void setLevel3HighestScore(int level3HighestScore) {
        this.level3HighestScore = level3HighestScore;
    }

    /**
     * update one player's score with input of difficulty level
     * */
    public void updateHighestScore(int difficultyLevel, int newScore) throws IOException {
        if (difficultyLevel > 3 || difficultyLevel < 1) throw new IOException("invalid difficultyLevel");

        if (difficultyLevel == 1) {
            setLevel1HighestScore(Math.max(getLevel1HighestScore(), newScore));
        } else if (difficultyLevel == 2) {
            setLevel2HighestScore(Math.max(getLevel2HighestScore(), newScore));
        } else {
            setLevel3HighestScore(Math.max(getLevel3HighestScore(), newScore));
        }
    }
    // testing
//    public static void main(String[] args) throws IOException {
//        User p = new User("anneliu@gmail.com", "like", "12323", 1,2, 3);
//        System.out.println(p);
//    }
}
