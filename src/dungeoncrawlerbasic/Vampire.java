package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Vampire extends Enemy{
    public Vampire() {
        this.name = "Vampire";
        this.healthCap = random(7, 8);
        this.damage = random(4, 4);
        this.armor = random(3, 1);
        this.speed = random(3, 3);
        this.weaponEffects.add("Vampire");
        this.healthPotions = random(3, 1);
        
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
            opponent.weaponEffects.add("Vampire");       
        }    
    }
}
