package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Skeleton extends Character {
    public Skeleton() {
        this.name = "Skeleton";
        this.healthCap = random(15, 5);
        this.damage = random(5, 1);
        this.speed = random(2, 1);
        this.armor = random(3, 0);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
