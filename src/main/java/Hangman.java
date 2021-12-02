import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static final Integer ALLOWED_ERRORS = 8;

    private final String word;
    private List<String> wordLetters;
    private final List<String> inputs = new ArrayList<>();
    private Integer errorCount = 0;


    public Hangman(String word) {
        this.word = word;
    }

    public void startHangman() {

        printWordLength();

        this.wordLetters = getWordLetters();

        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            askForLetter();
            String input = scanner.next();
            if (isInputValid(input)) {
                this.inputs.add(input);
                System.out.println(getPrintResult());
                gameOver = checkForGameOver(input) || checkForSuccess();
            }
        }
        scanner.close();
    }

    private void askForLetter() {
        System.out.println("Pleas type one letter?");
    }

    private void printWordLength() {
        System.out.println(String.format("Word has %s letters", this.word.length()));
    }

    private List<String> getWordLetters() {
        List<String> letters = new ArrayList<>();
        for (String letter : this.word.split("")
        ) {
            letters.add(letter.toLowerCase());
        }
        return letters;
    }

    private boolean isInputValid(String input) {
        if (input.length() > 1) {
            System.out.println(String.format("input %s longer then one letter", input));
            return false;
        }
        if (inputs.contains(input.toLowerCase())) {
            System.out.println(String.format("Char %s already exist", input));
            return false;
        }
        return true;
    }

    private boolean checkForGameOver(String input) {
        if (!wordLetters.contains(input.toLowerCase())) {
            this.errorCount++;
            System.out.println(String.format("Letter %s not in searched word. %s tries left", input, (ALLOWED_ERRORS - this.errorCount)));
            if (this.errorCount >= ALLOWED_ERRORS) {
                System.out.println("To much tries. Game Over!");
                return true;
            }
        }
        return false;
    }

    private boolean checkForSuccess() {
        int successLetters = 0;
        for (String letter : this.wordLetters
        ) {
            if (this.inputs.contains(letter.toLowerCase())) {
                successLetters++;
            }
        }
        return successLetters == this.wordLetters.size();
    }

    private String getPrintResult() {
        StringBuilder stringBuilder = new StringBuilder("Result: ");
        for (String letter : this.wordLetters
        ) {
            if (this.inputs.contains(letter.toLowerCase())) {
                stringBuilder.append(" " + letter);
            } else {
                stringBuilder.append(" _");
            }
        }
        return stringBuilder.toString();
    }

}
