package dungeoncrawlerbasic;
import java.util.*;

public class DungeonCrawlerBasic {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("\tYou have entered the dungeon!");
        System.out.println("-------------------------------------");
        delay(3000);
                
        Player player = new Player();
        while (player.isAlive()) {
            Character enemy = new Character();
            if (player.level >= 1 && player.level < 6) {
                enemy = enemyChooserLevel1to5();
            } else if (player.level >= 6 && player.level < 11) {
                enemy= enemyChooserLevel6to10();
            } else if (player.level >= 11 && player.level < 16) {
                enemy = enemyChooserLevel11to15();
            } else if (player.level >= 16 && player.level < 21) {
                enemy = enemyChooserLevel15to20();
            } else if (player.level >= 20) {
                enemy = enemyChooserLevel20orUp();
            }    
            System.out.println("");
            System.out.println("\t# " + enemy.name + " has arrived #");
            
            isAlive:
            while (player.isAlive() && enemy.isAlive()) {
                System.out.println("");
                System.out.println("Your stats:");
                System.out.println("-----------");
                System.out.print("XP: " + player.xpCurrent + "/" + player.xpCap);
                System.out.println("\t\tLevel: " + player.level);
                player.statsInitial();
                System.out.println("");
                System.out.println(enemy.name + "'s stats");
                System.out.println("-----------");
                enemy.statsInitial();
                
                boolean attempt = false;
                
                actionChoice:
                while (attempt == false) {
                    player.startTurn();
                    if (!player.isStunned) {
                        System.out.println("\nWhat is your move?");
                        System.out.println("1) Attack");
                        System.out.println("2) Defend");
                        System.out.println("3) Use potion");
                        System.out.println("4) Run");
                        System.out.print("> ");
                        String input1 = in.nextLine();

                        if (input1.equals("1")) {
                            player.attack(enemy);
                            if (enemy.healthCurrent <= 0) {
                                break isAlive;
                            }
                            attempt = true; 
                        } else if (input1.equals("2")) {
                            player.defend();
                            attempt = true;
                        } else if (input1.equals("3")) {
                            System.out.println("Which kind?");
                            System.out.println("1)Health potion");
                            System.out.println("2)Anti-venom potion");        
                            String inputPotion = in.nextLine();
                            
                            if (inputPotion.equals("1")) {
                                if (player.healthCurrent == player.healthCap) {
                                    System.out.println("You can't drink a health potion with full health");
                                    break;
                                } else {
                                    player.useHealthPotion();
                                }
                            } else if (inputPotion.equals("2")) {
                                player.useAntiVenomPotion();
                            }
                            attempt = true;
                        } else if (input1.equals("4")) {
                            player.run(enemy);
                            if (player.run(enemy) == true ) {
                                break isAlive;    
                            } else {
                                System.out.println("Your attempt failed");
                                delay(1500);
                            }
                            attempt = true;
                        } else {
                            System.out.println("That is not a valid action.");
                        }
                    } else {
                        delay(500);
                        System.out.println("");
                        System.out.println("You are stunned!");
                        attempt = true;
                        delay(1000);
                    } 
                    player.endTurn();
                }    
                
                enemyTurn:
                System.out.println("\n- " + enemy.name + "'s move -");
                delay(1000);
                enemy.startTurn();
                if (enemy.stunCounter <= 0) {
                    int enemyChoice = random(5, 1);
                    if (enemyChoice >= 1 && enemyChoice < 4) {
                        enemy.attack(player);
                    } else if (enemyChoice == 4) {
                        enemy.defend();
                    } else if (enemyChoice == 5) {
                        if (enemy.healthCurrent == enemy.healthCap) {
                            enemy.attack(player);
                        } else if (enemy.healthPotions > 0) {
                            enemy.useHealthPotion();
                        } else {
                            enemy.attack(player);
                        }
                    }
                } else {
                    System.out.println("Enemy stunned!");
                }
                enemy.endTurn();
                delay(1000);
            }
            
            if (player.healthCurrent > 0) {
                System.out.println("You survived!");
                delay(1000);
                if (enemy.healthCurrent <= 0) {
                    enemy.itemDrop(player);
                    player.getStuff(enemy);
                    player.kills += 1;
                    player.xpCurrent += 1;
                }
                if (player.xpCurrent == player.xpCap) {
                    player.levelUp();
                }
            } else if (player.healthCurrent <= 0) {
                System.out.println("Oh well, you died. Better luck next time.");
            }   
            delay(2000);
            System.out.println("Kills so far: " + player.kills);
            player.endTurn();
            delay(2000);        
        }
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
    
    public static void delay(int a) {
        try {
            Thread.sleep(a);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static int random(int a, int b) {
        int x = (int)((Math.random()*a)+b);
        return x;
    }
}