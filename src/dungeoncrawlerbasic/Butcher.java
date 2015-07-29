package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Butcher extends Character{
    public Butcher() {
        this.name = "Butcher";
        this.healthCap = random(30, 50);
        this.damage = random(10, 20);
        this.armor = random(5, 5);
        this.speed = random(3, 2);
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
