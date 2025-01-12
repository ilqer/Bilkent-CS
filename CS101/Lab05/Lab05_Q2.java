/**
 * Lab05_Q2
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab05_Q2 {
    public static char convertChar(char c) {
        String key = "a@bpdqi!l1mwnuo0s5t+z2A4B8E3G6WM";
        int index = key.indexOf(c);
        if (index >= 0 && index % 2 == 0) {
            return key.charAt(index + 1);
        } else {
            return c;
        }
    }
    public static String convertText(String input){
        int count;
        String text;
        count = 0;
        text = "";
        while(input.length()>count){
        text += convertChar(input.charAt(count));
        count++;
        }
        return text;
    }
    public static int countNumbers(String s){
        int count;
        int num;
        count = 0;
        num = 0;
        while(count<s.length()){
            if(Character.isDigit(s.charAt(count))){
                num++;
            }
            count++;
        }
        return num;
    }
    public static void main(String[] args) {
        String input;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a phrase for conversion:");
        input = scan.nextLine();
        if(input.length()==0){
            System.out.println("No phrase entered.");
        }
        else{
            System.out.printf("Text you entered: '%s'%n",input);
            System.out.printf("After conversion: '%s'%n",convertText(input)); 
            if(countNumbers(convertText(input)) == 0){
                System.out.println("!! Attention !! There is no number in this text");
            }
            if(input.equals(convertText(input))){
                System.out.println("No conversion happened");
            }
        }
        scan.close();

    }
    
}
