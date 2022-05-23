/**
 * Defines a character for an RPG
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */

package BasicRPG;
import java.util.ArrayList;
import java.util.Scanner;

import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;

public class Character extends Entity {
    private int lvl;
    private ArrayList<Spell> spellbook = new ArrayList<Spell>();
    private ArrayList<Weapon> arsenal = new ArrayList<Weapon>();
    private ArrayList<Item> backpack = new ArrayList<Item>();

    public Scanner kb = new Scanner(System.in);

    public Character(String name, int max_mana, int max_health, int action_speed, int lvl, int defense){
        super(name, max_mana, max_health, action_speed, defense);
        this.lvl = lvl;
        this.arsenal.add(new Weapon("Stick", 6, 1, 600, MeleeType.BLUNT));
        this.spellbook.add(new Spell("Fire Bolt", 8, 1, 500, SpellType.FIRE));
        this.equipped_weapon = this.arsenal.get(0);
        this.equipped_spell = this.spellbook.get(0);
    }
    
    public int getLVL(){
        return this.lvl;
    }

    public void setLVL(int lvl){
        this.lvl = lvl;
    }

    public String toString(){
        return (this.getName() + ": \n" + "Health: " + this.getHealth()+"/"+this.getMaxHealth() + "\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString()+"\n");
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
        this.spellbook.add(spell);
    }

    public void addItem(Item item){
        this.backpack.add(item);
    }

    public void attack(Enemy target){
        int DMG = this.getEquippedWeapon().attack(target.getDefense(), target.getWeaponTypeWeakness());
        System.out.printf("\nYou dealt %d damage with %s!\n\n", DMG, this.getEquippedWeapon().getName());
        target.takeDMG(DMG);
    }

    public void castSpell(Enemy target){
        int DMG = this.getEquippedSpell().castSpell(target.getDefense(), target.getSpellTypeWeakness());
        System.out.printf("\nYou dealt %d damage with %s!\n\n", DMG, this.getEquippedSpell().getName());
        target.takeDMG(DMG);
    }

    public void showArsenal(){
        int i = 0;
        for (Weapon tempWeapon : this.arsenal){
            System.out.println(i+ " - " + tempWeapon);
            i++;
        }
    }
    
    public void showSpellbook(){
        int i=0; //need to show index for player input
        for (Spell tempSpell : this.spellbook){
            System.out.println(i+ " - " +tempSpell);
            i++;
        }
    }

    public void showBackpack(){
        int i=0; //need to show index for player input
        for (Item tempItem : this.backpack){
            System.out.println(i+ " - " +tempItem);
            i++;
        }
    }

    public void changeWeapon(){
        this.showArsenal();
        int i = -1;
        while (i<0 || i>=this.arsenal.size()){
            System.out.println("Type the number of the weapon you would like to switch to then press enter");
            i = kb.nextInt();
        }
        this.setWeapon(this.arsenal.get(i));
    }

    public void changeSpell(){
        this.showSpellbook();
        int i = -1;
        while (i<0 || i>=this.spellbook.size()){
            System.out.println("Type the number of the spell you would like to switch to then press enter");
            i = kb.nextInt();
        }
        this.setSpell(this.spellbook.get(i));
    }

    public void removeItem(Item item){    // removes item from the backpack
        this.backpack.remove(item);
    }

    public void checkItemCharges(Item item){
        int charges = item.getCharges();
        if (charges <= 0){
            this.removeItem(item);
        }
    }

    public void useItem(){
        this.showBackpack();
        int i = -1;
        while (i<0 || i>=this.backpack.size()){
            System.out.println("Type the number of the item you would like to use to then press enter");
            i = kb.nextInt();
        }
        this.use(this.backpack.get(i));
    }

    public void use(Item item){
        item.use(this);
        this.checkItemCharges(item);
    }

}
