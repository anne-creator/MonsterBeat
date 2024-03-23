package backend;

/**
 * never really use this abstract class
 * declare Character and Monster specifically inside
 */
public abstract class Player {
    int livesLeft = 3;
    //need a img here
    boolean ifDied = false;

    public int getLivesLeft() {
        return livesLeft;
    }

    public boolean getIfDied() {
        return ifDied;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
    public void setIfDied(boolean ifDied) {
        this.ifDied = ifDied;
    }
    public void getAttacked(Player player) {
        System.out.println("getAttacked");
    }
    public void loseALife() {
        if (this.livesLeft > 0) this.livesLeft--;
        if (this.livesLeft == 0) this.ifDied = true;
    }
}

