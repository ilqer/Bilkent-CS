
import java.util.ArrayList;

public class Wizard extends Unit{
    public Wizard(){
        name = "Wizard";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return 1;
    }
    public int getMaxHealth(){
        return level+2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.println("Wizard does nothing.");
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.println("Wizard does nothing.");
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        
        for(int i = 0;i<enemyWaiting.size();i++){
            if(!enemyWaiting.get(i).isDead){
                System.out.printf("Wizard damaged waiting %s by 1",enemyWaiting.get(i).name);
                enemyWaiting.get(i).damage(1);
                if(enemyWaiting.get(i).isDead){
                    level++;
                }
            }
        }
        
    }
}
