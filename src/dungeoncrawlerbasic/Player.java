package dungeoncrawlerbasic;

import static dungeoncrawlerbasic.DungeonCrawlerBasic.delay;
import java.util.Scanner;


public class Player extends Character {
    int xpCurrent = 0;
    int xpCap = 2;
    int skillPoints = 0;
    int kills = 0;
    String nickname = "";
    static Scanner in = new Scanner(System.in);
    
    public void levelUp() {
        System.out.println("Level Up!");
        this.xpCurrent = 0;
        this.xpCap += 1;
        this.level += 1;
        this.skillPoints += 6;
        boolean FinishFlag = false;
        delay(400);
        
        while (FinishFlag == false) {
            System.out.println("Choose a stat to level up:");
            System.out.println("Skill points: " + this.skillPoints);
            System.out.println("");
            System.out.println("1) Max Health +2 / 1 point");
            System.out.println("2) Damage +1 / 2 points");
            System.out.println("3) Armor +1 / 3 points");
            System.out.println("4) Speed +1 / 4 points");
            System.out.println("5) Finish\n>");
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
            delay(400); 
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
    public Enemy enemyChooser() {
        Enemy enemy = new Enemy();
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
    public static Enemy enemyChooserLevel1to5() {
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
    public static Enemy enemyChooserLevel6to10() {
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
    public static Enemy enemyChooserLevel11to15() {
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
    public static Enemy enemyChooserLevel15to20() {
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
    public static Enemy enemyChooserLevel20orUp() {
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
        System.out.print("Choose a class:\n1) Warrior\t(++HP, +AT, +AR, -SP)\n2) Rogue\t(+HP, +AT, +AR, +SP)\n3) Assassin\t(-HP, ++AT, -AR, ++SP)\n> ");
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
    
    public void chooseSpell() {
        System.out.print("Choose a spell:\n1) Heal\t\t(+40 hp)\n2) Fireball\t(Enemy: -hp, -ar)\n3) Ice\t\t(Freeze enemy)\n> ");
        String inputSpell = in.nextLine();
        if (inputSpell.equals("1")) {
            this.spellName = "Heal";
        } else if (inputSpell.equals("2")) {
            this.spellName = "Fireball";
        } else if (inputSpell.equals("3")) {
            this.spellName = "Ice";
        }
    }
    
    public void playerAttackPhase(Character enemy) {
        System.out.println("\nWhat is your move?");
        System.out.println("1) Attack");
        System.out.println("2) Spell");
        System.out.println("3) Defend");
        System.out.println("4) Use potion");
        System.out.println("5) Run");
        System.out.println("6) Show stats");
        System.out.print("> ");
        String input1 = in.nextLine();

        if (input1.equals("1")) {
            this.attack(enemy);
            if (enemy.healthCurrent <= 0) {
            }
            this.attempt = true; 
        } else if (input1.equals("2")) {
            this.useSpell(enemy);
            if (enemy.healthCurrent<= 0) {
            }
        } else if (input1.equals("3")) {
            this.defend();
            this.attempt = true;
        } else if (input1.equals("4")) {
            System.out.println("Which kind?");
            System.out.println("1)Health potion");
            System.out.println("2)Anti-venom potion");        
            String inputPotion = in.nextLine();

            if (inputPotion.equals("1")) {
                if (this.healthCurrent == this.healthCap) {
                    System.out.println("You can't drink a health potion while at full health");
                } else {
                    this.useHealthPotion();
                }
            } else if (inputPotion.equals("2")) {
                this.useAntiVenomPotion();
            }
            this.attempt = true;
        } else if (input1.equals("5")) {
            this.run(enemy);
            if (this.run(enemy) == true ) {  
            } else {
                System.out.println("Your attempt failed");
                delay(400);
            }
            this.attempt = true;
        } else if (input1.equals("6")) {
            boolean end = false;
            while (!end) {
                this.showStats();
                enemy.showStats();
                System.out.println("1) Continue");
                System.out.print("> ");
                String inputContinue = in.nextLine();
                if (inputContinue.equals("1")) {
                    end = true;
                }
            this.attempt = false;    
            } 
        } else {
            System.out.println("That is not a valid action.");
        }
    }   
        
    public void defeatedEnemy(Character enemy) {
        System.out.println("You survived!");
            delay(400);
            if (enemy.healthCurrent <= 0) {
                enemy.itemDrop(this);
                this.getStuff(enemy);
                this.kills += 1;
                this.xpCurrent += 1;
            }
            if (this.xpCurrent == this.xpCap) {
                this.levelUp();
            }
        delay(400);
        System.out.println("Kills so far: " + this.kills);
        System.out.println("\n\n\n");
    } 
}

