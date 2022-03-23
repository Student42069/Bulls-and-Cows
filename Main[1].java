package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String guess;
        int bulls = 0;
        int counter = 1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");

        if (!scanner.hasNextInt()){
            System.out.printf("Error: \"%s\" isn't a valid number.", scanner.nextLine());
            System.exit(1);
        }
        int len = scanner.nextInt();
        if (len==0) {
            System.out.println("Error: the length can't be 0.");
            System.exit(2);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int possible = scanner.nextInt();
        scanner.nextLine();

        if (possible < len) {
            System.out.printf("Error: it's not possible to generate a code with " +
                    "a length of %d with %d unique symbols.", len, possible);
            System.exit(1);
        }

        if (possible > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else if (len > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", len);
            System.exit(0);
        }

        Game game = new Game(len, possible);

//        game.prepared();

        while (bulls != len) {
            System.out.println("Turn " + counter++ + ":");
            guess = scanner.nextLine();
            bulls = game.grade(guess);
        }

        System.out.println("Congratulations! You guessed the secret code.");

        scanner.close();
    }
}