import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Random;

public class randomNumber {
    public static void main (String [] args) {
        UIManager.put("OptionPane.messageFont", new Font("Comic Sans MS", Font.BOLD, 4000));
        //UIManager.put("OptionPane.messageFont", new Font("Courier New", Font.PLAIN, 12));
        ArrayList<Integer> Guesses = new ArrayList<Integer>();
        ArrayList<Long> Times = new ArrayList<Long>();
        int playAgain = 1;

        while(playAgain == 1) {
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            UIManager.put("Button.background", Color.white);

            Random nextRandom = new Random();
            int randomNum = nextRandom.nextInt(1000) + 1;
            boolean playerCorrect = false;
            String keyboardInput;
            int playerGuess;
            int numPlayerGuesses = 0;
            long startTime = System.nanoTime();
            while (!playerCorrect) {
                numPlayerGuesses = numPlayerGuesses + 1;

                JOptionPane JOptionPane1 = new JOptionPane();
                keyboardInput = JOptionPane1.showInputDialog("Guess my number between 1 and 1000!");
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                playerGuess = Integer.parseInt(keyboardInput);

                if (playerGuess > randomNum) {
                    JOptionPane JOptionPane2 = new JOptionPane();
                    UIManager.put("OptionPane.background", Color.red);
                    UIManager.put("Panel.background", Color.red);
                    JOptionPane2.showMessageDialog(null, "You guessed too high, try again!");

                } else if (playerGuess < randomNum) {
                    JOptionPane JOptionPane3 = new JOptionPane();
                    UIManager.put("OptionPane.background", Color.blue);
                    UIManager.put("Panel.background", Color.blue);
                    JOptionPane3.showMessageDialog(null, "You guessed too low, try again!");

                } else {
                    JOptionPane JOptionPane4 = new JOptionPane();
                    long estimatedTime = System.nanoTime() - startTime;
                    UIManager.put("OptionPane.background", Color.green);
                    UIManager.put("Panel.background", Color.green);
                    estimatedTime = estimatedTime / 1000000000;
                    JOptionPane.showMessageDialog(null, "Congrats! you guessed THE NUMBER!!! it only took you " + numPlayerGuesses + " tries in " + estimatedTime + " seconds!");
                    Guesses.add(numPlayerGuesses);
                    Times.add(estimatedTime);
                    playerCorrect = true;

                    int selectedOption = JOptionPane.showConfirmDialog(null,
                            "Do you want to play again?",
                            "Play Again?",
                            JOptionPane.YES_NO_OPTION);
                    if (selectedOption == JOptionPane.NO_OPTION) {
                        String output = "Times   |  Guesses \n";
                        output+= "------------------\n";
                        for(int i = 0; i<Guesses.size(); i++){
                            String timesString = Times.get(i).toString();
                            String guessesString = Guesses.get(i).toString();

                            output += timesString;
                            for (int j = timesString.length(); j < 8; j++) {
                                output += " ";
                            }
                            output+= "| " + guessesString + "\n";
                            output+= "------------------\n";
                        }
                        JOptionPane.showMessageDialog(null, output);
                        playAgain = 0;
                    }
                }
            }
        }
        System.exit(0);
    }
} 