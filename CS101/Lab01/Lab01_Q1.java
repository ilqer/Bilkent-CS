/**
 * Lab01_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;
import java.lang.Math;

public class Lab01_Q1 {

    public static void main(String[] args) {
        
        double x;
        double y;

        Scanner stdin = new Scanner(System.in);

        System.out.print("Enter x: ");
        x = stdin.nextInt();


        stdin.close();

        
        y = (Math.pow(x, 3) + 3*Math.abs(x) + 9 ) / Math.pow(x, 2);
        
        System.out.println("y:" + Math.round(y));

        System.out.printf("%.2f is between %.0f and %.0f",y,Math.floor(y),Math.ceil(y));



    

    }
}