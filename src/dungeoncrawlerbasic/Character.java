package dungeoncrawlerbasic;

import java.util.*;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.delay;
import static dungeoncrawlerbasic.DungeonCrawlerBasic.random;

public class Character {
    String name = "";
    String spellName = "";
    int healthCurrent = 0;
    int healthCap = 0;
    int damage = 0;
    int armor = 0;
    int speed = 0;
    int level = 1;
    int healthPotions = 0;
    int antiVenomPotions = 0;
    
    int defendCounter = 0;
    int stunCounter = 0;
    int poisonCounters = 0;
    
    int manaPoints = 4;
    
    boolean attempt = false;
    ArrayList<String> weaponEffects = new ArrayList<String>();
    
    boolean isStunned = false;
        
    public void useHealthPotion() {
        if (this.healthPotions > 0) {
            this.healthCurrent += 20;
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
            delay(400);
            if (defender.defendCounter > 0) {
                System.out.println("Hit blocked!");
                defender.healthCurrent = (int)(defender.healthCurrent - (this.damage * .5));
                delay(400);
                int stunChance = random(10, 1);
                if (stunChance > 6) {
                    this.stunCounter += 1;
                    System.out.println("Stunned!");
                    delay(400);
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
                    delay(400);
                }
                if (this.weaponEffects.contains("Poison")) {
                    defender.poisonCounters += 5;
                    System.out.println("Opponent poisoned!");
                    delay(400);
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
        delay(400);
    }
    
    public void useSpell(Character opponent) {
        if (this.manaPoints == 0) {
            System.out.println("Insufficient mana");
            this.attempt = false;
        } else if (this.spellName == "Heal") {
            this.manaPoints -= 1;
            this.healthCurrent += 40;
            this.attempt = true;
        } else if (this.spellName == "Fireball") {
            this.manaPoints -= 1;
            opponent.armor -= 2;
            opponent.healthCurrent -= this.damage/3;
            this.attempt = true;
        } else if (this.spellName == "Ice") {
            this.manaPoints -= 1;
            opponent.isStunned = true;
            this.attempt = true;
            System.out.println("This worked");
        }
        System.out.println("You use " + this.spellName + "!");
    }
    
    public void criticalAttack(Character defender) {
        defender.healthCurrent -= this.damage*2;
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
            this.healthCurrent -= 3;
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
        this.isStunned = false;
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
        System.out.print("Damage: " + this.damage + this.weaponEffects + "\t\t");
        System.out.print("Armor: " + this.armor);
        System.out.println("");
    }
    
    public void showStats() {
        System.out.println("");
        System.out.println(this.name + "'s stats -");
        System.out.println(" Health: " + this.healthCurrent + "/" + this.healthCap);
        System.out.println(" Damage: " + this.damage + this.weaponEffects);
        System.out.println("  Armor: " + this.armor);
        System.out.println("  Speed: " + this.speed);
        System.out.println("Potions: Health - " + this.healthPotions + " / Anti-Venom - " + this.antiVenomPotions);
        System.out.println(" Poison: " + this.poisonCounters);
        System.out.println("   Mana: " + this.manaPoints);
        System.out.println("  Spell: " + this.spellName);
        System.out.println("");
    }
    
    public static void preRoundSetup(Player player, Character enemy) {
        System.out.println("");
        System.out.println("Your stats:");
        System.out.println("-----------");
        System.out.print("XP: " + player.xpCurrent + "/" + player.xpCap);
        System.out.println("\t\t\tLevel: " + player.level);
        player.statsInitial();
        System.out.println("");
        System.out.println(enemy.name + "'s stats");
        System.out.println("-----------");
        enemy.statsInitial();
                
        player.attempt = false;
        player.manaPoints = 4;
    }
    
    public void improveStatsByLevel(Character leveler) {
        this.healthCap = this.healthCap * leveler.level;
        this.damage = this.damage * leveler.level;
        this.armor = this.armor * leveler.level;
        this.speed = (this.speed * leveler.level)/2;
        
        this.maximizeStats();
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
    
    public void stunned() {
        delay(400);
        System.out.println("");
        System.out.println("Stunned!");
        this.attempt = true;
        delay(400);
    }
    
    public void maximizeStats() {
        this.healthCurrent = this.healthCap;
    }
}
