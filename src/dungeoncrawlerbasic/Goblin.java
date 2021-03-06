package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Goblin extends Enemy {
    public Goblin() {
        this.name = "Goblin";
        this.healthCap = random(5, 5);
        this.damage = random(4, 3);
        this.armor = random(3, 1);
        this.speed = random(3, 3);
        this.healthPotions = random(3, 0);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
