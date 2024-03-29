/**
 * Defines an enemy for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 */
package BasicRPG;
import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;

public class Enemy extends Entity {
    SpellType spellTypeWeakness;
    MeleeType weaponTypeWeakness;
    int rewardExp;

    public Enemy(String name, Weapon equippedWeapon, Spell equippedSpell, int maxMana, int maxHealth, int action_speed, SpellType spelltype, MeleeType weapontype, int defense, int rewardExp){
        super(name, maxMana, maxHealth, action_speed, defense);
        this.equippedWeapon = equippedWeapon;
        this.equippedSpell = equippedSpell;
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
        this.rewardExp = rewardExp;
    }

    //GETTERS

    public SpellType getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public MeleeType getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

    public int getRewardExp(){
        return this.rewardExp;
    }

    public void showWeakness(){
        System.out.println("Melee weakness : "+this.getWeaponTypeWeakness());
        System.out.println("Spell weakness : "+this.getSpellTypeWeakness());
    }

    public void attack(Character target){
        int DMG = this.getEquippedWeapon().attack(target.getDefense());
        System.out.printf("\n%s dealt %d damage with %s!\n\n", this.getName(), DMG, this.getEquippedWeapon().getName());
        target.takeDMG(DMG);
    }

    public void castSpell(Character target){
        int DMG = this.getEquippedSpell().castSpell(target.getDefense());
        System.out.printf("\n%s dealt %d damage with %s!\n\n", this.getName(), DMG, this.getEquippedSpell().getName());
        target.takeDMG(DMG);
    }

    public String toString(){
        return (this.getName() + ": \n" + "Max Health: " + this.getHealth()+"/"+this.getMaxHealth() + "\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString());
    }
}
