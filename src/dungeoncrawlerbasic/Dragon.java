package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Dragon extends Enemy{
    public Dragon() {
        this.name = "Dragon";
        this.healthCap = random(20, 30);
        this.damage = random(12, 15);
        this.armor = random(7, 5);
        this.speed = random(5, 4);
        this.weaponEffects.add("Burn");
        this.healthPotions = random(4, 4);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
