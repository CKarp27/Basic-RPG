/**
 * Defines an RPG game to be played 
 * @author Connor Karpinski
 * @version 1.0
 * 
 */

package BasicRPG;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;

public class Game {
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    Scanner kb = new Scanner(System.in);
    Character activePlayer;

    public void fillList(int i){
        for (int j = 0; j<i ; j++){
            enemyList.add(GameTool.createEnemy());
        }
    }

    public void setCharacter(Character player){
        this.activePlayer = player;
    }

    public Character getCharacter(){
        return this.activePlayer;
    }

    public void makeWeapon(Character Player){
        boolean done = false;
        while (!done){
            try{
                System.out.println("\nWhat is the name of the weapon?");
                kb.nextLine();
                String name = kb.nextLine();
                System.out.println("What type of die would you like it to have (ex: 4 , 6 , 8)");
                int diceType = kb.nextInt();
                System.out.println("How many dice would you like it to have? (Suggested range: 1 to 3)");
                int diceNum = kb.nextInt();
                System.out.println("What would you like the accuracy to be (Suggested range: 500 to 1000)");
                int accuracy = kb.nextInt();
                System.out.println("What type would you like the weapon to be (blunt, stab, slash)");
                kb.nextLine();
                String typeEntry =kb.nextLine();
                MeleeType type = MeleeType.valueOf(typeEntry.toUpperCase());
                Player.addWeapon(new Weapon(name, diceType, diceNum, accuracy, type));
                done = true;
            }catch (InputMismatchException ex){
                System.out.println("Please retry and be sure to enter integers for number entries");
            }catch (IllegalArgumentException ex2){
                System.out.println("Please retry and be sure to the weapon type spelled correctly");
            }
        }
    }

    public void makeSpell(Character Player){
        boolean done = false;
        while (!done){
            try{
                System.out.println("\nWhat is the name of the spell?");
                // kb.nextLine();
                String name = kb.nextLine();
                System.out.println("What type of die would you like it to have (ex: 4 , 6 , 8)");
                int diceType = kb.nextInt();
                System.out.println("How many dice would you like it to have? (Suggested range: 2 to 4)");
                int diceNum = kb.nextInt();
                System.out.println("What would you like the accuracy to be (Suggested range: 500 to 1000)");
                int accuracy = kb.nextInt();
                System.out.println("What type would you like the spell to be (fire, water, ground, air)");
                kb.nextLine();
                String typeEntry =kb.nextLine();
                SpellType type = SpellType.valueOf(typeEntry.toUpperCase());
                Player.learnSpell(new Spell(name, diceType, diceNum, accuracy, type));
                done = true;
            }catch (InputMismatchException ex){
                System.out.println("Please retry and be sure to enter integers for number entries");
            }catch (IllegalArgumentException ex2){
                System.out.println("Please retry and be sure to the spell type spelled correctly");
            }
        }
    }

    public void makeCharacter(){
        boolean done = false;
        while (!done){
            try{
                System.out.println("\nWhat would you like your Character's name to be?");
                kb.nextLine();
                String name = kb.nextLine();
                System.out.println("How much mana would you like your Character to have? (Suggested range: 80-200)");
                int mana = kb.nextInt();
                System.out.println("What would you like your Character's action speed to be (this determines how many actions you perform per turn) (Suggested: 1 or 2 )");
                int actionSpeed = kb.nextInt();
                System.out.println("What level would you like your Character to be? (Suggested range : 1-20)");
                int level = kb.nextInt();
                System.out.println("How much defense would you like your Character to have? (Suggested range: 800-1200)");
                int defense =kb.nextInt();
                this.activePlayer= new Character(name, mana, actionSpeed, level, defense);
                done = true;
            }catch (InputMismatchException ex){
                System.out.println("Please retry and be sure to enter integers for number entries");
            }catch (IllegalArgumentException ex2){
                System.out.println("Please retry and be sure to the spell type spelled correctly");
            }
        }
    }

    public static void main(String[] args) {
        Game myGame = new Game();
        System.out.println("Welcome to my Basic RPG game, follow the instructions to set up your Character and enemies, then you will fight until you complete all the enemies");
        System.out.println("\nHow many enemies would you like to fight? (Suggested range : 1-5)");
        int enemyCount = myGame.kb.nextInt();
        myGame.fillList(enemyCount);

        // myGame.setCharacter(new Character("Drax the Mighty", 100, 2, 5, 1000)); //testing fake character
        
        System.out.println("You will now create a character");
        myGame.makeCharacter();
        System.out.println("You will now create a weapon for "+myGame.getCharacter().getName());
        myGame.makeWeapon(myGame.getCharacter());
        System.out.println("You will now create a spell for "+myGame.getCharacter().getName());
        myGame.makeSpell(myGame.getCharacter());

        System.out.println("Your Character has been given a healing and mana potion to help");
        myGame.getCharacter().addItem(new HealingPotion(2, 50));
        myGame.getCharacter().addItem(new ManaPotion(2, 50));
        System.out.println("Fighting begins now");
        for (Enemy aciveEnemy : myGame.enemyList){
            GameTool.battle(myGame.getCharacter(), aciveEnemy);
            if (!myGame.getCharacter().checkVitals()){ //end game when character dies
                break;
            }
        }
        System.out.println("Thank you for playing!");
    }
}
