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
        super(name, equipped_weapon, equipped_spell, max_mana, max_health, action_speed);
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
        this.defense = defense;
    }

    //GETTERS

    public SpellType getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public MeleeType getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

    public void attack(Character target){
        target.takeDMG(this.getEquippedWeapon().attack(target.getDefense()));
    }

    public void castSpell(Character target){
        target.takeDMG(this.getEquippedSpell().castSpell(target.getDefense()));
    }

    public String toString(){
        return (this.getName() + ": \n" + "Max Health: " + this.getMaxHealth() + "\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString());
    }
}
