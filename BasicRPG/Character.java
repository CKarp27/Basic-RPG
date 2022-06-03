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
    private int lvl, exp, reqExp; //current experience, exp for next level
    private boolean actionUsed; //will track when a turn is done
    private ArrayList<Spell> spellbook = new ArrayList<Spell>();
    private ArrayList<Weapon> arsenal = new ArrayList<Weapon>();
    private ArrayList<Item> backpack = new ArrayList<Item>();

    public Scanner kb = new Scanner(System.in);

    public Character(String name, int maxMana, int actionSpeed, int lvl, int defense){
        super(name, maxMana, 10, actionSpeed, defense);
        this.setLVL(lvl);
        this.arsenal.add(new Weapon("Stick", 6, 1, 600, MeleeType.BLUNT));
        this.spellbook.add(new Spell("Fire Bolt", 8, 1, 500, SpellType.FIRE));
        this.equippedWeapon = this.arsenal.get(0);
        this.equippedSpell = this.spellbook.get(0);
        this.actionUsed = false;
        this.updateReqExp();
    }
    
    public int getLVL(){
        return this.lvl;
    }

    public void setLVL(int lvl){
        this.lvl = lvl;
        this.setMaxHealth(this.calcMaxHP());
        this.setHealth(this.getMaxHealth()); //heal to full
    }

    public String toString(){
        return (this.getName() + " - LVL " +this.getLVL()+" (" + this.getExp()+"/"+this.getReqExp()+" EXP) : \n" + "Health: " + this.getHealth()+"/"+this.getMaxHealth() + 
        "\t\tMana: " + this.getMana()+ "/"+this.getMaxMana()+"\nWeapon: " + this.getEquippedWeapon().toString() + "\nSpell: " + this.getEquippedSpell().toString());
    }

    public void consumeAction(){
        this.actionUsed = true;
    }

    public void restoreAction(){
        this.actionUsed = false;
    }

    public boolean getActionStatus(){
        return this.actionUsed;
    }

    public void lvlUP(){
        this.setLVL(this.getLVL()+1);
        System.out.println("Level Up! "+this.getName()+" is now LVL "+this.getLVL());
        this.exp = 0; 
    }

    public int getExp(){
        return this.exp;
    }

    public int getReqExp(){
        return this.reqExp;
    }

    public void setExp(int newExp){
        this.exp = newExp;
    }

    public void setReqExp(int reqExp){
        this.reqExp = reqExp;
    }

    public void addExp(int expGained){
        int newTotal = (this.getExp() + expGained);
        if (this.getReqExp()-newTotal>=0){
            this.lvlUP();
            this.setExp(this.getReqExp()-newTotal);
        }else{
            this.setExp(newTotal);
        }
    }

    public void updateReqExp(){
        int formula = 100+(int)Math.pow((double)(this.getLVL()-1), 2)*10; //formula to get experience required for new level
        this.setReqExp(formula);
    }

    public int calcMaxHP(){
        return 20 + this.getLVL()*20;     // Make HP equal to 50 + 50 * lvl
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
        if (DMG>0){
            System.out.printf("You dealt %d damage with %s!\n", DMG, this.getEquippedWeapon().getName());
            target.takeDMG(DMG);
        }else System.out.println("You missed!");
        this.consumeAction();
    }

    public void castSpell(Enemy target){
        if(this.getMana()-this.getEquippedSpell().getManaCost()<0){
            System.out.println("Not enough mana for this spell");
        }else{
            int DMG = this.getEquippedSpell().castSpell(target.getDefense(), target.getSpellTypeWeakness());
            if (DMG>0){
                System.out.printf("You dealt %d damage with %s!\n", DMG, this.getEquippedSpell().getName());
                target.takeDMG(DMG);
            }else System.out.println("You missed!");
            this.consumeAction();
            this.setMana(this.getMana()-this.equippedSpell.getManaCost());
        }
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
        int i = -2;
        while (i<-1 || i>=this.arsenal.size()){
            System.out.println("Type the number of the weapon you would like to switch to then press enter\nType -1 and hit enter to perform a different action");
            i = kb.nextInt();
        }
        if (i>=0){
            this.setWeapon(this.arsenal.get(i));
        }
    }

    public void changeSpell(){
        this.showSpellbook();
        int i = -2;
        while (i<-1 || i>=this.spellbook.size()){
            System.out.println("Type the number of the spell you would like to switch to then press enter\nType -1 and hit enter to perform a different action");
            i = kb.nextInt();
        }
        if (i>=0){
            this.setSpell(this.spellbook.get(i));
        }
        
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
        int i = -2;
        while (i<-1 || i>=this.backpack.size()){
            System.out.println("Type the number of the item you would like to use to then press enter\nType -1 and hit enter to perform a different action");
            i = kb.nextInt();
        }
        if (i>=0){
            this.use(this.backpack.get(i));
        }
        
    }

    public void use(Item item){
        item.use(this);
        this.checkItemCharges(item);
        this.consumeAction();
    }

}
