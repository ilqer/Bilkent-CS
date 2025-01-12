/**
 * Lab04_Q3_2
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab04_Q3_2 {
    public static void main(String[] args) {

        String word;
        String convertedWord;

        int difference;
        int newLetter;

        char special;

        boolean wordWrong;

        wordWrong = false;

        convertedWord = "";

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter word to convert:");
        word = scan.next();

        for (int count = 0; count < word.length(); count++) {

            if (!Character.isLowerCase(word.charAt(count))) {
                wordWrong = true;
            }
        }

        if (!wordWrong){
            System.out.println("Length:" + word.length());

            if (word.length() % 2 == 0) {
                special = word.charAt(0);
            } else {
                special = word.charAt((word.length() - 1) / 2);
            }
            System.out.println("Special letter:" + special);

            for (int count = 0; count < word.length(); count++) {

                if (word.charAt(count) == special) {
                    convertedWord += "(";
                } else {
                    difference = special - 'a';
                    newLetter = Math.abs(difference) + word.charAt(count);

                    if (newLetter > 122) {
                        newLetter = newLetter - 26;
                    }
                    convertedWord += Character.toString((char) newLetter);
                }
            }
            System.out.println("Converted word:" + convertedWord);
        } else {
            System.out.println("Invalid input! Only enter lowercase letters.");
        }
        scan.close();
    }
}
