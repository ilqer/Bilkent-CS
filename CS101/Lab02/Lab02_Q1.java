/**
 * Lab02_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;



public class Lab02_Q1 {
    public static void main(String[] args) {
        
    

    double x;
    double y;

    Scanner scan = new Scanner(System.in);
    System.out.print("Enter a real number:");

    
    
    if(scan.hasNextDouble()){
        x = scan.nextDouble();

            if(x>15) {

                y = Math.sqrt(Math.pow(x,5)+1);
                System.out.printf("f(x) =%.2f ",y);

            }
    
            else if (x<0 ) {

                y = x/(x+10);
                System.out.printf("f(x) =%.2f ",y);

            }
    
            else if (x>=0 && x<=15){

                y = Math.exp(x)-15;
                System.out.printf("f(x) =%.2f ",y);

        
            }
    
        }    
    else {
        System.out.println("Invalid input - value must be numeric....");
    }
    scan.close();
    

    
    
   

}
}


    
















    

