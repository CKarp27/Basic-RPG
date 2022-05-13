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

    public Enemy(String name, SpellType spelltype, MeleeType weapontype, int health, int action_speed, Weapon weapon, Spell spell){
        this.name = name;
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
        this.max_health = health;
        this.equipped_weapon = weapon;
        this.equipped_spell = spell;
        this.action_speed = action_speed;
    }

    //GETTERS

    public SpellType getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public MeleeType getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

    public String toString(){
        return (this.getName() + ": \n" + "Max Health: " + this.getMaxHealth() + "\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString());
    }
}
