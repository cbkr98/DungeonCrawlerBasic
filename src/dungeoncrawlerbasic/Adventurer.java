package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Adventurer extends Character{
    public Adventurer() {
        this.name = "Adventurer";
        this.healthCap = random(5, 8);
        this.damage = random(2, 3);
        this.armor = random(5, 2);
        this.speed = random(3, 2);
        this.healthPotions = random(2, 2);
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;  
        
        maximizeStats();
    }
    
    @Override
    public void itemDrop(Character opponent) {
        super.itemDrop(opponent);
        int antiVenomGet = random(5, 1);
        if (antiVenomGet == 1) {
            opponent.antiVenomPotions += 1;
        }
    }    
}
