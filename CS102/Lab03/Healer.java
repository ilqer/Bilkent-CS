import java.util.ArrayList;
import java.util.Random;

public class Healer extends Unit {
    Random rand = new Random();
    public Healer(){
        name = "Healer";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level+2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randAlly = getRandomAlive(allyWaiting);
        if(health == getMaxHealth() && randAlly != null){
            randAlly.health+=level;
            if(randAlly.health > randAlly.getMaxHealth()){
                randAlly.health = randAlly.getMaxHealth();
            }
        }
        else{
            health+=level;
            if(health>getMaxHealth()){
                health = getMaxHealth();
            }
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Healer damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randAlly = getRandomDead(allyWaiting);
        if(randAlly != null){
            randAlly.isDead = false;
            randAlly.level-=1;
            if(randAlly.level<1){
                randAlly.level = 1;
            }
            randAlly.health = randAlly.getMaxHealth();
            System.out.println("Healer revived "+randAlly.name );
        }
        else{
            System.out.println("Healer does nothing");
        }
    }
}
