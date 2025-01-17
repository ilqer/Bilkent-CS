import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Arena {
    public static ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    public static ArrayList<Unit> computerUnits = new ArrayList<Unit>();

    private static boolean endGame = false;

    public Arena(){
        Random rand = new Random();
        
        playerUnits.add(new Warrior());
        computerUnits.add(new Warrior());

        for(int i = 0; i<6;i++){
            int randNum = rand.nextInt(7);
            if(randNum == 0){
                playerUnits.add(new Warrior());
            }
            else if(randNum == 1){
                playerUnits.add(new Archer());
            }
            else if(randNum == 2){
                playerUnits.add(new Bard());
            }
            else if(randNum == 3){
                playerUnits.add(new Healer());
            }
            else if(randNum == 4){
                playerUnits.add(new Necromancer());
            }
            else if(randNum == 5){
                playerUnits.add(new Rouge());
            }
            else if(randNum == 6){
                playerUnits.add(new Wizard());
            }
        }
        for(int i = 0; i<6;i++){
            int randNum = rand.nextInt(7);
            if(randNum == 0){
                computerUnits.add(new Warrior());
            }
            else if(randNum == 1){
                computerUnits.add(new Archer());
            }
            else if(randNum == 2){
                computerUnits.add(new Bard());
            }
            else if(randNum == 3){
                computerUnits.add(new Healer());
            }
            else if(randNum == 4){
                computerUnits.add(new Necromancer());
            }
            else if(randNum == 5){
                computerUnits.add(new Rouge());
            }
            else if(randNum == 6){
                computerUnits.add(new Wizard());
            }
        }
        
    }
    public static void battle(int playerIndex, int computerIndex){

        Unit playerTemp = new Unit();
        Unit computerTemp = new Unit();
        playerTemp = playerUnits.get(playerIndex);
        computerTemp = computerUnits.get(computerIndex);

        System.out.print("Player: ");
        playerTemp.firstPhase(computerTemp, playerUnits, computerUnits);
        System.out.print("Computer: ");
        computerTemp.firstPhase(playerTemp, computerUnits, playerUnits);
        if(!playerTemp.isDead && !computerTemp.isDead){
            System.out.print("Player: ");
            playerTemp.secondPhase(computerTemp, playerUnits, computerUnits);
            System.out.print("Computer: ");
            computerTemp.secondPhase(playerTemp, computerUnits, playerUnits);
            if(!playerTemp.isDead && !computerTemp.isDead){
                System.out.print("Player: ");
                playerTemp.thirdPhase(computerTemp, playerUnits, computerUnits);
                System.out.print("Computer: ");
                computerTemp.thirdPhase(playerTemp, computerUnits, playerUnits);
            }
        }
        if(playerTemp.isDead && !computerTemp.isDead){
            computerTemp.level+=1;

        }
        if(computerTemp.isDead && !playerTemp.isDead){
            playerTemp.level+=1;
        }
        playerUnits.set(playerIndex, playerTemp);
        computerUnits.set(computerIndex, computerTemp);
        endGame = true;
        for(int i = 0; i<playerUnits.size(); i++){
            if(!playerUnits.get(i).isDead){
                endGame = false;
            }
        }
        for(int i = 0; i<computerUnits.size(); i++){
            if(!computerUnits.get(i).isDead){
                endGame = false;
            }
        }
    }
    public static void main(String[] args) {
        int turn = 0;
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        Arena arena = new Arena();
        while(!endGame){
            int chosenUnit;
            Unit computerChosen = Unit.getRandomAlive(computerUnits);
            if(computerChosen == null) {
                return;
            }
            turn++;
            System.out.println("Turn: "+ turn);
            System.out.println();
            System.out.println("Computer's units:");
            for(int i = 0; i<computerUnits.size(); i++){
                System.out.println((i+1) + ". " +computerUnits.get(i).getInfo());
            }
            System.out.println();
            System.out.println("Player's units:");
            for(int i = 0; i<playerUnits.size(); i++){
                System.out.println((i+1) + ". " +playerUnits.get(i).getInfo());
            }
            System.out.print("Which unit you choose: ");
            chosenUnit = scan.nextInt()-1;
            System.out.println();
            System.out.println("You choose "+ playerUnits.get(chosenUnit).getInfo());
            System.out.println("Computer chooses " + computerChosen.getInfo());
            battle(chosenUnit,computerUnits.indexOf(computerChosen));
        }
        scan.close();
    }
}
