package BasicRPG;

public class Game {
    public static void main(String[] args) {
        Enemy enemy = GameTool.createEnemy();
        Character player = new Character("Drax the Mighty", 100, 100, 2, 5, 1000);
        player.addItem(new HealingPotion(2, 10));
        player.addItem(new ManaPotion(1, 20));
        GameTool.battle(player, enemy);
    }
}
