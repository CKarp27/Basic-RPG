/**
 * Defines a character for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */

 import java.util.ArrayList;

public class Character extends Entity {
    private int lvl;
    private ArrayList<Spell> known_spells;
    private ArrayList<Weapon> arsenal;
    private ArrayList<Item> backpack;

    public int getLVL(){
        return this.lvl;
    }


}
