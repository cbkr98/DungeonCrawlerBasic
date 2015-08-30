package dungeoncrawlerbasic;

public class PlayerRogue extends Player{
    public PlayerRogue() {
        this.name = "Player";
        this.nickname = "Rogue";
        this.healthCap = 14;
        this.damage = 2;
        this.armor = 1;
        this.speed = 5;
        this.healthPotions = 3;
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
