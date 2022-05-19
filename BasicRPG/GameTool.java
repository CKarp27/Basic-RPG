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
import java.util.Scanner;

public final class GameTool {
    static Random randgen = new Random();
    static Scanner kb = new Scanner(System.in);
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
        int accuracy = 400 + randgen.nextInt(401); // range 800 - 1200
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
        int accuracy = 400 + randgen.nextInt(401); // range 800 - 1200
        return new Spell(name, dtype, dnum, accuracy, type);
    }

    public static Enemy createEnemy(){                                          // create random enemy to fight
        SpellType SpellWeakness = SpellType.getRandomType();
        MeleeType MeleeWeakness = MeleeType.getRandomType();
        String name = enemy_names[randgen.nextInt(enemy_names.length)];
        int action_speed = 1;                                                   // change later if more speeds wanted
        int health = 20 + randgen.nextInt(50);
        int mana = 20 + randgen.nextInt(50);
        Weapon wep = GameTool.createWeapon();
        Spell spell = GameTool.createSpell();
        int defense = 300 + randgen.nextInt(500);
        return new Enemy(name, wep, spell, mana, health, action_speed, SpellWeakness, MeleeWeakness, defense);
    }

    public static void Battle(Character player, Enemy enemy){
        showBattleStatus(player, enemy);
        while (player.checkVitals() && enemy.checkVitals()){
            playerTurn(player,enemy);
            if (!enemy.checkVitals()) break;
            showBattleStatus(player, enemy);
            enemyTurn(player,enemy);
            showBattleStatus(player, enemy);
        }
    }

    public static void showBattleStatus(Character player, Enemy enemy){
        System.out.println(player);
        System.out.println(enemy);
    }

    public static void playerTurn(Character player, Enemy enemy){
        boolean flag = false;    //used action flag
        while (!flag){
            System.out.println("What would you like to do for your turn action? Type the number of the action");
            System.out.println("1 - Attack with current weapon " + player.getEquippedWeapon().toString());
            System.out.println("2 - Cast current spell " + player.getEquippedSpell().toString());
            System.out.println("3 - Use an item ");
            System.out.println("4 - Change Current Weapon (Does not cost turn action)");
            System.out.println("5 - Change Current Spell (Does not cost turn action)");
            System.out.println("6 - Check enemy weaknesses");
            int choice = kb.nextInt();
            switch (choice){
                case 1: player.attack(enemy);
                        flag = true;
                        break;
                case 2: player.castSpell(enemy);
                        flag = true;
                        break;
                //case 3: player.useItem()
                        // flag = true;
                        // break;
                case 4: player.changeWeapon();
                        break;
                case 5: player.changeSpell();
                        break;
                case 6: enemy.showWeakness();
                        flag = true;
                        break;
            }
        }
    }

    public static void enemyTurn(Character player, Enemy enemy){
        int i = randgen.nextInt(2);
        switch (i){
            case 0: enemy.attack(player);
                    break;
            case 1: enemy.castSpell(player);
                    break;
        }

    }
}
