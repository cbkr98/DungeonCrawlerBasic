package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Zombie extends Character{
    public Zombie() {
        this.name = "Zombie";
        this.healthCap = random(15, 35);
        this.damage = random(10, 6);
        this.armor = random(3, 2);
        this.speed = random(2, 1);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
