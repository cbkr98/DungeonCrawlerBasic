package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Warrior extends Character{
    public Warrior() {
        this.name = "Warrior";
        this.healthCap = random(8, 10);
        this.damage = random(4, 3);
        this.armor = random(4, 2);
        this.speed = random(3, 3);
        this.healthPotions = random(3, 2);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
