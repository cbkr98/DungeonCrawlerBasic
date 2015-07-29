package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.delay;
import java.util.Scanner;

public class Player extends Character {
    int xpCurrent = 0;
    int xpCap = 0;
    int level = 0;
    int skillPoints = 0;
    Scanner in = new Scanner(System.in);
    public Player() {
        this.name = "Player";
        this.healthCap = 40;
        this.damage = 8;
        this.armor = 2;
        this.speed = 3;
        this.healthPotions = 5;
        this.antiVenomPotions = 2;
        this.kills = 0;
        
        this.defendCounter = 0;
        this.poisonCounters = 0;
        this.stunCounter = 0;
        
        this.xpCurrent = 0;
        this.xpCap = 2;
        this.level = 1;
        this.skillPoints = 0;
        
        maximizeStats();
    }
    
    public void levelUp() {
        System.out.println("Level Up!");
        this.xpCurrent = 0;
        this.xpCap += 1;
        this.level += 1;
        this.skillPoints = 6;
        delay(1000);
        
        while (this.skillPoints > 0) {
            System.out.println("Choose a stat to level up:");
            System.out.println("Skill points: " + this.skillPoints);
            System.out.println("");
            System.out.println("1) Max Health +5 / 1 point");
            System.out.println("2) Damage +2 / 2 points");
            System.out.println("3) Armor +1 / 3 points");
            System.out.println("4) Speed +1 / 4 points");
            String inputLevelUp = in.nextLine();
            
            boolean levelUpFlag = false;
            if (inputLevelUp.equals("1")) {
                if (this.skillPoints > 0) {
                    this.healthCap += 5;
                    this.skillPoints -= 1;
                    System.out.println("Your health increased");
                    levelUpFlag = true;
                }
            } else if (inputLevelUp.equals("2")) {
                if (this.skillPoints > 1) {
                    this.damage += 2;
                    this.skillPoints -= 2;
                    System.out.println("Your damage increased");
                    levelUpFlag = true;
                }
            } else if (inputLevelUp.equals("3")) {
                if (this.skillPoints > 2) {
                    this.armor += 1;
                    this.skillPoints -= 3;
                    System.out.println("Your armor increased");
                    levelUpFlag = true;
                }
            } else if (inputLevelUp.equals("4")) {
                if (this.skillPoints > 3) {
                    this.speed += 1;
                    this.skillPoints -= 4;
                    System.out.println("Your speed increased");
                    levelUpFlag = true;
                }
            }
            if (!levelUpFlag) {
                System.out.println("You don't have enought points for this");
            }
            delay(1000); 
        }    
        maximizeStats();
    }
}

