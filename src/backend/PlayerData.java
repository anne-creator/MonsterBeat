package backend;

import java.io.*;
import java.util.*;

/**
 * Handles the storage and retrieval of player data in a CSV format.
 */
public class PlayerData {
    private static final String CSV_FILE = "players.csv";

    /**
     * Represents a player with a name, email, password, score, and the date of last save.
     */
    static class Player {
        String name;
        String email;
        String password; // Note: Storing passwords in plain text is a security risk
        int score;
        String lastSaved;

        /**
         * Constructs a new Player with the given details.
         *
         * @param name      the player's name
         * @param email     the player's email address
         * @param password  the player's password (not securely stored!)
         * @param score     the player's score
         * @param lastSaved the last saved date as a String
         */
        public Player(String name, String email, String password, int score, String lastSaved) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.score = score;
            this.lastSaved = lastSaved;
        }

        /**
         * Converts player data to a CSV-compliant string.
         *
         * @return a CSV-formatted string of player data
         */
        @Override
        public String toString() {
            return name + "," + email + "," + password + "," + score + "," + lastSaved;
        }
    }

    /**
     * Saves a player's data to a CSV file.
     *
     * @param player the player object to save
     * @throws IOException if there is an I/O error during saving
     */
    public static void savePlayer(Player player) throws IOException {
        File file = new File(CSV_FILE);
        boolean isNewFile = !file.exists();

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            if (isNewFile) {
                out.println("Name,Email,Password,Score,LastSaved"); // Write header if new file
            }
            out.println(player.toString());
        }
    }

    /**
     * Reads all players' data from the CSV file into a list of Player objects.
     *
     * @return a list of Player objects
     * @throws IOException if there is an I/O error during reading
     */
    public static List<Player> readPlayers() throws IOException {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine(); // Read header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Player player = new Player(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
                    players.add(player);
                }
            }
        }
        return players;
    }

    /**
     * Main method for demonstration and testing the functionality of the backend.PlayerData class.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            Player newPlayer = new Player("John Doe", "john@example.com", "password123", 100, "2024-03-19");
            savePlayer(newPlayer);

            List<Player> players = readPlayers();
            for (Player player : players) {
                System.out.println(player.name + " " + player.score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
