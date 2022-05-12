/**
 * Defines a spell for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */
import java.util.Random;

public class Spell {
    private String name;
    private String type;
    private int diceNum; //(6 for d6, 8 for d8 etc.)
    private int diceType;
    private int accuracy;
    public Random randgen = new Random();

    public Spell(String name, String type, int diceNum, int diceType, int accuracy){ //Constructor
        this.name = name;
        this.type = type;
        this.diceNum = diceNum;
        this.diceType = diceType;
        this.accuracy = accuracy;
    }

    public String getType(){
        return this.type;
    }

    public int getDiceNum(){
        return this.diceNum;
    }

    public int getDiceType(){
        return this.diceType;
    }

    public int getAccuracy(){
        return this.accuracy;
    }

    public String getName(){
        return this.name;
    }
    
    public int rollDMG(){
        int sum = 0;
        for (int i=0; i<this.getDiceNum(); i++){
            sum += 1+randgen.nextInt(diceType);
        }
        return sum;
    }

    public void buffSpell(int buffLVL){
        diceNum += buffLVL;
    }

    public void debuffSpell(int debuffLVL){
        diceNum -= debuffLVL;
    }

    public int rollDMG(int buffLVL){                    // buff the spell then roll damage then debuff
        buffSpell(buffLVL);
        int sum = this.rollDMG();
        debuffSpell(buffLVL);
        return sum;
    }

    public int calcHitProb(int enemyDef){                // need this to show to player
        int total = this.getAccuracy() + enemyDef;                                                   // take total first      
        int prob_thresh = (int) Math.floor((double)this.getAccuracy()/total*100);                    // calculate hit threshold for roll
        return prob_thresh;
    }
    
    public int rollHit(int enemyDef){
        int hit = 0;                                                                      // 0 for miss, 1 for hit, 2 for crit         
        int prob_thresh = this.calcHitProb(enemyDef);                                      // divide your acc by total to calculate prob
        int hit_roll = randgen.nextInt(101);                                       //randomly roll out of 100
        if (hit_roll <= prob_thresh){
            hit = 1; // hit if the roll is inside the threshold
            if (hit_roll==0){
                hit = 2; // Crit on a 0
                System.out.println("Critical Hit!");
            }
        }
        return hit;
    }

    public int attack(int enemyDef, String enemyWeakness){                    //return dmg done
        int mod = 1;                                                          //type dmg modifier
        if (this.getType().equalsIgnoreCase(enemyWeakness)){                  //check weakness
            mod = 2;
        }

        int hit = this.rollHit(enemyDef);
        int dmg = this.rollDMG()*hit*mod;
        if (hit == 2){
            dmg = mod*hit*this.getDiceNum()*this.getDiceType();
        }
        return dmg;
    }

    public static void main(String[] args) {
        Spell fireball = new Spell("Fireball" , "Water", 1 , 8, 1000);
        int sum = 0;
        for (int i = 0; i<10; i++){
            System.out.println(fireball.attack(1000, "Fire"));
        }
        
    }
}
