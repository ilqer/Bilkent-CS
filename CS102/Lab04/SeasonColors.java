package Lab04;

import java.awt.Color;
import java.util.ArrayList;

public class SeasonColors {
    private static ArrayList<Color> spring = new ArrayList<Color>(){
        {
            add(new Color(157, 244, 252));
            add(new Color(134, 169, 101));
            add(new Color(132, 52, 38));
            add(new Color(97, 225, 119));
        }
    };
    private static ArrayList<Color> summer=new ArrayList<Color>(){
        {
            add(new Color(178, 234, 252));
            add(new Color(106, 196, 77));
            add(new Color(124, 40, 25));
            add(new Color(24, 241, 24));
            add(new Color(220, 23, 40));
        }
    };
    private static ArrayList<Color> fall=new ArrayList<Color>(){
        {
            add(new Color(122, 175, 183));
            add(new Color(184, 111, 36));
            add(new Color(98, 36, 25));
            add(new Color(203, 113, 17));
        }
    };
    private static ArrayList<Color> winter=new ArrayList<Color>(){
        {
            add(new Color(82, 95, 110));
            add(new Color(128, 144, 142));
            add(new Color(78, 38, 31));
            add(new Color(251, 254, 254));
        }
    };

    public static Color getColor(int season, int type) {
        ArrayList<Color> seasonColors;
        if (season == 0) {
            seasonColors = spring;

        } else if (season == 1) {
            seasonColors = summer;
        } else if (season == 2) {
            seasonColors = fall;
        } else{
            seasonColors = winter;
        }
        return seasonColors.get(type);
    }

}
