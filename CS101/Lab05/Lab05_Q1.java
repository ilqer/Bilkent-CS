/**
 * Lab05_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab05_Q1 {
    public static int persistence(int num){
        int digit;
        int mult;
        int count;

        mult = 1;
        count = 0;
        if(num/10!=0){
            do{
                do{
                    digit = num%10;
                    mult = mult*digit;
                    num = num/10;
                }while(num>0);
                num = mult;
                mult = 1;
                count++;
            }while(num/10!=0);
        }
        return count;
    }
    public static void main(String[] args) {
        int number;
        int count;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a positive integer:");
        do{ 
            while(!scan.hasNextInt()){
                scan.next();
                System.out.print("Enter a positive integer:");
            }
            number = scan.nextInt();
        }while( number<=0);
        count = persistence(number);
        System.out.printf("multiplicative persistence of %d is %d",number,count);
        scan.close();
    }
    
}
