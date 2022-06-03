package BasicRPG;
import java.util.ArrayList;
import java.util.Scanner;

import BasicRPG.Spell.SpellType;
import BasicRPG.Weapon.MeleeType;

public class Game {
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    Scanner kb = new Scanner(System.in);
    Character activePlayer;

    public void fillList(){
        for (int i = 0; i<2 ; i++){
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
        System.out.println("What is the name of the weapon?");
        String name = kb.nextLine();
        System.out.println("What type of dice would you like it to have (ex: 4 , 6 , 8)");
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
    }

    public void makeSpell(Character Player){
        System.out.println("What is the name of the spell?");
        String name = kb.nextLine();
        System.out.println("What type of dice would you like it to have (ex: 4 , 6 , 8)");
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
    }

    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.fillList();
        // Enemy enemy = GameTool.createEnemy();
        myGame.setCharacter(new Character("Drax the Mighty", 100, 2, 5, 1000));
        myGame.getCharacter().addItem(new HealingPotion(2, 50));
        myGame.getCharacter().addItem(new ManaPotion(1, 50));
        myGame.makeWeapon(myGame.getCharacter());
        myGame.makeSpell(myGame.getCharacter());

        for (Enemy temp : myGame.enemyList){
            GameTool.battle(myGame.getCharacter(), temp);
        }
    }
}
