/**
 * Tests the Character class and dependent methods for weapon, spell, healing potion, mana potion, healing potion
 * @author Connor Karpinski
 * @version 1.0
 * 
 */

package BasicRPG;
import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;

public class CharacterTester {
    public static void main(String[] args) {
        //test constructor and some getters and setters
        int mana = 100;
        int actSpeed = 1;
        int lvl = 5;
        int defense = 1000;
        Character testChar = new Character("Drax", mana, actSpeed, lvl, defense);
        System.out.println("Character level is : "+testChar.getLVL()+"\nShould be : "+lvl);
        System.out.println("Character action speed is : "+testChar.getActionSpeed()+"\nShould be : "+actSpeed);
        System.out.println("Character mana is : "+testChar.getMaxMana()+"\nShould be : "+mana);
        System.out.println("Character level is :"+testChar.getDefense()+"\nShould be : "+defense);
        System.out.println("\nShow toString with default equipped weapon and spell: \n"+testChar+"\n");

        int newLVL = 10;
        testChar.setLVL(newLVL);
        System.out.println("New level is : "+testChar.getLVL());
        System.out.println("End testing of getters and setters, focus on more complex methods\n");

        //test arsenal
        System.out.println("Show arsenal (available weapons)");
        testChar.showArsenal();
        Weapon testWep = new Weapon("Test Wep", 6, 3, 1000, MeleeType.BLUNT);
        testChar.addWeapon(testWep);
        System.out.println("\nShow arsenal (available weapons) after adding a test weapon");
        testChar.showArsenal();

        //test spellbook
        System.out.println("Show spellbook (available spells)");
        testChar.showArsenal();
        Spell testSpell = new Spell("Test Spell", 6, 3, 1000, SpellType.FIRE);
        testChar.learnSpell(testSpell);
        System.out.println("\nShow spellbook (available spells) after adding a test spell");
        testChar.showSpellbook();

        testChar.takeDMG(30);
        //test backpack
        testChar.addItem(new HealingPotion(2, 20));
        testChar.addItem(new HealingPotion(2, 20));
        testChar.addItem(new ManaPotion(2, 20));
        testChar.use(testChar.getBackpack().get(1));
        testChar.showBackpack();
        testChar.use(testChar.getBackpack().get(1));
        testChar.showBackpack(); //check removal of item

        System.out.println("alive : "+testChar.checkVitals());
        Enemy testEnemy = new Enemy("test Enemy", testWep, testSpell, 100, 100, 1, SpellType.AIR, MeleeType.BLUNT, 444, 20);
        testEnemy.showWeakness();
        //test weapon attack and spellcasting
        for (int i = 0; i<4; i++){
            testChar.attack(testEnemy);
        }
        for (int i = 0; i<4; i++){
            testEnemy.attack(testChar);
        }
        for (int i = 0; i<4; i++){
            testChar.castSpell(testEnemy);
        }
        for (int i = 0; i<4; i++){
            testEnemy.castSpell(testChar);
        }
    }
}
