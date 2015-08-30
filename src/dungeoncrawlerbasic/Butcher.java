package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Butcher extends Character{
    public Butcher() {
        this.name = "Butcher";
        this.healthCap = random(7, 12);
        this.damage = random(3, 5);
        this.armor = random(5, 2);
        this.speed = random(3, 1);
        this.weaponEffects.add("Shred");
        this.healthPotions = random(2, 1);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
    
    @Override
    public void itemDrop(Character opponent) {
        super.itemDrop(opponent);
        int y = random(15, 1);
        if (y == 1) {
            opponent.weaponEffects.add("Shred");
        }    
    }
}
