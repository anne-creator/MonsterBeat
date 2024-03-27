package backend;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * start game: currently only has one monster
 */

public class Game {
    private ArrayList<Monster> monsters; // 2 monster for each level
    private Character character;
    private int difficultyLevel;
    private long gameDuration = 10 * 1000; // 1 min in millisecond
    private ArrayList<Question> questionBank;
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }
    public int getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public long getGameDuration() {
        return gameDuration;
    }
    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }
    public ArrayList<String> getQuestionBank() {
        return questionBank;
    }
    public void setQuestionBank(ArrayList<Question> questionBank) {
        this.questionBank = questionBank;
    }

    /** constructor
     *
     */
    public Game(Character character, ArrayList<Monster> monsters, int difficultyLevel) {
        this.monsters = loadMonsters(difficultyLevel);
        this.character = character;
        this.difficultyLevel = difficultyLevel;
        this.questionBank = DataProcessing.loadQuestionBank(difficultyLevel);
        Collections.shuffle(questionBank); //shuffle the question bank
    }
    public  void start() throws IOException {
        int questionCounter = 0; //track the question
        System.out.println("start game");
        long startTime = System.currentTimeMillis();
        System.out.println("start game at the time: " + startTime);

//        while (isRunning(startTime)) {
//            System.out.println("the game is running at the time: " + System.currentTimeMillis());
//            String question = questionBank.get(questionCounter);
//            attack(int questionCounter);
//            if (questionCounter++ >= questionBank.size()) throw new IOException("run out of the question");
//        }

        // when game ends, check if character has dead, calculate the marks and send data to frontend
        int mark = GameResult.calculateMarks(monsters, character, 0);
    }

//    private boolean isRunning(long startTime) {
//        long currentTime = System.currentTimeMillis();
//        return currentTime - startTime < gameDuration && !character.getIfDied() && !monster.getIfDied();
//    }

    private String getNextQuestionString(int questionCounter) {
        return questionBank.get(questionCounter).questionString;
    }
    public static void main(String[] args) throws IOException {
//        Character character1 = new Character();
//        Monster monster1 = new Monster();
//        Game game = new Game(character1, monsters,1);
//        game.start();
    }
}
