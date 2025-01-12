/**
 * Lab01_Q3
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */

import java.util.Scanner;

public class Lab_Q3 {
    public static void main(String[] args) {


        int kgToLose; 
        int day;
        int carbPer;
        int proPer;
        int fatPer;
        int dailyCal;
        int lessCarb;
        double recCarb;
        double recPro;
        double recFat;
        
        final int oneKgCal = 7700;
        final int maintainW = 2000;

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter kilograms to lose: ");
        kgToLose = scan.nextInt();

        System.out.printf("Enter days to lose %d kilograms: ",kgToLose);
        day = scan.nextInt();

        System.out.print("Enter persentage of carbs you will take: ");
        carbPer = scan.nextInt();

        scan.close();


        proPer = (100-carbPer)*2/5;
        fatPer = (100-carbPer-proPer);

        lessCarb = kgToLose*oneKgCal/day;

        dailyCal = maintainW-lessCarb;
        recCarb = dailyCal*carbPer/100;
        recPro = dailyCal*proPer/100;
        recFat = dailyCal*fatPer/100;

        System.out.println(    "*******************************************************************************************************");
        System.out.printf("To lose %d kilograms in % days you will need a daily deficit of %d calories\n",kgToLose,day,lessCarb);
        System.out.println(    "*******************************************************************************************************");
        System.out.printf("Reccomended daily calories to lose %d kilos in %d days:%d\n",kgToLose,day,dailyCal);
        System.out.println(    "*******************************************************************************************************");
        System.out.println(    "Macro                Percent             Calories Per Gram               Reccomended Calories     Grams");
        System.out.printf("Carbohydrate         %d%%                  4                               %.0f                      %.1f\n",carbPer,recCarb,recCarb/4);
        System.out.printf("Fat                  %d%%                  9                               %.0f                      %.1f\n",fatPer,recFat,recFat/9);
        System.out.printf("Protein              %d%%                  4                               %.0f                      %.1f\n",proPer,recPro,recPro/4);
       








        

        







    }


    
}
