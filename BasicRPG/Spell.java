/**
 * Defines a spell for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */
package BasicRPG;
import java.util.Random;

public class Spell {
    String name;
    Random randgen = new Random();
    enum SpellType{
        FIRE,
        WATER,
        GROUND,
        AIR;

        public static SpellType getRandomType(){
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }
    }
    SpellType type;
    int diceNum; //(6 for d6, 8 for d8 etc.)
    int diceType;
    int accuracy;
    int manaCost;
    

    public Spell(String name, int diceType, int diceNum, int accuracy, SpellType type){ //Constructor
        this.name = name;
        this.type = type;
        this.diceNum = diceNum;
        this.diceType = diceType;
        this.accuracy = accuracy;
        this.calcManaCost();
    }

    public SpellType getType(){
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

    public int getManaCost(){
        return this.manaCost;
    }

    public String toStringShort(){
        return this.name+" ("+this.getDiceNum()+"d"+this.getDiceType()+" "+this.getType()+")"+"("+this.getManaCost()+" mana cost)";
    }
    
    public String toString(){
        return (this.getName() + " (costs "+this.getManaCost()+" mana), uses " + this.getDiceNum() + "d" + this.getDiceType() +"(s) with type " + this.getType() + " and an accuracy of " + this.getAccuracy());
    }
    
    public void calcManaCost(){
        this.manaCost = (int) Math.sqrt(4*(double)this.diceNum*(double)this.diceType);
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

    //player cast spell
    public int castSpell(int enemyDef, SpellType enemyWeakness){                    //return dmg done
        int mod = 1;                                                          //type dmg modifier
        if (this.getType()==enemyWeakness) {                                //check weakness
            mod = 2;
        }

        int hit = this.rollHit(enemyDef);
        int dmg = this.rollDMG()*hit*mod;
        if (hit == 2){
            dmg = mod*hit*this.getDiceNum()*this.getDiceType();
        }
        return dmg;
    }

    //enemy cast spell
    public int castSpell(int playerDef){                    //return dmg done
        int hit = this.rollHit(playerDef);
        int dmg = this.rollDMG()*hit;
        if (hit == 2){
            dmg = hit*this.getDiceNum()*this.getDiceType();
        }
        return dmg;
    }

}
