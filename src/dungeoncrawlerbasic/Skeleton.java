package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Skeleton extends Character {
    public Skeleton() {
        this.name = "Skeleton";
        this.healthCap = random(3, 3);
        this.damage = random(4, 1);
        this.speed = random(3, 2);
        this.armor = random(3, 0);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
