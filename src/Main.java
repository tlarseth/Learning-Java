import java.util.Scanner;

class HiLo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String playAgain;

        do {
            int theNumber = (int) (Math.random() * 100 + 1);
            int guess = 0;
            int numberOfTries = 0;

            while (guess != theNumber) {
                System.out.println("Guess a number between 1 and 100: ");
                guess = scan.nextInt();
                numberOfTries++;
                if (guess < theNumber) {
                    System.out.println(guess + " is too low. Try again.");
                } else if (guess > theNumber) {
                    System.out.println(guess + " is too high. Try again.");
                } else {
                    System.out.println(guess + " is correct. You Win!!!");
                }
            }

            System.out.println("It only took you " + numberOfTries + " tries.");
            System.out.println("Would you like to play again (Y/N)?");
            playAgain = scan.next();
        } while (playAgain.equalsIgnoreCase("y"));

        System.out.println("Thank you for playing! Goodbye.");
        scan.close();
    }
}