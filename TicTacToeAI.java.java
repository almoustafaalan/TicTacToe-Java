import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeAI extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean playerTurn = true; // true = player (X), false = AI (O)

    public TicTacToeAI() {
        setTitle("Tic Tac Toe with AI");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (playerTurn && clickedButton.getText().equals("")) {
            clickedButton.setText("X");  // Player move
            playerTurn = false;

            if (checkWin("X")) {
                JOptionPane.showMessageDialog(this, "You Win!");
                resetGame();
                return;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a Draw!");
                resetGame();
                return;
            }

            // AI move
            aiMove();
        }
    }

    private void aiMove() {
        // Simple AI: pick first available spot
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                buttons[i].setText("O"); // AI move
                break;
            }
        }

        if (checkWin("O")) {
            JOptionPane.showMessageDialog(this, "AI Wins!");
            resetGame();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetGame();
        }

        playerTurn = true; // back to player
    }

    private boolean checkWin(String symbol) {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Cols
            {0, 4, 8}, {2, 4, 6}             // Diags
        };

        for (int[] condition : winConditions) {
            if (buttons[condition[0]].getText().equals(symbol) &&
                buttons[condition[1]].getText().equals(symbol) &&
                buttons[condition[2]].getText().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        playerTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToeAI();
    }
}
