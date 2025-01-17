import java.util.ArrayList;
import java.util.Random;

public class Unit {
    public String name;
    public int health;
    public int maxHealth;
    public int level;
    public boolean isDead;
    public int attack;


    public Unit(){

    }
    public int getAttack(){
        return attack; 
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){

    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        
    }


    
    public void damage(int damageAmount){
        health = health-damageAmount;
        if(health<=0){
            isDead = true;
        }
    }
    public void increaseLevel(){
        level++;
    }
    public void decreaseLevel(){
        level--;
        if(level<1){
            level = 1;
        }
        
    }
    public void revive(){
        isDead = false;
        level--;
        if(level<1){
            level = 1;
        }
        health = getMaxHealth();
    }
    public void heal(int healAmount){
        health+=healAmount;
        if(health>getMaxHealth()){
            health = getMaxHealth();
        }
    }
    public String getInfo(){
        return name + ", LVL: " + level + ", ATK: " + getAttack() +", HEALTH: " + health +"/" + getMaxHealth();
        
    }
    public static  Unit getRandomAlive(ArrayList<Unit> ally){
        Random rand = new Random();
        
        ArrayList<Unit> aliveAllys = new ArrayList<Unit>();
        for(int i = 0;i<ally.size();i++){
            if(!ally.get(i).isDead){
                aliveAllys.add(ally.get(i));
            }
        }
        int chosenAlly = rand.nextInt(aliveAllys.size()+1)-1;
        if(chosenAlly<0){
            chosenAlly = 0;
        }
        return aliveAllys.get(chosenAlly);
    }
    public static Unit getRandomDead(ArrayList<Unit> ally){
        Random rand = new Random();
        
        ArrayList<Unit> deadUnits = new ArrayList<Unit>();
        for(int i = 0;i<ally.size();i++){
            if(ally.get(i).isDead){
                deadUnits.add(ally.get(i));
            }
        }
        int chosenAlly = rand.nextInt(deadUnits.size()+1)-1;
        if(chosenAlly<0){
            chosenAlly = 0;
        }
        return deadUnits.get(chosenAlly);
    }
}
