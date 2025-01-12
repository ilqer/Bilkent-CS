/**
 * Lab02_Q3
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;

public class Lab02_Q3 {
    public static void main(String[] args) {

        String x;
        int xSize;
        char first;
        char second;
        char third;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter string:" );
        x = scan.nextLine();
        xSize = x.length();

        if (xSize<3){
            System.out.print("Length of string not sufficient");
        }
        else{
            first = x.charAt(0);
            second = x.charAt((int)Math.ceil(xSize/2));
            third = x.charAt(xSize-1);
            if(Character.isLowerCase(first) && Character.isLowerCase(second) && Character.isLowerCase(third)){
                
                if(!x.contains(" ")){
                    System.out.printf("first:%c middle:%c last:%c \n",first,second,third);
                    
                }

                if(Math.abs(first-second)<2 && Math.abs(first-third)>=2 && Math.abs(second-third)>=2 ){

                    System.out.println("String is special: true");

                }
                else if (Math.abs(first-third)<2 && Math.abs(first-second)>=2 && Math.abs(third-second)>=2 ){

                    System.out.println("String is special: true");

                }
                else{
                    System.out.println("String is special: false");
                }





            }
            else{
                System.out.print("Characters not lowercase letters...");
            }
            scan.close();

            


            
            







        }




        







    }


    }
    

