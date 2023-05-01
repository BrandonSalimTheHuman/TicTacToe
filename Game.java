package TicTacToe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener{
    // Declare variables, so I can use them in the constructor and methods
    JFrame frame;
    JButton[][] grid;
    JPanel panel;
    JLabel label;
    Font font;
    Font font2;
    String current_player;
    JButton clicked_button;
    boolean game_over_by_win;

    public Game(){
        // Create new frame
        frame = new JFrame();

        // Create grid, a 2D array for 3 rows of 3 buttons
        grid = new JButton[3][3];

        // Create a panel that will be used to add all 9 buttons as one object
        panel = new JPanel(new GridLayout(3, 3));

        // Create font for player icons
        font = new Font("Arial", Font.BOLD,125);

        // Create a label to show text
        label = new JLabel();

        // Create font for the label, then apply the font
        font2 = new Font("Georgia", Font.BOLD, 40);
        label.setFont(font2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                // Create button
                grid[i][j] = new JButton();
                // Apply font
                grid[i][j].setFont(font);
                // Add an event listener to detect user clicks
                grid[i][j].addActionListener(this);
                // Add button to panel
                panel.add(grid[i][j]);
            }
        }

        // Initialize variable to false
        game_over_by_win = false;

        // Define starting player
        current_player = "X";

        // Define starting text
        label.setText("Current player: " + current_player);

        // Center align the label horizontally
        label.setHorizontalAlignment(JLabel.CENTER);

        // Add panel, label and their alignments to frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(label, BorderLayout.NORTH);

        // Initialize frame size, then center it on screen
        frame.setSize(800, 900);
        frame.setLocationRelativeTo(null);

        // Make frame visible
        frame.setVisible(true);

        // Automatically terminates the program when the close button is clicked on the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Overriding the method from ActionListener
    public void actionPerformed(ActionEvent event) {
        // Assign the button that was clicked into clicked_button
        clicked_button = (JButton) event.getSource();
        // Make sure the button is an empty button and that the game is still not over
        if (!clicked_button.getText().equals("X") && !clicked_button.getText().equals("O") && !game_over()){
            // Apply red text color to X and blue text color to O
            if (current_player.equals("X")){
                clicked_button.setForeground(Color.RED);
            }
            else {
                clicked_button.setForeground(Color.BLUE);
            }

            // Show the player on the button
            clicked_button.setText(current_player);

            // Check if the move ended the game
            if (game_over()){
                // Check if the game ended because of a win
                if (game_over_by_win){
                    label.setText(current_player + " wins!");

                }
                // If it didn't, then it ended because of a tie
                else {
                    label.setText("It's a tie.");
                }
            }
            // If the game didn't end, then continue the game and change the current player
            else {
                if (current_player.equals("X")){
                    current_player = "O";
                }
                else {
                    current_player = "X";
                }

                // Change the text on the label
                label.setText("Current player: " + current_player);
            }
        }
    }

    public boolean game_over(){
        // Check for wins
        if (check_columns() || check_rows() || check_diagonals()){
            game_over_by_win = true;
            return true;
        }

        // If there's no win, the return statement relies only on whether the board is full
        return check_board_full();
    }

    public boolean check_columns(){
        // i is the column
        for (int i = 0; i < 3; i++) {
            if (grid[0][i].getText().equals(current_player) &&
                    grid[1][i].getText().equals(current_player) &&
                    grid[2][i].getText().equals(current_player)){
                game_over_by_win = true;
                return true;
            }
        }
        return false;
    }

    public boolean check_rows(){
        // i is the row
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].getText().equals(current_player) &&
                    grid[i][1].getText().equals(current_player) &&
                    grid[i][2].getText().equals(current_player)){
                game_over_by_win = true;
                return true;
            }
        }
        return false;
    }

    public boolean check_diagonals(){
        // these are the two ways a diagonal win can happen
        if (grid[0][0].getText().equals(current_player) &&
                grid[1][1].getText().equals(current_player) &&
                grid[2][2].getText().equals(current_player)){
            game_over_by_win = true;
            return true;
        }
        if (grid[0][2].getText().equals(current_player) &&
                grid[1][1].getText().equals(current_player) &&
                grid[2][0].getText().equals(current_player)){
            game_over_by_win = true;
            return true;
        }
        return false;
    }

    public boolean check_board_full(){
        // loop through every button and make sure it isn't occupied by a player
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if (!grid[i][j].getText().equals("X") && !grid[i][j].getText().equals("O")){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Game game = new Game();
    }
}