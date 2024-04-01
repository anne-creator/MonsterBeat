package frontend;

import backend.LeaderBoard;
import backend.User;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LeaderBoardPanel extends JPanel {
    private JTable leaderboardTable;
    private JScrollPane scrollPane;
    private JComboBox<String> difficultyComboBox;
    private JButton backButton;
    private String[] columnNames = {"Rank", "Email", "Score"};
    private String userEmail; // Variable to store user's email

    public LeaderBoardPanel(MainApplication mainApp, String userEmail) {
        this.userEmail = userEmail;
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible positioning of components
        GridBagConstraints gbc = new GridBagConstraints();

        // Dropdown for selecting difficulty level
        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficulties);
        difficultyComboBox.addActionListener(e -> updateLeaderboardTable(difficultyComboBox.getSelectedIndex() + 1));
        difficultyComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        difficultyComboBox.setBackground(new Color(255, 255, 255));
        difficultyComboBox.setForeground(new Color(50, 50, 50));

        // Initialize JTable with DefaultTableModel
        leaderboardTable = new JTable(new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells uneditable
                return false;
            }
        });
        styleTable(); // Apply custom styles to the table

        scrollPane = new JScrollPane(leaderboardTable);
        leaderboardTable.setFillsViewportHeight(true);

        // Back Button to return to the previous menu
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
        	try { sfx("soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }
        	
            try {
                mainApp.switchToMenuPanel(userEmail); // Use stored userEmail to switch panels
            } catch (IOException ex) {
                ex.printStackTrace(); // Replace with proper error handling
            }
        });

        // Panel for the leaderboard and dropdown
        JPanel innerWrapperPanel = new JPanel(new BorderLayout());
        innerWrapperPanel.add(difficultyComboBox, BorderLayout.NORTH);
        innerWrapperPanel.add(scrollPane, BorderLayout.CENTER);

        // Set a preferred size for innerWrapperPanel if you want to make it smaller or bigger
        innerWrapperPanel.setPreferredSize(new Dimension(600, 400));
        innerWrapperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border for the wrapper panel

        // Add the back button to the bottom of the panel
        innerWrapperPanel.add(backButton, BorderLayout.SOUTH);

        // Configure GridBagConstraints for the inner panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Add innerWrapperPanel to the LeaderBoardPanel using GridBagConstraints
        add(innerWrapperPanel, gbc);

        // Load the leaderboard with the Easy difficulty initially
        updateLeaderboardTable(1);
    }

    private void styleTable() {
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setFont(new Font("Serif", Font.PLAIN, 20));
        leaderboardTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        leaderboardTable.getTableHeader().setBackground(new Color(34, 34, 34));
        leaderboardTable.getTableHeader().setForeground(new Color(255, 255, 255));
        leaderboardTable.setGridColor(new Color(200, 200, 200));
        leaderboardTable.setBackground(new Color(240, 240, 240));
        leaderboardTable.setForeground(new Color(50, 50, 50));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        leaderboardTable.setDefaultRenderer(Object.class, centerRenderer);
        leaderboardTable.getTableHeader().setDefaultRenderer(centerRenderer);
    }

    private void updateLeaderboardTable(int difficultyLevel) {
        ArrayList<User> users = LeaderBoard.getSortedList(difficultyLevel);
        Object[][] data = new Object[users.size()][3];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = i + 1; // Rank
            data[i][1] = user.getEmail();
            data[i][2] = switch (difficultyLevel) {
                case 1 -> user.getLevel1HighestScore();
                case 2 -> user.getLevel2HighestScore();
                case 3 -> user.getLevel3HighestScore();
                default -> 0;
            };
        }

        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();
        model.setDataVector(data, columnNames);
        configureTableColumns();
    }

    private void configureTableColumns() {
        int[] columnWidths = {50, 200, 100};
        TableColumn column;
        for (int i = 0; i < columnWidths.length; i++) {
            if (i < leaderboardTable.getColumnModel().getColumnCount()) {
                column = leaderboardTable.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }
        }
    }
    
    public void sfx(String filename) throws IOException {
    	Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
} // class end
