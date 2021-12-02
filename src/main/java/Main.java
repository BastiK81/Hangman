public class Main {

    public static void main(String[] args) {

        String checkWord;
        checkWord = "JavaBootCamp";

        Hangman hangman = new Hangman(checkWord);
        hangman.startHangman();

    }
}
