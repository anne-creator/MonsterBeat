package backend;

import java.io.*;
import java.util.*;

/**
 * Handles the storage and retrieval of player data in a CSV format.
 */
public class DataPreProcessing {
    //input source file
    private static final String CSV_FILE = "../database/Database.csv";

    /**
     * Saves a player's data to a CSV file.
     *
     * @param player the player object to save
     * @throws IOException if there is an I/O error during saving

    /**
     * load database source to a list
     *
     * @return a list of player source
     * @throws IOException if there is an I/O error during reading
     */
    public static ArrayListList<Player> readData() throws IOException {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine(); // Read header line
            while ((line) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Player player = new Player(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
                    players.add(player);
                }
            }
        }
        return players;
    }
}
