package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.delay;
import java.util.Scanner;


public class Player extends Character {
    int xpCurrent = 0;
    int xpCap = 2;
    int skillPoints = 0;
    int kills = 0;
    String spell = "";
    String nickname = "";
    static Scanner in = new Scanner(System.in);
    
    public void levelUp() {
        System.out.println("Level Up!");
        this.xpCurrent = 0;
        this.xpCap += 1;
        this.level += 1;
        this.skillPoints += 6;
        boolean FinishFlag = false;
        delay(1000);
        
        while (FinishFlag == false) {
            System.out.println("Choose a stat to level up:");
            System.out.println("Skill points: " + this.skillPoints);
            System.out.println("");
            System.out.println("1) Max Health +2 / 1 point");
            System.out.println("2) Damage +1 / 2 points");
            System.out.println("3) Armor +1 / 3 points");
            System.out.println("4) Speed +1 / 4 points");
            System.out.println("5) Finish");
            String inputLevelUp = in.nextLine();
            
            boolean levelUpFlag = false;
            if (inputLevelUp.equals("1")) {
                if (this.skillPoints > 0) {
                    this.healthCap += 2;
                    this.skillPoints -= 1;
                    System.out.println("Your health increased");
                    levelUpFlag = true;
                }
            } else if (inputLevelUp.equals("2")) {
                if (this.skillPoints > 1) {
                    this.damage += 1;
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
            } else if (inputLevelUp.equals("5")) {
                FinishFlag = true;
                levelUpFlag = true;
            }
            if (!levelUpFlag) {
                System.out.println("You don't have enought points for this");
            }
            delay(1000); 
        }
        if (this.nickname == "Assassin") {
            this.healthCap = (this.healthCap + this.healthCap/8) + 2;
            this.damage = (this.damage + this.damage/6) + 4;
            this.speed = this.speed + 1;
        } else if (this.nickname == "Warrior") {
            this.healthCap = (this.healthCap + this.healthCap/8) + 4;
            this.damage = (this.damage + this.damage/6) + 3;
            this.armor = (this.armor + this.armor/5) + 1;
            this.speed = this.speed + 1;
        } else if (this.nickname == "Rogue") {
            this.healthCap = (this.healthCap + this.healthCap/8) + 3;
            this.damage = (this.damage + this.damage/6) + 2;
            this.armor = (this.armor + this.armor/5) + 0;
            this.speed = this.speed + 1;
        }
        maximizeStats();
    }
    
    // Create the enemy
    public Character enemyChooser() {
    Character enemy = new Character();
        if (this.level >= 1 && this.level < 6) {
            enemy = enemyChooserLevel1to5();
        } else if (this.level >= 6 && this.level < 11) {
            enemy = enemyChooserLevel6to10();
        } else if (this.level >= 11 && this.level < 16) {
            enemy = enemyChooserLevel11to15();
        } else if (this.level >= 16 && this.level < 21) {
            enemy = enemyChooserLevel15to20();
        } else if (this.level >= 20) {
            enemy = enemyChooserLevel20orUp();
        }
        return enemy;
    }
    public static Character enemyChooserLevel1to5() {
        int x = (int)((Math.random()*15)+1);
        if (x >= 1 && x < 10) {
            return new Skeleton();
        } else if (x >= 10 && x < 15) {
            return new Goblin();
        } else if (x == 15) {
            return new Slime();
        } 
        return new Skeleton();
    }
    public static Character enemyChooserLevel6to10() {
        int x = (int)((Math.random()*25)+1);
        if (x >= 1 && x < 10) {
            return new Goblin();
        } else if (x >= 10 && x < 20) {
            return new Slime();
        } else if (x >= 20 && x < 25) {
            return new Zombie();
        } else if (x == 25) {
            return new Adventurer();
        }    
        return new Goblin();
    }
    public static Character enemyChooserLevel11to15() {
        int x = (int)((Math.random()*30)+1);
        if (x >= 1 && x < 5) {
            return new Goblin();
        } else if (x >= 5 && x < 15) {
            return new Zombie();
        } else if (x >= 15 && x < 25) {
            return new Adventurer();
        } else if (x >= 25 && x < 30) {
            return new Warrior();
        } else if (x == 30) {
            return new Butcher();
        }    
        return new Zombie();
    }
    public static Character enemyChooserLevel15to20() {
        int x = (int)((Math.random()*35)+1);
        if (x >= 1 && x < 5) {
            return new Zombie();
        } else if (x >= 5 && x < 10) {
            return new Adventurer();
        } else if (x >= 10 && x < 20) {
            return new Warrior();
        } else if (x >= 20 && x < 30) {
            return new Butcher();
        } else if (x >= 30 && x < 35) {
            return new Vampire();
        } else if (x == 35) {
            return new Ogre();
        }    
        return new Warrior();
    }
    public static Character enemyChooserLevel20orUp() {
        int x = (int)((Math.random()*40)+1);
        if (x >= 1 && x < 5) {
            return new Slime();
        } else if (x >= 5 && x < 15) {
            return new Butcher();
        } else if (x >= 15 && x < 25) {
            return new Vampire();
        } else if (x >= 25 && x < 35) {
            return new Warrior();
        } else if (x >= 35 && x < 40) {
            return new Ogre();
        } else if (x == 40) {
            return new Dragon();
        }
        return new Warrior();
    }
    
    public static Player chooseClass() {
        System.out.println("Choose a class:\n1) Warrior\n2) Rogue\n3) Assassin\n>");
        String inputClass = in.nextLine();
        if (inputClass.equals("1")) {
            return new PlayerWarrior();
        } else if (inputClass.equals("2")) {
            return new PlayerRogue();
        } else if (inputClass.equals("3")) {
            return new PlayerAssassin();
        }
        return new PlayerWarrior();
    }
}

