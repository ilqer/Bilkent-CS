/**
 * Lab05_Q3
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab05_Q3 {
    public static void displayMenu(){
        System.out.printf(" 1 - Find Probability of Same Birthday %n 2 - Draw Chart %n 3 - Convert Word %n 4 - QUIT%n");
    }
    public static int getChoice(){
        int cho;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter choice:");
        cho = scan.nextInt();
        return(cho);
    }
    public static double sameBirthday(int size){
        
        double probability;

        probability = 1;

        for (int count = 0; count <= size - 1; count++) {
            probability = probability * (365 - count) / 365;
        }
        return (1-probability);
    }
    public static int findMax(int num){
        int max;
        max = 0;
        while(num!=0){
            if(num%10>max){
                max = num%10;
            }
            num/=10;
        }
        return max;
    }
    public static void displayChart(int digit,String text){
        for (; digit >= 1; digit--) {

            for (int count = 0; count < text.length(); count++) {

                if (Character.getNumericValue(text.charAt(count)) >= digit) {
                    System.out.printf("%-10s", "***");
                } else {
                    System.out.printf("%-10s", "");
                }
            }
            System.out.println();
        }
    }
    public static boolean isValidNumeric(String s){
        boolean numeric;
        numeric = true;
        for(int count = 0; count<s.length();count++){
            if(!Character.isDigit(s.charAt(count))){
                numeric = false;
            }
        }
        return numeric;
    }
    public static int countLetter(String s,int loc){
        int count;
        int equal;
        count = 0;
        equal = -1;
        while(count<s.length()){
            if(s.charAt(loc) == s.charAt(count)){
                equal++;
            }
            count++;    
        }
        return equal;
    }
    public static void convertWord(String s){
        String convertedWord;
        convertedWord = "";
        for (int count = 0; count < s.length(); count++) {
            char c = s.charAt(count);

            if (!Character.isLetter(c)) {
                convertedWord += "*";
            } else {
                if(countLetter(s, count)>0){
                    convertedWord += ")";
                }
                else{
                    convertedWord += "(";
                }   
            }
        }
        System.out.printf("Original word:%s Converted Word:%s", s, convertedWord);
        System.out.println();
    }


    public static void main(String[] args) {
        int choice;
        int size;
        int chartData;
        String wordConvert;
        displayMenu();
        

        Scanner scan = new Scanner(System.in);
        do{
            choice = getChoice();
            if (choice == 1){
                System.out.print("Enter the group size:");
                size = scan.nextInt();
                System.out.printf("The probability of two people in a group of %d having the same birthday is %.2f ", size, sameBirthday(size));
                System.out.println();
            }
            else if (choice == 2){

                System.out.print("Enter chart data:");
                chartData = scan.nextInt();
                if(isValidNumeric(Integer.toString(chartData))){
                    for (int count = 0; count < (Integer.toString(chartData)).length(); count++) {
                        System.out.printf("rewiev%d   ", count + 1);
                    }
                    System.out.println();
                    displayChart(findMax(chartData),Integer.toString(chartData));
                }
                else{
                    System.out.print("Invalid chart data");
                }
            }
            else if(choice == 3){
                System.out.print("Enter word to convert:");
                wordConvert = scan.nextLine();
                convertWord(wordConvert);
            }
            else if(choice == 4){
                System.out.println("GOODBYE");
            }
            else{
                System.out.println("Invalid Choice");
            }
        }while(choice!=4);
        scan.close();
    }
}
