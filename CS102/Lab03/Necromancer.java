import java.util.ArrayList;
import java.util.Random;

public class Necromancer extends Unit {
    Random rand = new Random();
    public Necromancer(){
        name = "Necromancer";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level+1;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Necromancer damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randAlly = getRandomDead(allyWaiting);
        if(randAlly != null){
            randAlly.isDead = false;
            randAlly.level-=1;
            randAlly.health = randAlly.getMaxHealth();
            System.out.println("Necromancer revived "+randAlly.name );
        }
        else{
            System.out.println("Necromancer does nothing");
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit randEnemy = getRandomAlive(allyWaiting);
        if(randEnemy != null && randEnemy.level != 1){           
            randEnemy.level-=1;
            System.out.println("Necromancer decreased level of "+randEnemy.name);
        }
        else{
            System.out.println("Necromancer does nothing");
        }
    }
}
