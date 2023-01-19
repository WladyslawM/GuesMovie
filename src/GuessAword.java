import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GuessAword {
    public static String word;

    public static void main(String [] args) throws FileNotFoundException{

        File file = new File("ListFilms.txt");
        Scanner scanner = new Scanner(file);

        int randomFilm = 1 + (int) (Math.random() * 25);

        for (int i = 0; i < randomFilm; i++){
            String line = scanner.nextLine();
            word = line;
        }

        int guessWrong = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("You are guessing:");

        int wordLenght = word.length();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordLenght; i++) {
            if (word.charAt(i) == ' ')
                stringBuilder.append(" ");
            else stringBuilder.append("_");
        }
        String maskWord = stringBuilder.toString();

        System.out.println(maskWord);

        do {
            System.out.print("Guess a letter: ");
            char c = input.next().charAt(0);
            if (word.toLowerCase().indexOf(c) >= 0) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == c ) {
                        maskWord = replaceLetter(Character.toString(c), maskWord);
                    }
                }
                System.out.println("You are guessing:" + maskWord);
                System.out.println("You have guessed (" + guessWrong + ") wrong letters: ");

                if (word.equals(maskWord)) {
                    System.out.println("You win!");
                    System.out.println("You have guessed '" + word + "' correctly.");
                }
            } else if (guessWrong == 10) {
                System.out.println("You lose!");
                break;
            } else if (word.toLowerCase().indexOf(c) < 0) {
                guessWrong++;
                System.out.println("You have guessed (" + guessWrong + ") wrong letters: ");
            }
        } while (maskWord.contains("_"));

    }

    private static String replaceLetter (String letter, String maskWord) throws FileNotFoundException{
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++){
            if (word.charAt(i) == letter.charAt(0)) {
                stringBuilder.append(letter);
            } else if (maskWord.charAt(i) != '_') {
                stringBuilder.append(maskWord.charAt(i));
            } else
                stringBuilder.append("_");
        }
        return stringBuilder.toString();
    }

}
