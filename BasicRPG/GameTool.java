/**
 * Game tools for an RPG such as creating enemy, to be called without an instance of the class
 * @author Connor Karpinski
 * @version 1.0
 * 
 * 
 */
package BasicRPG;
import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;
import java.util.Random;

public final class GameTool {
    static Random randgen = new Random();
    final static String[] enemy_names = {"Bugbear","Orc","Goblin"};
    final static String[] weapon_names = {"Axe","Spear","Sword","Dagger","Hammer","Greatsword"};         // change to 2d array with catagories by weapon type, same for spells
    final static String[] spell_names = {"Fireball", "Lightning Strike", "Hail", "Magic Missile"};


    public static Weapon createWeapon(){
        int dtype = (randgen.nextInt(5))*2+4; //allows possible values of 4 , 6 , 8 , 10 , 12 for dice
        int dnum = 1;
        if (dtype<=6){
            dnum = 1+randgen.nextInt(2); // can get a second die if type is a d4 or d6
        }
        String name = weapon_names[randgen.nextInt(weapon_names.length)];
        MeleeType type = MeleeType.getRandomType();
        int accuracy = 800 + randgen.nextInt(401); // range 800 - 1200
        return new Weapon(name, dtype, dnum, accuracy, type);
    }

    public static Spell createSpell(){
        int dtype = (randgen.nextInt(5))*2+4; //allows possible values of 4 , 6 , 8 , 10 , 12 for dice
        int dnum = 1+randgen.nextInt(3);
        if (dtype<=6){
            dnum = 3+randgen.nextInt(4); // can get more dice with d4 or d6
        }
        String name = spell_names[randgen.nextInt(spell_names.length)];
        SpellType type = SpellType.getRandomType();
        int accuracy = 800 + randgen.nextInt(401); // range 800 - 1200
        return new Spell(name, dtype, dnum, accuracy, type);
    }

    public static Enemy createEnemy(){                                          // create random enemy to fight
        SpellType SpellWeakness = SpellType.getRandomType();
        MeleeType MeleeWeakness = MeleeType.getRandomType();
        String name = enemy_names[randgen.nextInt(enemy_names.length)];
        int action_speed = 1;                                                   // change later if more speeds wanted
        int health = 20 + randgen.nextInt(50);
        Weapon wep = GameTool.createWeapon();
        Spell spell = GameTool.createSpell();
        return new Enemy(name, SpellWeakness, MeleeWeakness, health, action_speed, wep, spell);
    }
}
