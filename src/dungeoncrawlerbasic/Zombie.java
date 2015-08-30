package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Zombie extends Character{
    public Zombie() {
        this.name = "Zombie";
        this.healthCap = random(6, 6);
        this.damage = random(3, 2);
        this.armor = random(3, 1);
        this.speed = random(2, 2);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
