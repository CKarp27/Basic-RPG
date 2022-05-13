/**
 * Defines an enemy for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 */
package BasicRPG;
import BasicRPG.Spell.SpellTypes;

public class Enemy extends Entity {
    SpellTypes spellTypeWeakness;
    private String weaponTypeWeakness;

    public Enemy(String name, SpellTypes spelltype, String weapontype, int health, int action_speed, Weapon weapon, Spell spell){
        this.name = name;
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
        this.max_health = health;
        this.equipped_weapon = weapon;
        this.equipped_spell = spell;
        this.action_speed = action_speed;
    }

    //GETTERS

    public String getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public String getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

}
