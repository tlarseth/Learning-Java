package textadventures;

import java.util.Random;

public class Player {
    private static final int PLAYER_HEALTH_MAX = 100;
    private static final int PLAYER_ATTACK_BASE = 12;
    private static final int PLAYER_ATTACK_BONUS_MAX = 4;
    private static final int PLAYER_HEAL_AMOUNT = 0;
    private static final int PLAYER_HEAL_MAX = 100;
    
    private int health;
    private int defense = 5;
    private int magicResistance = 5;
    private Random random;

    public Player() {
        this.health = PLAYER_HEALTH_MAX;
        this.random = new Random();
    }

    public int getHealth() {
        return health;
    }
    
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagicResistance() {
        return magicResistance;
    }

    public int attack() {
        return PLAYER_ATTACK_BASE + random.nextInt(PLAYER_ATTACK_BONUS_MAX);
    }

    public int castFireball() {
        return 20 + random.nextInt(10); 
    }
    
    public int castIceShard() {
        return 18 + random.nextInt(8); 
    }
    
    public int castLightningBolt() {
        return 22 + random.nextInt(12);
    }
    
    public int castLightBeam() {
        return 25 + random.nextInt(15);
    }
    
    public int castDarknessBlast() {
        return 20 + random.nextInt(10);
    }

    // Now returns the heal amount as a string
    public String heal() {
        int healAmount = PLAYER_HEAL_AMOUNT + random.nextInt(PLAYER_HEAL_MAX);
        this.health = Math.min(PLAYER_HEALTH_MAX, this.health + healAmount);
        return "You use your potion and heal for " + healAmount + " health!\n";
    }
}