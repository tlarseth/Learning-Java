package funnyNameGame;

import java.io.IOException;
import java.util.Scanner;

public class Main {	
	
//  Method to clear screen on windows based systems	
	
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            ProcessBuilder pb;

            if (os.contains("Windows")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }

            pb.inheritIO();
            pb.start().waitFor();

        } catch (IOException e) {
            System.err.println("Error executing clear command: " + e.getMessage());
            for (int i = 0; i < 50; ++i) System.out.println();
        } catch (InterruptedException e) {
            System.err.println("Clear command interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            String userName;
            String[] splitName; //  Declaring an array that each element is a different word from user input
            boolean running = true;

            while (running) {
                clearScreen(); //  Calls the method to clear the screen if using Windows

                System.out.println("What's your first and last name? (Type 'exit' or 'quit' to end)");
                userName = input.nextLine();
                
//  Handling of ability of user to exit or quit the program
                
                if (userName.equalsIgnoreCase("exit") || userName.equalsIgnoreCase("quit")) {
                    System.out.println("Quitting already...guess I can't complain as 'This is the way.'");
                    running = false;
                    break;
                }

                String fullLowerCaseName = userName.toLowerCase(); //  Variable to put userName to all lower case so it will work even if letters are capital in the user input
                boolean specialCaseHandled = false;

//  Switch Case to handle Easter Egg names for the userName - Is called before the length validation as some of these are single word names                
                
                switch (fullLowerCaseName) {
                    case "din djarin":
                        System.out.println("This is the Way.");
                        specialCaseHandled = true;
                        break;
                    case "morticia addams":
                        System.out.println("Normal is an illusion. What is normal for the spider is chaos for the fly.");
                        specialCaseHandled = true;
                        break;
                    case "chica":
                        System.out.println("...because Chica loves pizza!");
                        specialCaseHandled = true;
                        break;
                    case "markiplier":
                        System.out.println("IS THAT THE BITE OF '87?!");
                        specialCaseHandled = true;
                        break;
                }

                if (!specialCaseHandled) {
                    splitName = userName.split(" ");

                    if (splitName.length >= 2) {
                        String firstName = splitName[0]; //  Sets the first name as the first word in userName
                        String lastName = splitName[splitName.length - 1]; //  Sets the last name as the last word in userName
                        System.out.println("Your new name is: " + lastName + ", " + firstName + ".  " + "That sounds better to me");
                    } else {
                        System.out.println("Are you that dumb? I asked you for your first AND last name, not one or the other");
                    }
                }

                System.out.println("\nPress Enter to try again...");
                input.nextLine();
            }
        }
    }
}