/**
 * Defines a character for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */

package BasicRPG;
import java.util.ArrayList;


public class Character extends Entity {
    private int lvl;
    private ArrayList<Spell> known_spells;
    private ArrayList<Weapon> arsenal;
    //private ArrayList<Item> backpack;

    public Character(String name, Weapon equipped_weapon, Spell equipped_spell, int max_mana, int max_health, int action_speed, int lvl){
        super(name, equipped_weapon, equipped_spell, max_mana, max_health, action_speed);
        this.lvl = lvl;
    }

    public int getLVL(){
        return this.lvl;
    }

    public void setLVL(int lvl){
        this.lvl = lvl;
    }

    public void lvlUP(){
        this.setLVL(this.getLVL()+1); 
    }

    public int calcMaxHP(){
        return 100 + this.getLVL()*50;     // Make HP equal to 100 + 50 * lvl
    }

    public void addWeapon(Weapon wep){
        this.arsenal.add(wep);
    }

    public void learnSpell(Spell spell){
        this.known_spells.add(spell);
    }

    public void attack(Enemy target){
        target.takeDMG(this.getEquippedWeapon().attack(target.getDefense(), target.getWeaponTypeWeakness()));
    }
    public void castSpell(Enemy target){
        target.takeDMG(this.getEquippedSpell().castSpell(target.getDefense(), target.getSpellTypeWeakness()));
    }
}
