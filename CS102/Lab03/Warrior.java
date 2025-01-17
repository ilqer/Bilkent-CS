import java.util.ArrayList;

public class Warrior extends Unit {

    

    public Warrior(){
        name = "Warrior";
        level = 1;
        health = getMaxHealth();
        attack = getAttack();
    }
    public int getAttack(){
        return level+1;
    }
    public int getMaxHealth(){
        return level+2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.println("Warrior does nothing.");
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Warrior damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.println("Warrior damaged arena opponent by "+getAttack());
        if(arenaOpponent.isDead){
            level++;
        }
    }


}
