import java.util.ArrayList;
import java.util.Random;

public class Board {
    
    public int width;
    public int height;
    public String board;
    public int[] corners;
    
    public int trapNum;
    private ArrayList<Player>players;
    
    public Board(int h,int w,ArrayList<Player> p){
        
        width = w;
        height =h;
        players = p;

        

        
        
        corners = new int[]{1,width,width+height-1,2*width+height-2};
        

        

        refreshBoard();

    }
    private String generateHeader(){
        String header = "";
        for(int j = 0;j<3*width+1;j++){
                header+="#";
                }
                header+="\n";
        return header;
    }
    private String generateSideHeader(){
        String header = "";
        header+="####";
        for(int j = 0;j<3*width-7;j++){
            header+=" ";
        }
        header+="####\n";
        return header;

    }
    private void refreshBoard(){
        board = "";

        board+=generateHeader();
        for(int i = 0;i<width;i++){
            board+="#  ";
        }
        board+="#\n";
        for(int i = 0;i<width;i++){
            board+="#  ";
        }
        board+="#\n";
        board+=generateHeader();
        for(int i = 0;i<height-2;i++){
            board+="#  #";
            for(int j = 0;j<3*width-7;j++){
                board+=" ";
            }
            board+="#  #\n";
            board+="#  #";
            for(int j = 0;j<3*width-7;j++){
                board+=" ";
            }
            board+="#  #\n";
            if(i!=height-3){
                board+=generateSideHeader();
            } 
        }
        
        board+=generateHeader();
        for(int i = 0;i<width;i++){
            board+="#  ";
        }
        board+="#\n";
        for(int i = 0;i<width;i++){
            board+="#  ";
        }
        board+="#\n";
        board+=generateHeader();
        

    }
    public int[] getCoordinates(int cellNum,int order){
        int[] coordinates = new int[2];
        if(corners[0]<=cellNum && cellNum<corners[1]){
            coordinates[0] = 1+3*(cellNum-corners[0]);
            coordinates[1] = 1;
        }
        else if(corners[1]<=cellNum && cellNum<corners[2]){
            coordinates[0] = 1+3*(corners[1]-1);
            coordinates[1] = 1+3*(cellNum-corners[1]);
        }
        else if(corners[2]<=cellNum && cellNum<corners[3]){
            coordinates[0] = 1+3*(corners[3]-cellNum);
            coordinates[1] = 1+3*(corners[2]-corners[1]);
        }
        else if(corners[3]<=cellNum){
            coordinates[0] = 1;
            coordinates[1] = 1+3*(2*(width+height-2)-cellNum+1);
        }
        coordinates[0] += order%2;
        coordinates[1] += order/2;
        return coordinates;
    }
    
    public String getBoard(){
        
        
        StringBuilder boardBuilder = new StringBuilder(board);
        for(int i = 0;i<players.size();i++){
            int[]coordinates = getCoordinates(players.get(i).getCell(), i);
            boardBuilder.setCharAt(coordinates[0]+coordinates[1]*(3*width+2), players.get(i).getSymbol());
            
        }
        
        return boardBuilder.toString();
    }
    public int[] trapCells(){
        Random rand = new Random();
        trapNum = 2*(width+height-2)/5;

        int[]trapLoc = new int[trapNum];
        for(int i = 0;i<trapNum;i++){
            trapLoc[i] = -1;
        }
        

        for(int i =0;i<trapNum;i++){
            boolean canBeTrap = true;
            int possibleTrapLoc = rand.nextInt(2*(width+height-2))+1;
            for(int j = 0;j<corners.length;j++){
                if(corners[j] == possibleTrapLoc){
                    canBeTrap = false;
                }
            }
            for(int j = 0;j<trapLoc.length;j++){
                if(trapLoc[j] == possibleTrapLoc){
                    canBeTrap = false;
                }
            }
            if(canBeTrap){
                trapLoc[i] = possibleTrapLoc;
            }
            else{
                i--;
            }
            
        }
        return trapLoc;
    }


        
        
        
        
}
