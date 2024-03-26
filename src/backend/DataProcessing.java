/**
 * all interactions with database
 * all method in DataProcessing is static method which could be used directly
 *     using DataProcessing.[Method Name]
 *
 * Methods:
 *     addNewUser: append a new user into UserInfo.csv
 *     loadUserInfo: load UserInfo.csv, return an arrayList
 */
package backend;
import java.io.FileWriter;
import java.util.*;
import java.io.IOException;
public class DataProcessing {
    //input source file
    static String CSV_FILE = "src/database/UserInfo.csv";

    /**
     * append a user's data to CSV file.
     * @params a User
     * @throws IOException if there is an I/O error during saving
     */
    public static void addNewUser(User user){
        try (FileWriter fileWriter = new FileWriter(CSV_FILE, true)) {
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
        System.out.println("absolute path is: " + new File(CSV_FILE).getAbsolutePath() + "\n"); // print path
        ArrayList<User> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
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

//    public static void main(String[] args) {
//        DataProcessing.addPlayer(new User("like@gmail.com", "like", "123", 333, 444, 555 ));
//        DataProcessing.readData();
//        DataProcessing.addPlayer(new User("dislike@gmail.com", "dislike", "678", 456, 234, 123 ));
//        DataProcessing.readData();
//    }
}
