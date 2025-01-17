package Lab04;



import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JPanel;


public class TreePanel extends JPanel{
    Random rand = new Random();

    int swing = 1;
    private static final int[] treeX = {
        94,194,167,160,211,219,205,184,169,188,163,174,156,142,146,128,136,130,80,70,72,125,130,118
    };
    private static final int[] treeY = {
        271,271,259,225,170,106,161,181,128,72,126,180,189,164,110,76,114,158,140,90,149,175,223,257
    };
    private static final int[] leavesX = {
        57,116,189,236,256,212,143,113,102,86,46,17
    };
    private static final int[] leavesY = {
        163,142,158,142,93,44,27,32,70,53,65,123
    };
    private static int[] snowY = new int[24];
    private static int[] snowX = new int[24];
    private static int[] snowXwoob = new int[24];
    private static int[] snowYwoob = new int[24];

    
    int Season;
    public TreePanel(){
        for(int i = 0;i<snowX.length;i++){
            int start = rand.nextInt(296);
            snowX[i] = start;
        }
        for(int i = 0;i<snowY.length;i++){
            int start = rand.nextInt(306);
            snowY[i] = start;
        }
        for(int i = 0;i<snowXwoob.length;i++){
            int woob = rand.nextInt(3);
            woob-=1;
            snowXwoob[i] = woob;
        }
        for(int i = 0;i<snowYwoob.length;i++){
            int woob = rand.nextInt(2)+1;
            snowYwoob[i] = woob;
        }
        
        new Timer(300,taskperformer).start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        g.setColor(SeasonColors.getColor(Season, 0));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(SeasonColors.getColor(Season, 1));
        g.fillRect(0, 271,getWidth(),17);
        if(Season != 3 && Season != 2){
            g.setColor(SeasonColors.getColor(Season, 3));
            g.fillPolygon(leavesX,leavesY,leavesX.length);
        }
        if(Season == 2){
            g.setColor(SeasonColors.getColor(Season, 3));
            g.fillPolygon(getLeaveCoordX(),leavesY,leavesX.length);
        }
        if(Season == 2 || Season == 3){
            g.setColor(SeasonColors.getColor(Season, 2));
            g.fillPolygon(getTreeCoordX(),treeY,treeX.length);
        }
        else{
            g.setColor(SeasonColors.getColor(Season, 2));
            g.fillPolygon(treeX,treeY,treeX.length);
        }
        
        if(Season == 1){
            g.setColor(SeasonColors.getColor(Season, 4));
            g.fillOval(58,102,10,10);
            g.fillOval(95,120,10,10);
            g.fillOval(150,64,10,10);
            g.fillOval(218,82,10,10);
            g.fillOval(193,134,10,10);
            
        }
        if(Season == 3){
            g.setColor(SeasonColors.getColor(Season, 3));
            for(int i = 0;i<snowY.length;i++){
                g.fillOval(snowX[i],snowY[i],4,4);
            }
            
        }
        

        repaint();
    }
    ActionListener taskperformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt){
            swing = -swing;
            snowChange();
        }
    };
     

    public void setSeason(int i ){
        Season = i;

    }
    public int[] getTreeCoordX(){
        int[] tempX = treeX.clone();
        
        for(int i = 0; i<tempX.length; i++){
            tempX[i]=treeX[i]+swing*((int)Math.log(3*(272-treeY[i]))/3);
        }
        return tempX;
        
    }
    public int[] getLeaveCoordX(){
        int[] tempX = leavesX.clone();
        
        for(int i = 0; i<tempX.length; i++){
            tempX[i]=leavesX[i]+swing*((int)Math.log(3*(272-treeY[i]))/3);
        }
        return tempX;
        
    }
    public void snowChange(){
        
        for(int i = 0;i<snowY.length;i++){
            snowY[i]+=snowYwoob[i];
            snowX[i]+=snowXwoob[i];
            if(snowY[i]>306){
                snowY[i]=0;
            }
            if(snowX[i]>296){
                snowX[i] = 0;
            }
            if(snowX[i]<0){
                snowX[i] = 296;
            }
        }
    }

    
    
    
}
