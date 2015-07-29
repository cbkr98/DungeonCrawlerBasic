package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Adventurer extends Character{
    public Adventurer() {
        this.name = "Adventurer";
        this.healthCap = random(15, 40);
        this.damage = random(6, 4);
        this.armor = random(5, 2);
        this.speed = random(3, 2);
        this.healthPotions = random(4, 2);
        
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
