package dungeoncrawlerbasic;

public class PlayerAssassin extends Player{
    public PlayerAssassin() {
        this.name = "Player";
        this.nickname = "Assassin";
        this.healthCap = 8;
        this.damage = 4;
        this.armor = 0;
        this.speed = 7;
        this.healthPotions = 1;
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
