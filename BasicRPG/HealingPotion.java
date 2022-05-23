/**
 * Defines an healing potion for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */

package BasicRPG;

public class HealingPotion extends Item {
    private int healVal;

    public HealingPotion(int maxCharges, int healVal){
        super(maxCharges);
        this.healVal = healVal;
        this.name = "Health Potion";
    }

    public int getHealVal(){
        return this.healVal;
    }

    public void use(Character player){
        this.consumeCharge();
        player.heal(this.getHealVal());
    }

    public String showEffect(){
        return ("Heals for " + this.getHealVal() + " health");
    }
}
