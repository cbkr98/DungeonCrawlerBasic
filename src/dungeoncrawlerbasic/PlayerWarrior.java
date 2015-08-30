package dungeoncrawlerbasic;

public class PlayerWarrior extends Player{
    public PlayerWarrior() {
        this.name = "Player";
        this.nickname = "Warrior";
        this.healthCap = 20;
        this.damage = 3;
        this.armor = 2;
        this.speed = 4;
        this.healthPotions = 2;
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        maximizeStats();
    }
}
