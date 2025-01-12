/**
 * Lab01_Q2
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;


public class Lab_Q2 {
    public static void main(String[] args) {

        String min;
        String hour;
        String day;
        String year;

        int tdots;
        int slash;
        int sline;
        int slinet;
        int comma;

        String month;
        String weekday;
        String text;


        
        Scanner stdin = new Scanner(System.in);
        
        
        System.out.print("Enter date and time:");
        text = stdin.nextLine();
        
        stdin.close();

        tdots = text.indexOf(":");
        slash = text.indexOf("/");
        sline = text.indexOf("-");
        slinet = text.indexOf("-",sline+1);
        comma = text.indexOf(",");

        hour = text.substring(1,tdots);
        min  = text.substring(tdots+1,slash);
        weekday = text.substring(slash+1,sline);
        month = text.substring(sline+1,slinet);
        day = text.substring(slinet+1,comma);
        year = text.substring(comma+1);



        System.out.printf("%s minutes past %s on %s %s %s (%s)",min,hour,day,month,year,weekday);




    }
    
}
