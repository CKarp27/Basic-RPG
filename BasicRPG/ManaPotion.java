/**
 * Defines an mana potion for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */

package BasicRPG;

public class ManaPotion extends Item{
    private int restoreVal;

    public ManaPotion(int maxCharges, int restoreVal){
        super(maxCharges);
        this.restoreVal = restoreVal;
        this.name = "Mana Potion";
    }

    public int getRestoreVal(){
        return this.restoreVal;
    }

    public void use(Character player){
        this.consumeCharge();
        player.restoreMana(this.getRestoreVal());
    }

    public String showEffect(){
        return ("Restores " + this.getRestoreVal() + " mana");
    }
}
