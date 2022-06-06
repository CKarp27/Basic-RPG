/**
 * Defines an item for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */


package BasicRPG;

public abstract class Item {
    protected String name;
    protected int maxCharges;
    protected int charges;
    protected int duration;
    
    public Item(int maxCharges){
        this.maxCharges = maxCharges;
        this.charges = maxCharges;
    }

    public void consumeCharge(){ // want to track charges so we can remove from backpack when 0 charges are left
        this.charges--;
    }

    public int getCharges(){
        return this.charges;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return (this.name + " "+ this.charges + "/" + this.maxCharges + " uses - Effect: " + this.showEffect());
    }

    public abstract void use(Character player);
    public abstract String showEffect();
}
