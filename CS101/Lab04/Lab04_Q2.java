/**
 * Lab04_Q2
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab04_Q2 {
    public static void main(String[] args) {
        String numbers;

        boolean numWrong;

        numWrong = false;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter chart data:");
        numbers = scan.next();

        for (int count = 0; count < numbers.length(); count++) {

            if (!Character.isDigit(numbers.charAt(count))) {
                numWrong = true;
            }
        }

        if (!numWrong) {

            for (int count = 0; count < numbers.length(); count++) {
                System.out.printf("rewiev%d   ", count + 1);
            }
            System.out.println();

            for (int digit = 9; digit >= 1; digit--) {

                for (int count = 0; count < numbers.length(); count++) {

                    if (Character.getNumericValue(numbers.charAt(count)) >= digit) {
                        System.out.printf("%-10s", "***");
                    } else {
                        System.out.printf("%-10s", "");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Invalid chart data!");
        }
        scan.close();
    }
}
