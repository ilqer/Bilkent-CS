/**
 * Lab04_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab04_Q1 {
    public static void main(String[] args) {

        int min;
        int max;

        double probability;

        boolean numWrong;

        probability = 1;

        numWrong = false;

        Scanner scan = new Scanner(System.in);

        do {

            if (numWrong) {
                System.out.println("Invalid input - minimum must be less than maximum...");
            }
            System.out.print("Enter the minimum and maximum number of people:");
            min = scan.nextInt();
            max = scan.nextInt();
            numWrong = true;
        } while (max <= min);
        numWrong = false;
        System.out.printf("%-20s%-20s","NUMBER OF PEOPLE"  ,   "PROBABILITY");
        System.out.println();

        for (int count = min; count <= max; count++) {

            for (int countTwo = 0; countTwo <= count - 1; countTwo++) {
                probability = probability * (365 - countTwo) / 365;
            }
            System.out.printf("%-20d%-20.3f", count, 1 - probability);
            System.out.println();
            probability = 1;
        }
        scan.close();
    }
}
