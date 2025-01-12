/**
 * Lab02_Q
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;

public class Lab02_Q2 {
    public static void main(String[] args) {
        
        int x;
        int y;
        int z;
        int t;


        
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the number of small jars available:");
        x = scan.nextInt();
        System.out.print("Input the number of large jars available:");
        y = scan.nextInt();
        System.out.print("Input the order size:");
        z = scan.nextInt();
        
        if(z<=5){
            System.out.print("Order must be larger than 5 litres");
        }
        
        else{
            if((5*y+x)<z){
                System.out.println("You do not have enough jars to complete the order");
            }
            else{

                if(z/5>y && z%5+5*(z/5-y)<=x){

                    t = 5*(z/5-y)+z%5;
                    System.out.printf("(Order of %d litres will contain %d small(1 litre)jars",z,t);

                    
                    


                }
                else if(z/5<=y && z%5<=x){

                    System.out.printf("Order of %d litres will contain %d small(1 litre)jars",z,z%5);

                }
                else{
                    System.out.println("You do not have enough jars to complete the order");

                }
                
            }





        }
        scan.close();



        








    }




    
}
