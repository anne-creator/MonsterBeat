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
    public ArrayList<Monster> monsters; // 2 monster for each level
    public Character character;
    public Monster monster1; //first monster
    public Monster monster2; // second monster
    public int difficultyLevel;
    public ArrayList<Question> questionBank;
    int questionCounter = 0;
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
    public ArrayList<Question> getQuestionBank() {
        return questionBank;
    }
    public void setQuestionBank(ArrayList<Question> questionBank) {
        this.questionBank = questionBank;
    }

    /** constructor
     *
     */
    public Game(String UserEmail, int difficultyLevel) {
        System.out.println("difficultyLevel is : " + difficultyLevel);
        this.monsters = generateMonsters(difficultyLevel);
        monster1 = monsters.get(0);
        monster2 = monsters.get(1);
        this.character = generateCharacter(difficultyLevel);
        this.difficultyLevel = difficultyLevel;
        this.questionBank = DataProcessing.loadQuestionBank(difficultyLevel);
//        Collections.shuffle(questionBank); //shuffle the question bank
    }

//    private Question getNextQuestionString(int questionCounter) {
//        return questionBank.get(questionCounter).questionString;
//    }
    public Question generateQuestion() {
        if (this.questionCounter >= this.questionBank.size()) {
            //end game
            return null;
        }
        return this.questionBank.get(questionCounter++);
    }
    public String answerWrong(){
        character.loseALife();
        System.out.println("answerWong invoked");
        System.out.println("now character" +
            "s life is " + character.getLivesLeft());
        if (character.ifDied) return "game end";
        else return "c1- change question";
    }
    public String answerRight() {
        // monster1 already died
        if (monster1.getIfDied()) {
            monster2.loseALife();
            if (monster2.getIfDied()) return "game end";
            return "m2- change question";
        } else { // monster 1 not died
            monster1.loseALife();
            if (monster1.ifDied) return "change monster and question"; // also needs update question
            return "m1- change question";
        }

    }

    public int calculateMarks(int timeLeft) {
        GameResult result = new GameResult();
        // if character survive
        if (!character.getIfDied()) {
            if (timeLeft > 0) { // if we still have time left, we killed all monsters and get 6 question right
                return timeLeft * 5 + character.livesLeft * 50 + 6 * 30;
            } else { // no time left, we check monsters to get how many questions do I get it right
                int questionAnswered = calculateQuestions(monsters);
                return character.livesLeft * 50 + questionAnswered * 30;
            }
        } else { // character died
            int questionAnswered = calculateQuestions(monsters);
            return questionAnswered * 30 - timeLeft;
        }
    }

    public int calculateQuestions(ArrayList<Monster> monsters) {
        int result = 0;
        Monster m1 = monsters.get(0);
        if (m1.getIfDied()) result += 3;
        else return 3 - m1.getLivesLeft();

        Monster m2 = monsters.get(1); //get second monster if first monster died
        result += 3 - m2.getLivesLeft();
        return result;
    }

    private ArrayList<Monster> generateMonsters(int difficultyLevel) {
        ArrayList<Monster> monsterList = new ArrayList<>(2);
        Monster monster1 = new Monster();
        Monster monster2 = new Monster();
        if (difficultyLevel == 1) {
            monster1.setImg("m1.png");
            monster2.setImg("m2.png");
        } else if (difficultyLevel == 2) {
            monster1.setImg("m3.png");
            monster2.setImg("m4.png");
        } else {
            monster1.setImg("m5.png");
            monster2.setImg("m6.png");
        }

        monsterList.add(monster1);
        monsterList.add(monster2);
        return monsterList;
    }

    private Character generateCharacter(int difficultyLevel) {
        Character character = new Character();
        if (difficultyLevel == 1) {
            character.setImg("c1");
        } else if (difficultyLevel == 2) {
            character.setImg("c2");
        } else {
            character.setImg("c3");
        }

        return character;
    }
//    public static void main(String[] args) throws IOException {
//        Character character1 = new Character();
//        Monster monster1 = new Monster();
//        Game game = new Game(character1, monsters,1);
//        game.start();
//    }
}
