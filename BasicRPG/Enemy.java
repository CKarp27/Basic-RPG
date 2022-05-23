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

    public Enemy(String name, Weapon equipped_weapon, Spell equipped_spell, int max_mana, int max_health, int action_speed, SpellType spelltype, MeleeType weapontype, int defense){
        super(name, max_mana, max_health, action_speed, defense);
        this.equipped_weapon = equipped_weapon;
        this.equipped_spell = equipped_spell;
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
    }

    //GETTERS

    public SpellType getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public MeleeType getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

    public void showWeakness(){
        System.out.println("\nMelee weakness : "+this.getWeaponTypeWeakness());
        System.out.println("Spell weakness : "+this.getSpellTypeWeakness());
        System.out.println("Defense : " + this.getDefense()+"\n");
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
        return (this.getName() + ": \n" + "Max Health: " + this.getHealth()+"/"+this.getMaxHealth() + "\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString()+"\n");
    }
}
