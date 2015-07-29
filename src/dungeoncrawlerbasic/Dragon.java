package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Dragon extends Character{
    public Dragon() {
        this.name = "Dragon";
        this.healthCap = random(125, 225);
        this.damage = random(25, 45);
        this.armor = random(20, 10);
        this.speed = random(6, 5);
        this.weaponEffects.add("Burn");
        this.healthPotions = random(4, 4);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
