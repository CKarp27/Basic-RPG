package BasicRPG;

public class Game {
    public static void main(String[] args) {
        Enemy enemy = GameTool.createEnemy();
        Character player = new Character("Drax the Mighty", 100, 100, 1, 5, 1000);
        GameTool.Battle(player, enemy);
    }
}
