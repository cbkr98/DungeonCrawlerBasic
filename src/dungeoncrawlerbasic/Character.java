package dungeoncrawlerbasic;

import java.util.*;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.delay;
import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Character {
    String name = "";
    int healthCurrent = 0;
    int healthCap = 0;
    int damage = 0;
    int armor = 0;
    int speed = 0;
    int healthPotions = 0;
    int antiVenomPotions = 0;
    int kills = 0;
    
    int defendCounter = 0;
    int stunCounter = 0;
    int poisonCounters = 0;
    
    ArrayList<String> weaponEffects = new ArrayList<String>();
    
    boolean isStunned = false;
        
    public void useHealthPotion() {
        if (this.healthPotions > 0) {
            this.healthCurrent += 30;
            if (this.healthCurrent > this.healthCap) {
                this.healthCurrent = this.healthCap;
            }
            this.healthPotions -= 1;
            System.out.println("Drank health potion.");
        }
    }
    
    public void useAntiVenomPotion() {
        if (this.antiVenomPotions > 0) {
            this.poisonCounters = 0;
            this.antiVenomPotions -= 1;
            System.out.println("Drank anti-venom potion.");
        }
    }
        
    public void attack(Character defender) {
        int defenderRoll = (int)((Math.random()*(defender.speed*35))+1);
        int attackerRoll = (int)((Math.random()*(this.speed*30))+1);
        if (attackerRoll > defenderRoll) {
            System.out.println("Attack hit!");
            delay(500);
            if (defender.defendCounter > 0) {
                System.out.println("Hit blocked!");
                defender.healthCurrent = (int)(defender.healthCurrent - (this.damage * .5));
                delay(500);
                int stunChance = random(10, 1);
                if (stunChance > 6) {
                    this.stunCounter += 1;
                    System.out.println("Stunned!");
                    delay(500);
                } 
                defender.defendCounter -= 1;    
            } else if (defender.armor >= this.damage) {
                System.out.println("No damage!");
            } else {
                if (this.weaponEffects.contains("Vampire")) {
                    this.healthCurrent = this.healthCurrent + (this.damage / 2);
                }
                if (this.weaponEffects.contains("Burn")) {
                    defender.armor = (int)(defender.armor * .85);
                    System.out.println("Opponent armor reduced!");
                    delay(500);
                }
                if (this.weaponEffects.contains("Poison")) {
                    defender.poisonCounters += 3;
                    System.out.println("Opponent poisoned!");
                    delay(500);
                }
                int defenderArmor = defender.armor;
                if (this.weaponEffects.contains("Shred")) {
                    defenderArmor = 0;
                }
                defender.healthCurrent = (defender.healthCurrent - this.damage) + defenderArmor;
            }    
        } else {
            System.out.println("Attack missed!");
        }
        delay(1500);
    }
    
    public void defend() {
        this.defendCounter += 1;
        System.out.println("Takes a defensive stance.");
    }
        
    public void itemDrop(Character opponent) {
        int x = random(3, 1);
        if (x == 1) {
            System.out.println("You found a health potion.");
            opponent.healthPotions += 1;
        }    
    }
    
    public void startTurn() {
        if (this.poisonCounters > 0) {
            this.healthCurrent -= 5;
            this.poisonCounters -= 1;
        }
        if (this.stunCounter > 0) {
            this.isStunned = true;
            this.stunCounter -= 1;
        } else {
            isStunned = false;
        }
        if (this.defendCounter > 0) {
            this.defendCounter -= 1;
        }
    }
    
    public void endTurn() {
    }
    
    public void endRound() {
        this.stunCounter = 0;
    }
    
    public void getStuff(Character enemy) {
        int playerDamageRoll = random(this.damage, 1);
        int enemyDamageRoll = random(enemy.damage, 1);
        if (playerDamageRoll < enemyDamageRoll) {
            int totalDamageRoll = enemyDamageRoll - playerDamageRoll;
            this.damage += totalDamageRoll;
            System.out.println("You found a better sword: damage + " + totalDamageRoll);
        }
        int playerArmorRoll = random(this.armor, 1);
        int enemyArmorRoll = random(enemy.armor, 1);
        if (playerArmorRoll < enemyArmorRoll) {
            int totalArmorRoll = enemyArmorRoll - playerArmorRoll;
            this.armor += totalArmorRoll;
            System.out.println("You found better armor: armor + " + totalArmorRoll);
        }
        int playerSpeedRoll = random(this.speed, 1);
        int enemySpeedRoll = random(enemy.speed, 1);
        if (playerSpeedRoll < enemySpeedRoll) {
            int totalSpeedRoll = enemySpeedRoll - playerSpeedRoll;
            this.speed += totalSpeedRoll;
            System.out.println("You found better boots: speed + " + totalSpeedRoll);
        }
    }
    
    public void statsInitial() {
        System.out.print("Health: " + this.healthCurrent + "/" + this.healthCap + "\t\t");
        System.out.print("Damage: " + this.damage + "(" + this.weaponEffects + ")");
        System.out.print("Armor: " + this.armor + "\t\t");
        System.out.println("Speed: " + this.speed + "\t\t");
        System.out.println("Potions: " + this.healthPotions + " / " + this.antiVenomPotions);
        System.out.println("Poison: " + this.poisonCounters);
    }
    
    public void showStats() {
        //System.out.println("Health")
    }
        
    public boolean run(Character opponent) {
        int opponentRoll = (int)((Math.random()*(opponent.speed*30))+1);
        int runnerRoll = (int)((Math.random()*(this.speed*35))+1);
        return runnerRoll > opponentRoll;
    }
        
    public boolean isAlive() {
        if (this.healthCurrent > 0) {
            return true;
        }
        return false;
    }
    
    public void maximizeStats() {
        this.healthCurrent = this.healthCap;
    }
}
