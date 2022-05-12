/**
 * Defines an enemy for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */


public class Enemy {
    private String name;
    private String spellTypeWeakness;
    private String weaponTypeWeakness;
    private int health;
    private Weapon weapon;
    private Spell spell;
    private int action_speed;

    public Enemy(String name, String spelltype, String weapontype, int health, Weapon weapon, Spell spell){
        this.name = name;
        this.spellTypeWeakness = spelltype;
        this.weaponTypeWeakness = weapontype;
        this.health = health;
        this.weapon = weapon;
        this.spell = spell;
    }

    //GETTERS

    public String getName(){
        return this.name;
    }

    public String getSpellTypeWeakness(){
        return this.spellTypeWeakness;
    }

    public String getWeaponTypeWeakness(){
        return this.weaponTypeWeakness;
    }

    public int getHealth(){
        return this.health;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public Spell getSpell(){
        return this.spell;
    }

    public int getActionSpeed(){
        return this.action_speed;
    }



}
