package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Enemy extends Character {
    
    
    public void enemyAttackPhase(Character player) {
        if (this.healthCurrent > 0) {
            System.out.println("\n- " + this.name + "'s move -");
            if (player.isStunned == true ) {
               this.attack(player);
            } else {
                int enemyChoice = random(5, 1);
                if (enemyChoice >= 1 && enemyChoice < 4) {
                this.attack(player);
                } else if (enemyChoice == 4) {
                this.defend();
                } else if (enemyChoice == 5) {
                    if (this.healthCurrent <= (this.healthCap *.3) && this.healthPotions > 0) {
                        this.useHealthPotion();
                    } else {
                        this.attack(player);
                    }
                }
            }
        }    
    }
}
