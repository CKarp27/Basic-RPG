/**
 * Tests the Weapon class 
 * @author Connor Karpinski
 * @version 1.0
 * 
 */

package BasicRPG;

import BasicRPG.Weapon.MeleeType;

public class WeaponTester {
    public static void main(String[] args) {
        Weapon testWeapon = new Weapon("Test wep", 6, 2, 1000, MeleeType.BLUNT);
        Weapon testWeapon2 = GameTool.createWeapon();
        System.out.println("The toStringMethod uses the getters");
        System.out.println(testWeapon);  
        System.out.println(testWeapon2);  
        System.out.println("Check if damage rolls are reasonable");
        for(int i = 0; i<5; i++){
            System.out.println(testWeapon.rollDMG());
        }
        System.out.println("Probability to hit equal defense: "+testWeapon.calcHitProb(1000));
        System.out.println(testWeapon.toStringShort());
        System.out.println("Try attacking");
        for(int i = 0; i<4; i++){
            System.out.println(testWeapon.attack(1000, MeleeType.SLASH));
        }
        System.out.println("Try with type advantage now(double damage)");
        for(int i = 0; i<4; i++){
            System.out.println(testWeapon.attack(1000, MeleeType.BLUNT));
        }
    }
}
