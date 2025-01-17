import java.util.ArrayList;
import java.util.Random;

public class Archer extends Unit {
    Random rand = new Random();
    public Archer(){
        name = "Archer";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level+1;
    }
    public int getMaxHealth(){
        return level+1;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Archer damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randEnemy = getRandomAlive(enemyWaiting);
        randEnemy.damage(getAttack());
        System.out.printf("Archer damaged waiting %s by ",randEnemy.name +getAttack()+"\n");
        if(randEnemy.isDead){
            level++;
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        health++;
        if(health>getMaxHealth()){
            health = getMaxHealth();
        }
        System.out.println("Archer healed self by 1");
    }
}
