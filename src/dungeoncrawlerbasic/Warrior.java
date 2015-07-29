package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Warrior extends Character{
    public Warrior() {
        this.name = "Warrior";
        this.healthCap = random(20, 55);
        this.damage = random(15, 15);
        this.armor = random(5, 3);
        this.speed = random(4, 3);
        this.healthPotions = random(4, 2);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
