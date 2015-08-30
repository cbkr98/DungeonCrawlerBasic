package dungeoncrawlerbasic;
import java.util.*;

public class DungeonCrawlerBasic {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("\tYou have entered the dungeon!");
        System.out.println("-------------------------------------");
        delay(3000);
        
        
        Player player = new Player();
        player = Player.chooseClass();
        while (player.isAlive()) {
            Character enemy = player.enemyChooser();
            enemy.improveStatsByLevel(player);
            
            System.out.println("");
            System.out.println("\t# " + enemy.name + " has arrived #");
            
            isAlive:
            while (player.isAlive() && enemy.isAlive()) {
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
                        System.out.println("5) Show stats");
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
                                    System.out.println("You can't drink a health potion while at full health");
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
                                delay(500);
                            }
                            attempt = true;
                        } else if (input1.equals("5")) {
                            boolean end = false;
                            while (!end) {
                                player.showStats();
                                enemy.showStats();
                                System.out.println("1) Continue");
                                System.out.print("> ");
                                String inputContinue = in.nextLine();
                                if (inputContinue.equals("1")) {
                                    end = true;
                                }
                            attempt = false;    
                            } 
                        } else {
                            System.out.println("That is not a valid action.");
                        }
                    } else {
                        delay(500);
                        System.out.println("");
                        System.out.println("You are stunned!");
                        attempt = true;
                        delay(500);
                    } 
                    player.endTurn();
                }    
                
                enemyTurn:
                System.out.println("\n- " + enemy.name + "'s move -");
                delay(500);
                enemy.startTurn();
                if (enemy.stunCounter <= 0) {
                    if (player.stunCounter > 0) {
                       enemy.attack(player);
                    } else {
                        int enemyChoice = random(5, 1);
                        if (enemyChoice >= 1 && enemyChoice < 4) {
                        enemy.attack(player);
                        } else if (enemyChoice == 4) {
                        enemy.defend();
                        } else if (enemyChoice == 5) {
                            if (enemy.healthCurrent <= (enemy.healthCap *.2) && enemy.healthPotions > 0) {
                                enemy.useHealthPotion();
                            } else {
                                enemy.attack(player);
                            }
                        }
                    }    
                } else {
                    System.out.println("Enemy stunned!");
                }
                enemy.endTurn();
                System.out.println("\n\n\n\n\n\n");
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
            delay(1500);
            System.out.println("Kills so far: " + player.kills);
            System.out.println("\n\n\n");
            player.endTurn();
            delay(1500);        
        }
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