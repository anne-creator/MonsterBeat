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
    private Monster monster;
    private Character character;
    private int difficultyLevel;
    private long gameDuration = 10 * 1000; // 1 min in millisecond
    private ArrayList<String> questionBank;
    public Monster getMonster() {
        return monster;
    }
    public void setMonster(Monster monster) {
        this.monster = monster;
    }
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
    public void setQuestionBank(ArrayList<String> questionBank) {
        this.questionBank = questionBank;
    }

    /** constructor
     *
     * @param character
     * @param monster
     * @param difficultyLevel
     */
    public Game(Character character, Monster monster, int difficultyLevel) {
        this.monster = monster;
        this.character = character;
        this.difficultyLevel = difficultyLevel;
//      this.questionBank = loadQuestionBank(difficultyLevel);

        //testing demo for loading the question bank
        ArrayList<String> questionBank = new ArrayList<String>();
        questionBank.add("question 1");
        questionBank.add("question 1");
        questionBank.add("question 1");
        questionBank.add("question 1");
        questionBank.add("question 1");
        this.questionBank = questionBank;
        Collections.shuffle(questionBank); //shuffle the question bank
        System.out.println(questionBank);

    }
    public  void start() throws IOException {
        int questionCounter = 0; //track the question
        System.out.println("start game");
        long startTime = System.currentTimeMillis();
        System.out.println("start game at the time: " + startTime);

        while (isRunning(startTime)) {
            System.out.println("the game is running at the time: " + System.currentTimeMillis());
            String question = questionBank.get(questionCounter);
            attack("12 + 5 = ?");
            if (questionCounter++ >= questionBank.size()) throw new IOException("run out of the question");
        }

        // when game ends, check if character has dead, calculate the marks and send data to frontend
        int mark = GameResult.calculateMarks(monster, character, 0);

        System.out.println("game ends and you have get: " + mark + " marks");

        System.out.println("the project stopped at: " + System.currentTimeMillis());
    }

    private boolean isRunning(long startTime) {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime < gameDuration && !character.getIfDied() && !monster.getIfDied();
    }

    private void attack(String question) {
        // need to deal with async input from front end and dealt with run out of time situation
        // at lease check time once a second
        // send question input to frontend and get the answer
        int answer = 1; //get answer from user input


    }
    public static void main(String[] args) throws IOException {
        Character character1 = new Character();
        Monster monster1 = new Monster();
        Game game = new Game(character1, monster1,1);
        game.start();
    }
}
