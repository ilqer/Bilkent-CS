/**
 * Lab06_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.util.ArrayList;
import java.util.Random;

public class Lab06_Q1 {
    private static final int CAT_RADIUS = 50;
    private static final int CAMPUS_RADIUS = 250;

    public static double calculateDistance(String locationOne, String locationTwo) {
        int commaOne;
        int commaTwo;

        int xOne;
        int yOne;
        int xTwo;
        int yTwo;

        double distance;

        commaOne = locationOne.indexOf(',');
        commaTwo = locationTwo.indexOf(',');
        xOne = Integer.valueOf(locationOne.substring(0, commaOne));
        yOne = Integer.valueOf(locationOne.substring(commaOne + 1));
        xTwo = Integer.valueOf(locationTwo.substring(0, commaTwo));
        yTwo = Integer.valueOf(locationTwo.substring(commaTwo + 1));

        distance = Math.sqrt(Math.pow(xOne - xTwo, 2) + Math.pow(yOne - yTwo, 2));
        return distance;
    }

    public static boolean doesIntersect(String locationOne, String locationTwo) {
        boolean intersect;
        intersect = false;
        if (calculateDistance(locationOne, locationTwo) <= CAT_RADIUS * 2) {
            intersect = true;
        }
        return intersect;

    }

    public static int countIntersecting(String location, ArrayList<String> locations) {
        int intersectNum;

        intersectNum = -1;

        for (int count = 0; count < locations.size(); count++) {
            if (doesIntersect(location, locations.get(count))) {
                intersectNum++;
            }
        }
        return intersectNum;
    }

    public static void displayIntersections(ArrayList<String> names, ArrayList<Integer> month,ArrayList<String> locations) {
        for (int count = 0; count < names.size(); count++) {
            System.out.printf("%s at location %s for %d months intersects with:", names.get(count),locations.get(count), month.get(count));
            if (countIntersecting(locations.get(count), locations) == 0) {
                System.out.print("NO CATS");
            } else {
                for (int countTwo = 0; countTwo < names.size(); countTwo++) {
                    if (count != countTwo && doesIntersect(locations.get(count), locations.get(countTwo))) {
                        System.out.println();
                        System.out.printf("         %s, at location %s for %d months", names.get(countTwo),locations.get(countTwo), month.get(countTwo));
                    }
                }
            }
            System.out.println();
        }
    }

    public static int findMinMonthsAtLocation(String location, ArrayList<String> locations, ArrayList<Integer> month) {
        int index;
        int minmonth;

        minmonth = Integer.MAX_VALUE;
        index = -1;
        for(int count = 0 ; count<locations.size(); count++){
            if(doesIntersect(location, locations.get(count))){
                if(minmonth>month.get(count)){
                    index = count;
                    minmonth = month.get(count);
                }
            }
        }


       
        return index;
    }

    public static String findRandomLocation() {
        int randX;
        int randY;

        String location;

        location = "";

        Random rand = new Random();
        while (location.isEmpty()) {
            randX = rand.nextInt(CAMPUS_RADIUS * 2);
            randY = rand.nextInt(CAMPUS_RADIUS * 2);
            // if name not already added to list
            if (Math.sqrt(Math.pow(randX, 2) + Math.pow(randY, 2)) <= CAMPUS_RADIUS * 2) {
                location = randX + "," + randY;
            }
        }
        return location;
    }

    public static void moveCats(ArrayList<String> locations, ArrayList<Integer> month) {
        String location;

            for (int count = 0; count < locations.size(); count++) {
                if (countIntersecting(locations.get(count), locations) >= 2) {
                    int index = findMinMonthsAtLocation(locations.get(count), locations, month);
                    do{
                        location = findRandomLocation();
                        locations.set(index,location);
                        month.set(index, 0);
                    }while(countIntersecting(location, locations) != 0);
                    count--;
                }
            }

    }

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(CatInfoGenerator.getRandomNameList(20));
        ArrayList<String> locations = new ArrayList<>(CatInfoGenerator.getRandomLocationList(20));
        ArrayList<Integer> month = new ArrayList<>(CatInfoGenerator.getRandomMonthList(20));

        displayIntersections(names, month, locations);
        moveCats(locations, month);
        System.out.println("After moving the cats:");
        displayIntersections(names, month, locations);

    }

}
