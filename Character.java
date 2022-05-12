/**
 * Defines a character for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */



public class Character {
    private String name;
    private int lvl;
    private Weapon equipped_weapon;
    private Spell equipped_spell;
    private int action_speed; // 1 is normal, 2 means two actions before enemy 
    private int max_mana;
    private int max_health;
    private int current_health;
    private int current_mana;
    private Spell[] known_spells;
    private Item[] backpack;

    public String getName(){
        return this.name;
    }

    public int getLVL(){
        return this.lvl;
    }

    public Weapon getEquippedWeapon(){
        return this.equipped_weapon;
    }

    public Spell getEquippedSpell(){
        return this.equipped_spell;
    }

    public int getActionSpeed(){
        return this.action_speed;
    }

    public int getMaxMana(){
        return this.max_mana;
    }

    public int getMaxHealth(){
        return this.max_health;
    }

    public int getHealth(){
        return this.current_health;
    }

    public int getMana(){
        return this.current_mana;
    }
}
