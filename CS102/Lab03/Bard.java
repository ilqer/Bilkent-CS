import java.util.Random;
import java.util.ArrayList;

public class Bard extends Unit{
    Random rand = new Random();
    public Bard(){
        name = "Bard";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Bard damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        if(health<getMaxHealth()){
            health+=1;
            System.out.println("Bard healed self by 1");
        }
        else{
            System.out.println("Bard does nothing");
        }
        
        
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randAlly = getRandomAlive(allyWaiting);
        if(randAlly != null){           
            randAlly.level+=1;
            System.out.println("Bard leveled up "+randAlly.name);
        }
        else{
            System.out.println("Bard does nothing");
        }
    }
}
