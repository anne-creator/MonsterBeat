package backend;

import java.io.*;
import java.util.*;

/**
 *
 * readData to a arrayList, addplayer to database, update player score to input file
 */
public class DataProcessing {
    //input source file
    String CSV_FILE = "src/database/Database.csv";
    ArrayList<Player> players = new ArrayList<>();

    public DataProcessing() {

    }

    /**
     * add a player's data to CSV file.
     *
     * @param Player the player object to save
     * @throws IOException if there is an I/O error during saving
     */


    /**
     * read database source to a list of Players
     *
     * @return a list of player source
     * @throws IOException if there is an I/O error during reading
     */
    public ArrayList<Player> readData() throws IOException {
        System.out.println(new File(CSV_FILE).getAbsolutePath());
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
        for (Player player : players) {
                System.out.println(player);
        }

        return players;
    }

    public static void main(String[] args) throws IOException {
        DataProcessing data = new DataProcessing();
        data.readData();
    }
}
