package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Slime extends Enemy{
    public Slime() {
        this.name = "Slime";
        this.healthCap = random(4, 2);
        this.damage = random(3, 1);
        this.armor = random(0, 0);
        this.speed = random(3, 3);
        this.weaponEffects.add("Poison");
        this.healthPotions = random(0, 0);
        
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
            opponent.weaponEffects.add("Poison");
        }    
        int antiVenomGet = random(3, 1);
        if (antiVenomGet == 1) {
            opponent.antiVenomPotions += 1;
        }
    }
}
