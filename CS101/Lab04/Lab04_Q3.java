/**
 * Lab04_Q3
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab04_Q3 {
    public static void main(String[] args) {

        String convertedWord;
        String word;

        int equal;

        boolean quit;

        convertedWord = "";

        equal = 0;

        quit = false;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.print("Enter word to convert:");
            word = scan.next();

            if (word.compareTo("exit")==0){ 
                quit = true;
            } 
            else{

                for (int count = 0; count < word.length(); count++) {
                    char c = word.charAt(count);

                    if (!Character.isLetter(c)) {
                        convertedWord += "*";
                    } else {

                        for (int countTwo = 0; countTwo < word.length(); countTwo++) {

                            if (word.charAt(countTwo) == c) {
                                equal++;
                            }
                        }

                        if (equal > 1) {
                            convertedWord += ")";
                            equal = 0;
                        } else {
                            convertedWord += "(";
                            equal = 0;
                        }
                    }
                }
                System.out.printf("Original word:%s Converted Word:%s", word, convertedWord);
                System.out.println();
            }
            convertedWord = "";
        } while (!quit);
        scan.close();
    }
}
