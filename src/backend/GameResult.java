package backend;

import java.util.ArrayList;

/**
 * Useage: GameResult.calculateMarks(monster, player, timeLeft)
 */
public class GameResult {

    public static int calculateMarks(ArrayList<Monster> monsters, Character character, int timeLeft) {
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
}
