package dungeoncrawlerbasic;
import java.util.*;

public class DungeonCrawlerBasic {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        enterDungeon();
        
        Player player = new Player();
        player = Player.chooseClass();
        player.chooseSpell();
        
        battle:
        while (player.isAlive()) {
            Enemy enemy = player.enemyChooser();
            enemy.improveStatsByLevel(player);
            System.out.println("");
            System.out.println("\t# " + enemy.name + " has arrived #");
            
            newRound:
            while (player.isAlive() && enemy.isAlive()) {
                Character.preRoundSetup(player, enemy);
                
                while (player.attempt == false) {
                    player.startTurn();
                    if (!player.isStunned) {
                        player.playerAttackPhase(enemy);
                    } else {
                        player.stunned();
                    }
                    player.endTurn();
                }    
                
                enemy.startTurn();
                if (!enemy.isStunned) {
                    enemy.enemyAttackPhase(player);
                } else {
                    enemy.stunned();
                }
                enemy.endTurn();
            }
            
            if (player.healthCurrent > 0) {
                player.defeatedEnemy(enemy);
            } else {
                System.out.println("Oh well, you died. Better luck next time.");
            }
        }
    }    
    
    
    
    public static void delay(int a) {
        try {
            Thread.sleep(a);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static int random(int a, int b) {
        int x = (int)((Math.random()*a)+b);
        return x;
    }
    public static void enterDungeon() {
        System.out.println("\tYou have entered the dungeon!");
        System.out.println("-------------------------------------");
        delay(1500);
    }
}