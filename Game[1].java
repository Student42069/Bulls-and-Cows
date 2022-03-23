package bullscows;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
    String code;
    int len;
    int possible;

    Game(int len, int possible){
        this.len = len;
        this.possible = possible;
        this.code = randomCode();
        System.out.println(code);
        prepared();
    }

    void prepared() {
        String stars = "";
        for (int i = 0; i < this.len; i++) {
            stars += "*";
        }
        if (this.possible <= 10) {
            stars += " (0-" + String.valueOf(this.possible-1) + ").";
        } else if (this.possible == 11){
            stars += " (0-9, a).";
        } else {
            stars += " (0-9, a-" + String.valueOf((char) (this.possible + 86)) + ").";
        }
        System.out.println("The secret is prepared: " + stars);
        System.out.println("Okay, let's start a game!");
    }

    int grade(String guess) {
        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < this.len; i++) {
            if (guess.charAt(i) == this.code.charAt(i)) {
                bulls++;
            } else if (this.code.contains(Character.toString(guess.charAt(i)))) {
                cows++;
            }
        }

        if (cows > 0 && bulls > 0){
            System.out.printf("Grade: %d bull(s) and %d cow(s)%n", bulls, cows);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cow(s)%n", cows);
        } else if (bulls >0) {
            System.out.printf("Grade: %d bull(s)%n", bulls);
        } else {
            System.out.printf("Grade: None%n");
        }
        return bulls;
    }

    private String randomCode(){
        String code  = "";
        String[] caracters = new String[36];
        for (int i = 0; i < 10; i++) {
            caracters[i] = String.valueOf(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            caracters[(int) i - 87] = String.valueOf(i);
        }

        for (int i = 0; i < len; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, possible);
            while (code.contains(String.valueOf(caracters[random]))) {
                random = ThreadLocalRandom.current().nextInt(0, possible);
            }
            code += caracters[random];
        }
        return code;
    }
}
