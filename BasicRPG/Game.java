package BasicRPG;
import java.util.ArrayList;
public class Game {
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    public void fillList(){
        for (int i = 0; i<2 ; i++){
            enemyList.add(GameTool.createEnemy());
        }
    }
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.fillList();
        Enemy enemy = GameTool.createEnemy();
        Character player = new Character("Drax the Mighty", 100, 100, 2, 5, 1000);
        player.addItem(new HealingPotion(2, 10));
        player.addItem(new ManaPotion(1, 20));
        
        for (Enemy temp : myGame.enemyList){
            GameTool.battle(player, temp);
        }
    }
}
