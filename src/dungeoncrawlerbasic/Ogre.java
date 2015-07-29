package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Ogre extends Character{
    public Ogre() {
        this.name = "Ogre";
        this.healthCap = random(50, 125);
        this.damage = random(15, 20);
        this.armor = random(10, 7);
        this.speed = random(4, 2);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
;    }
}
