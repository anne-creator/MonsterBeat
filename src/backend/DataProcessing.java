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
            String userString = user.getEmail() + ',' + user.getLevel1HighestScore() + ',' + user.getLevel2HighestScore() + ',' + user.getLevel3HighestScore() + "\n";
            System.out.println(userString);
            fileWriter.append(userString);
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
        ArrayList<User> players = new ArrayList<>();
        System.out.println("absolute path is: " + new File(UserInfo).getAbsolutePath() + "\n"); // print path
        try (
            BufferedReader br = new BufferedReader(new FileReader(UserInfo))) {
            String line = br.readLine(); // Read header line
            while (line != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    User player = new User(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
                    players.add(player);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    public static boolean findUserByEmail(String email) {
        ArrayList<User> users = DataProcessing.loadUserInfo();
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
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
                    String[] optionList = new String[4];
                    optionList[0] = data[2];
                    optionList[1] = data[3];
                    optionList[2] = data[4];
                    optionList[3] = data[5];
                    Question question = new Question(Integer.parseInt(data[0]), data[1], optionList);
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
//        Boolean result = DataProcessing.findUserByEmail("ron@gmail.com");
//
//    }
}
