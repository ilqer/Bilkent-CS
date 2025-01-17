public class Player {

    private char symbol;
    public int turn;
    public int diceRoll;
    public int location;
    public int cell;
    public int startLoc;
    private int triggeredTraps;

    public Player(){
        cell = 1;
        triggeredTraps = 0;
    }
    public void setSymbol(char c){
        symbol = c;
    }
    public char getSymbol(){
        return symbol;
    }
    public void setTurn(int i){
        turn = i;
    }
    public void diceRoll(int i){
        diceRoll = i;
    }
    public int getDiceRoll(){
        return diceRoll;
    }
    public void setLocation(int l){
        location = l;
    }
    public int getCell(){
        return cell;
    }
    public void increaseCell(int change){
        cell = cell+change;
    }
    public void setCell(int c){
        cell = c;
    }
    public void setStartLoc(int s){
        startLoc = s;
    }
    public void triggeredTraps(){
        triggeredTraps++;
    }
    public int getTriggeredTraps(){
        return triggeredTraps;
    }


}
