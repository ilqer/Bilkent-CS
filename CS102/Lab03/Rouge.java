import java.util.Random;
import java.util.ArrayList;

public class Rouge extends Unit{
    Random rand = new Random();
    public Rouge(){
        name = "Rouge";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level+2;
    }
    public int getMaxHealth(){
        return level;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Rouge damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Rouge damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randEnemy = getRandomAlive(enemyWaiting);
        randEnemy.damage(getAttack());
        System.out.printf("Rouge damaged waiting %s by ",randEnemy.name +getAttack()+"\n");
        if(randEnemy.isDead){
            level++;
        }
    }
}
