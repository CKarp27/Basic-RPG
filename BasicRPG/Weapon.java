/**
 * Defines a weapon for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */
package BasicRPG;
 import java.util.Random;


public class Weapon {
    int diceType;
    int diceNum;
    String name;
    int accuracy;
    enum MeleeType{
        BLUNT,
        STAB,
        SLASH;

        public static MeleeType getRandomType(){
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }
    MeleeType type;
    public Random randgen = new Random();

    public Weapon(String name, int diceType, int diceNum, int accuracy, MeleeType type){
        this.name = name;
        this.diceType = diceType;
        this.diceNum = diceNum;
        this.accuracy = accuracy;
        this.type = type;
    }

    public MeleeType getType(){
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
    
    public String toString(){
        return (this.getName() + " , uses " + this.getDiceNum() + "d" + this.getDiceType() +"(s) with type " + this.getType() + " and an accuracy of " + this.getAccuracy());
    }
   
    public int rollDMG(){
        int sum = 0;
        for (int i=0; i<diceNum; i++){
            sum += 1+randgen.nextInt(diceType);
        }
        return sum;
    }

    public int rollDMG(int buff){
        int sum = 0;
        for (int i=0; i<this.getDiceNum(); i++){
            sum += 1+randgen.nextInt(diceType);
        }
        return sum+buff;
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

    //player attack
    public int attack(int enemyDef, MeleeType enemyWeakness){                    //return dmg done
        int mod = 1;                                                          //type dmg modifier
        if (this.getType()==enemyWeakness){                  //check weakness
            mod = 2;
        }

        int hit = this.rollHit(enemyDef);
        int dmg = this.rollDMG()*hit*mod;
        if (hit == 2){
            dmg = mod*hit*this.getDiceNum()*this.getDiceType();
        }
        return dmg;
    }

    //enemy attack
    public int attack(int playerDef){                    //return dmg done
        int hit = this.rollHit(playerDef);
        int dmg = this.rollDMG()*hit;
        if (hit == 2){
            dmg = hit*this.getDiceNum()*this.getDiceType();
        }
        return dmg;
    }
}
