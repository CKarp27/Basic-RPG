/**
 * Tests the Spell class 
 * @author Connor Karpinski
 * @version 1.0
 * 
 */
package BasicRPG;
import BasicRPG.Spell.SpellType;

public class SpellTester {
    public static void main(String[] args) {
        Spell testSpell = new Spell("Test spell", 6, 2, 1000, SpellType.FIRE);
        Spell testSpell2 = GameTool.createSpell();
        System.out.println("The toStringMethod uses the getters");
        System.out.println(testSpell);  
        System.out.println(testSpell2);  
        System.out.println("Check if damage rolls are reasonable");
        for(int i = 0; i<5; i++){
            System.out.println(testSpell.rollDMG());
        }
        System.out.println("Probability to hit equal defense: "+testSpell.calcHitProb(1000));
        System.out.println(testSpell.toStringShort());
        System.out.println("Try attacking");
        for(int i = 0; i<4; i++){
            System.out.println(testSpell.castSpell(1000, SpellType.AIR));
        }
        System.out.println("Try with type advantage now (double damage)");
        for(int i = 0; i<4; i++){
            System.out.println(testSpell.castSpell(1000, SpellType.FIRE));
        }
    }
}
