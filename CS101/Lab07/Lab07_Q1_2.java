
/**
 * Lab07_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.Scanner;

public class Lab07_Q1_2 {
    public static String[] name = { "Ela", "Eren", "Alona", "Jen", "Mark", "Mel", "Ender" };
    public static int[] arrives = { 9, 5, 8, 3, 9, 4, 8 };
    public static int[] leaves = { 12, 7, 11, 9, 10, 8, 10 };
    public static int[] score = { 1, 3, 2, 1, 3, 1, 2 };

    public static int[] friendsAttending(int arrive, int leave) {
        int[] interraction;
        interraction = new int[leave - arrive];
        for (int i = 0; i < interraction.length; i++) {
            interraction[i] = 0;
        }
        for (int count = arrive; count < leave; count++) {
            for (int count2 = 0; count2 < arrives.length; count2++) {
                if (count >= arrives[count2] && count < leaves[count2]) {
                    interraction[count - arrive] = interraction[count - arrive] + score[count2];
                }
            }
        }
        return interraction;
    }

    public static int bestTimeToAttend(int arrive, int leave, String avoid) {
        int avoidInd;
        int[] Attending;
        int bestT;
        bestT = -1;
        avoidInd = -1;
        Attending = friendsAttending(arrive, leave);
        int max = Integer.MIN_VALUE;

        for (int count = 0; count < name.length; count++) {
            if (name[count].equals(avoid)) {
                avoidInd = count;
            }
        }
        if (avoidInd != -1) {
            for (int count = arrive; count < leave; count++) {
                if (arrives[avoidInd] <= count && leaves[avoidInd] > count) {
                    Attending[count - arrive] = Attending[count - arrive]-score[avoidInd];
                }
            }
        }
        for (int count = 0; count < Attending.length; count++) {
            if (Attending[count] > max) {
                max = Attending[count];
                bestT = count + arrive;
            }
        }
        return bestT;
    }

    public static void main(String[] args) {
        int arrive;
        int leave;
        int[] Attend;
        int max = Integer.MIN_VALUE;
        int bestT;

        String avoid;

        bestT = -1;

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter time interval you are available:");
        arrive = scan.nextInt();
        leave = scan.nextInt();
        Attend = friendsAttending(arrive, leave);
        for (int count = 0; count < Attend.length; count++) {
            System.out.printf("At %dpm, %d is the addition of the points of friends at the party", arrive + count,
                    Attend[count]);
            System.out.println();
            if (Attend[count] > max) {
                max = Attend[count];
                bestT = count + arrive;
            }

        }
        System.out.println("Best time to attend is " + bestT);
        System.out.print("Enter friend you wish to avoid:");
        avoid = scan.next();
        System.out.println();
        System.out.printf("Best time to attend to avoid %s and to see your best friends is %d", avoid,
                bestTimeToAttend(arrive, leave, avoid));
        scan.close();

    }
}
