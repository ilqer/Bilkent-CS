/**
 * Lab01_Q3_2
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;

public class Lab02_Q3_2 {
    public static void main(String[] args) {

        String x;
        int xSize;
        char first;
        char second;
        char third;
        char fourth;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter string:" );
        x = scan.nextLine();
        xSize = x.length();

        if (xSize<4){
            System.out.print("Length of string not sufficient");
        }
        else{
            if(xSize%2 == 0){
                
                first = x.charAt(0);
                second = x.charAt((int)Math.ceil(xSize/2)-1);
                third = x.charAt((int)Math.ceil(xSize/2));
                fourth =x.charAt(xSize-1);

                if(!x.contains(" ")){
                    System.out.printf("first:%c middlefirst:%c middlesecond:%c last:%c \n",first,second,third,fourth);

                    
                }
                if(Character.isLowerCase(first) && Character.isLowerCase(second) && Character.isLowerCase(third) && Character.isLowerCase(fourth)){

                    if(Math.abs(first-second)<2 && Math.abs(first-third)>=2 && Math.abs(first-fourth)>=2 && Math.abs(second-third)>=2 && Math.abs(second-fourth)>=2 ){

                        System.out.println("String is special: true");

                    }
                    else if (Math.abs(first-third)<2 && Math.abs(first-second)>=2 && Math.abs(first-fourth)>=2 && Math.abs(third-second)>=2 && Math.abs(third-fourth)>=2){

                        System.out.println("String is special: true");

                    }
                    else if(Math.abs(first-fourth)<2 && Math.abs(first-second)>=2 && Math.abs(first-third)>=2 && Math.abs(fourth-second)>=2 && Math.abs(fourth-third)>=2){

                        System.out.println("String is special: true");



                    }
                    else{
                        System.out.println("String is special: false");
                    }
                }





            
                else{
                    System.out.print("Characters not lowercase letters...");
                }
            }
            else{
                System.out.printf("%s in not even",x);
            }
            
            


            
            







        }
        scan.close();




        







    }


    }