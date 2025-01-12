/**
 * Lab03_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */



import java.util.Random;
import java.util.Scanner;

public class Lab03_Q1_2 {
    public static void main(String[] args) {

        String word;
        String wordTwo;
        String relocate;
        String relocateTwo;
        String firstPart;
        String secondPart;
        String firstPartTwo;

        int x;
        int randomCharLoc;
        int count;
        int countTwo;

        char lastChar;

        Boolean close = false;

        count = 0;
        countTwo = 0;

        word = "";
        wordTwo = "";

        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        while (!close) {
            System.out.print("1)Encode Message \n2)Decode Message \n3)Encode2 \n4)Decode2 \n5)Quit \nEnter Choice:");
            x = scan.nextInt();
            scan.nextLine();

            if (x == 1) {
                System.out.print("Enter text:");
                word = scan.next();

                if (word.length() > 1) {
                    randomCharLoc = random.nextInt(word.length() - 1);
                    firstPart = word.substring(0, randomCharLoc + 1);
                    secondPart = word.substring(randomCharLoc + 1);
                    relocate = secondPart + " " + firstPart;
                    StringBuilder encWord = new StringBuilder(relocate);

                    while (count != word.length()) {
                        int randomNumber = random.nextInt(10); 
                        int numPlace = random.nextInt(word.length());
                        encWord.insert(numPlace, randomNumber);
                        count++;
                    }
                    count = 0;
                    System.out.println(encWord);
                } else {
                    System.out.println("Invalid length word - try again");
                }
            } else if (x == 2) {
                if (word.isEmpty()) {
                    System.out.println("No message to decode....");
                } else {
                    System.out.println("The decoded message is:" + word);
                }
            } else if (x == 3) {
                System.out.print("Enter text:");
                wordTwo = scan.nextLine();

                if (wordTwo.length() > 1) {
                    firstPartTwo = wordTwo.substring(0, wordTwo.length() - 1);
                    lastChar = wordTwo.charAt(wordTwo.length() - 1);
                    relocateTwo = lastChar + firstPartTwo;
                    firstPartTwo = relocateTwo.substring(0, relocateTwo.length() - 1);
                    lastChar = relocateTwo.charAt(relocateTwo.length() - 1);
                    relocateTwo = lastChar + firstPartTwo;
                    Scanner scanTwo = new Scanner(relocateTwo);

                    while (scanTwo.hasNext()) {
                        String part = scanTwo.next();
                        StringBuilder encWordTwo = new StringBuilder(part);

                        while (countTwo != part.length() - 1) {
                            encWordTwo.insert(2 * countTwo + 1, "*");
                            countTwo++;
                        }
                        countTwo = 0;
                        System.out.print(encWordTwo.toString() + " ");
                    }
                    scanTwo.close();
                    System.out.println();
                } else {
                    System.out.println("Invalid length word - try again");
                }
            } else if (x == 4) {

                if (wordTwo.isEmpty()) {
                    System.out.println("No message to decode....");
                } else {
                    System.out.println("The decoded message is:" + wordTwo);
                }
            } else if (x == 5) {
                System.out.println("Thank you, goodbye!");
                close = true;
            } else {
                System.out.println("Invalid choice - try again....");
            }
        }
        scan.close();
    }
}