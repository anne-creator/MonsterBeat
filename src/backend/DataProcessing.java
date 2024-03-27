/**
 * all interactions with database
 * all method in DataProcessing is static method which could be used directly
 *     using DataProcessing.[Method Name]
 *
 * Methods:
 *     addNewUser: append a new user into UserInfo.csv
 *     loadUserInfo: load UserInfo.csv, return an arrayList
 *     loadQuestionBank(int difficulty): load questionBank based on difficulty
 *
 * examples: (same with the user)
 *     ArrayList<Question> questionBank = DataProcessing.loadQuestionBank(1);
 *     for (Question question : questionBank) {
 *             System.out.println(question.questionString);
 *     }
 *
 */
package backend;
import java.io.*;
import java.util.*;

public class DataProcessing {
    // UserINfo file address
    static String UserInfo = "src/database/UserInfo.csv";

    // questionbank address with different levels
    static String questionBank1Address = "src/database/questionbank1.csv";
    static String questionBank2Address = "src/database/questionbank2.csv";
    static String questionBank3Address = "src/database/questionbank3.csv";

    /**
     * append a user's data to CSV file.
     * @params a User
     * @throws IOException if there is an I/O error during saving
     */
    public static void addNewUser(User user){
        try (FileWriter fileWriter = new FileWriter(UserInfo, true)) {
            String playerString = user.getEmail() + ',' + user.getName() + ',' + user.getPassword() + ',' + user.getLevel1HighestScore() + ',' + user.getLevel2HighestScore() + ',' + user.getLevel3HighestScore() + "\n";
            System.out.println(playerString);
            fileWriter.append(playerString);
            System.out.println("user read successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read UserInfo.csv
     * @return an Array list read from UserINfo.csv
     * @throws IOException if there is an I/O error during reading
     */
    public static ArrayList<User> loadUserInfo() {
        System.out.println("absolute path is: " + new File(UserInfo).getAbsolutePath() + "\n"); // print path
        ArrayList<User> players = new ArrayList<>();
        try (
            BufferedReader br = new BufferedReader(new FileReader(UserInfo))) {
            String line = br.readLine(); // Read header line
            while (line != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    User player = new User(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                    players.add(player);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // testing
//        for (User player : players) {
//                System.out.println(player);
//        }

        return players;
    }

    public static ArrayList<Question> loadQuestionBank(int difficulty) {
        //choose the question bank file based on difficulty
        String sourcefile;
        if (difficulty == 1) {
            sourcefile = questionBank1Address;
        }  else if (difficulty == 2) {
            sourcefile = questionBank2Address;
        } else {
            sourcefile = questionBank3Address;
        }

        // load the questionbank file to a arrayList
        ArrayList<Question> questionBank = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(new FileReader(sourcefile))) {
            String line = br.readLine(); // Read header line

            while (line != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Question question = new Question(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5]);
                    questionBank.add(question);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionBank;
    }
//    public static void main(String[] args) {
//        ArrayList<Question> questionBank = DataProcessing.loadQuestionBank(1);
//        for (Question question : questionBank) {
//                System.out.println(question.questionString);
//        }
//
//    }
}
