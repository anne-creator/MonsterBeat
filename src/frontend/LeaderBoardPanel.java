package frontend;

import backend.LeaderBoard;
import backend.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class LeaderBoardPanel extends JPanel {
    private JTable leaderboardTable;
    private JScrollPane scrollPane;
    private JComboBox<String> difficultyComboBox;
    private String[] columnNames = {"Rank", "Email", "Score"};

    public LeaderBoardPanel() {
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
        leaderboardTable = new JTable(new DefaultTableModel(null, columnNames));
        styleTable(); // Apply custom styles to the table

        scrollPane = new JScrollPane(leaderboardTable);
        leaderboardTable.setFillsViewportHeight(true);

        // Wrapping the components in a JPanel for better control over their layout
        JPanel innerWrapperPanel = new JPanel(new BorderLayout());
        innerWrapperPanel.add(difficultyComboBox, BorderLayout.NORTH);
        innerWrapperPanel.add(scrollPane, BorderLayout.CENTER);

        // Customizing the wrapper panel
        innerWrapperPanel.setPreferredSize(new Dimension(600, 400)); // Set your desired size
        innerWrapperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border for the wrapper panel

        // GridBagConstraint customization
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // Add the inner wrapper panel to the LeaderBoardPanel using GridBagConstraints
        add(innerWrapperPanel, gbc);

        // Initially load the leaderboard with the Easy difficulty
        updateLeaderboardTable(1);
    }

    private void styleTable() {
        // Set the row height and custom renderers for the table
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setFont(new Font("Serif", Font.PLAIN, 20));
        leaderboardTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        leaderboardTable.getTableHeader().setBackground(new Color(34, 34, 34));
        leaderboardTable.getTableHeader().setForeground(new Color(255, 255, 255));
        leaderboardTable.setGridColor(new Color(200, 200, 200));
        leaderboardTable.setBackground(new Color(240, 240, 240));
        leaderboardTable.setForeground(new Color(50, 50, 50));

        // Center text in column header and cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        leaderboardTable.setDefaultRenderer(Object.class, centerRenderer);
        leaderboardTable.getTableHeader().setDefaultRenderer(centerRenderer);
    }

    private void updateLeaderboardTable(int difficultyLevel) {
        // Get the sorted list of users for the selected difficulty level
        ArrayList<User> users = LeaderBoard.getSortedList(difficultyLevel);

        // Create a new table model with the updated data
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

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        leaderboardTable.setModel(model);
        configureTableColumns();
    }

    private void configureTableColumns() {
        // Set the preferred widths for the columns
        int[] columnWidths = {50, 200, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            if (i < leaderboardTable.getColumnModel().getColumnCount()) {
                TableColumn column = leaderboardTable.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }
        }
    }
}
