package textadventures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        runGame();
    }

    public static void runGame() {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();

        List<Enemy> enemies = new ArrayList<>();
        // Name, HP, Atk Base, Atk Bonus, Defense, Fire, Ice, Lightning, Light, Darkness Resistance
        enemies.add(new Enemy("Fire Elemental", 100, 8, 5, 2, 10, -25, 0, 0, 5));
        enemies.add(new Enemy("Ice Golem", 120, 10, 6, 5, -75, 10, 5, 0, 0));
        enemies.add(new Enemy("Thunder Dragon", 200, 15, 10, 5, -50, 5, 15, 5, 5));
        enemies.add(new Enemy("Shadow Beast", 80, 5, 15, 0, 0, 0, 0, -420, 10));

        Collections.shuffle(enemies);

        System.out.println("You awaken in a dark forest. You sense danger ahead.");

        for (Enemy currentEnemy : enemies) {
            System.out.println("-----------------------------------");
            System.out.println("A wild " + currentEnemy.getName() + " appears!");
            System.out.println("Its health is " + currentEnemy.getHealth() + ".");

            while (player.getHealth() > 0 && currentEnemy.getHealth() > 0) {
                System.out.println("-----------------------------------");
                System.out.println("Your health: " + player.getHealth());
                System.out.println(currentEnemy.getName() + "'s health: " + currentEnemy.getHealth());
                System.out.println("Type 'attack', 'fire', 'ice', 'lightning', 'light', 'darkness', or 'heal'.");

                String playerAction = scanner.nextLine();
                Utility.clearConsole(); // Call the clear screen function here

                int damage = 0;
                String damageType = "";

                if (playerAction.equalsIgnoreCase("attack")) {
                    damage = Math.max(0, player.attack() - currentEnemy.getDefense());
                    damageType = "physical";
                } else if (playerAction.equalsIgnoreCase("fire")) {
                    damage = Math.max(0, player.castFireball() - currentEnemy.getFireResistance());
                    damageType = "fire";
                } else if (playerAction.equalsIgnoreCase("ice")) {
                    damage = Math.max(0, player.castIceShard() - currentEnemy.getIceResistance());
                    damageType = "ice";
                } else if (playerAction.equalsIgnoreCase("lightning")) {
                    damage = Math.max(0, player.castLightningBolt() - currentEnemy.getLightningResistance());
                    damageType = "lightning";
                } else if (playerAction.equalsIgnoreCase("light")) {
                    damage = Math.max(0, player.castLightBeam() - currentEnemy.getLightResistance());
                    damageType = "light";
                } else if (playerAction.equalsIgnoreCase("darkness")) {
                    damage = Math.max(0, player.castDarknessBlast() - currentEnemy.getDarknessResistance());
                    damageType = "darkness";
                } else if (playerAction.equalsIgnoreCase("heal")) {
                    player.heal();
                    damage = -1; // Special value to indicate no damage dealt
                } else {
                    System.out.println("Invalid command. You hesitate, and nothing happens.");
                    continue;
                }

                if (damage >= 0) {
                    currentEnemy.takeDamage(damage);
                    System.out.println("You hit the " + currentEnemy.getName() + " for " + damage + " " + damageType + " damage!");
                }
                
                if (currentEnemy.getHealth() <= 0) {
                    System.out.println("You have defeated the " + currentEnemy.getName() + "!");
                    break;
                }

                int enemyDamage = Math.max(0, currentEnemy.attack() - player.getDefense());
                player.takeDamage(enemyDamage);
                System.out.println("The " + currentEnemy.getName() + " strikes you for " + enemyDamage + " damage!");
            }
            
            if (player.getHealth() <= 0) {
                System.out.println("-----------------------------------");
                System.out.println("You have been defeated! Game Over.");
                scanner.close();
                return;
            }
        }
        
        System.out.println("-----------------------------------");
        System.out.println("You have defeated all enemies! You are the champion! 🎉");
        scanner.close();
    }
}