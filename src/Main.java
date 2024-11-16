import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class HiLoGUI {

    private JFrame frame;
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton;
    private JButton playAgainButton;
    private int theNumber;
    private int numberOfTries;

    public HiLoGUI() {
        initialize();
        newGame();
    }

    private void initialize() {
        frame = new JFrame("Hi-Lo Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        promptLabel.setBounds(20, 20, 250, 25);
        frame.add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(240, 20, 120, 25);
        frame.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(20, 60, 100, 25);
        frame.add(guessButton);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(240, 60, 120, 25);
        frame.add(playAgainButton);

        messageLabel = new JLabel("Enter your guess above");
        messageLabel.setBounds(20, 100, 340, 25);
        frame.add(messageLabel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        playAgainButton.setEnabled(false);
    }

    private void newGame() {
        Random rand = new Random();
        theNumber = rand.nextInt(100) + 1;
        numberOfTries = 0;
        guessField.setText("");
        messageLabel.setText("Enter your guess above");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            numberOfTries++;
            if (guess < theNumber) {
                messageLabel.setText(guess + " is too low. Try again.");
            } else if (guess > theNumber) {
                messageLabel.setText(guess + " is too high. Try again.");
            } else {
                messageLabel.setText(guess + " is correct! You win in " + numberOfTries + " tries.");
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HiLoGUI window = new HiLoGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}