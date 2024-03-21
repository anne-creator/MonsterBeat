package backend;

import java.io.*;
import java.util.*;

/**
 *
 * readData to a arrayList, addplayer to database, update player score to database
 * maybe a deletePlayer method later
 *
 * all method in DataProcessing is static method which could be used directly
 * using DataProcessing.methodname
 */
public class DataProcessing {
    //input source file
    static String CSV_FILE = "src/database/UserInfo.csv";

    /**
     * add a player's data to CSV file.
     *
     * @param player the player object to save
     * @throws IOException if there is an I/O error during saving
     */
    public static void addPlayer(Player player){
        try (FileWriter fileWriter = new FileWriter(CSV_FILE, true)) {
            String playerString = player.getEmail() + ',' + player.getName() + ',' + player.getPassword() + ',' + player.getLevel1HighestScore() + ',' + player.getLevel2HighestScore() + ',' + player.getLevel3HighestScore() + "\n";
            System.out.println(playerString);
            fileWriter.append(playerString);
            System.out.println("Data appended successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read database source to a list of Players
     *
     * @return a list of player source
     * catch IOException if there is an I/O error during reading
     */
    public static ArrayList<Player> readData() {
        System.out.println("absolute path is: " + new File(CSV_FILE).getAbsolutePath() + "\n"); // print path
        ArrayList<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine(); // Read header line
            while (line != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Player player = new Player(data[0], data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                    players.add(player);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // testing
//        for (Player player : players) {
//                System.out.println(player);
//        }

        return players;
    }

//    public static void main(String[] args) {
//        DataProcessing.addPlayer(new Player("like@gmail.com", "like", "123", 333, 444, 555 ));
//        DataProcessing.readData();
//        DataProcessing.addPlayer(new Player("dislike@gmail.com", "dislike", "678", 456, 234, 123 ));
//        DataProcessing.readData();
//    }
}
