package textadventures;

import java.util.Random;

public class Enemy {
    private String name;
    private int health;
    private int attackBase;
    private int attackBonusMax;
    private int defense;
    private int fireResistance;
    private int iceResistance;
    private int lightningResistance;
    private int lightResistance;
    private int darknessResistance;
    private Random random;

    public Enemy(String name, int health, int attackBase, int attackBonusMax, int defense, int fireResistance, int iceResistance, int lightningResistance, int lightResistance, int darknessResistance) {
        this.name = name;
        this.health = health;
        this.attackBase = attackBase;
        this.attackBonusMax = attackBonusMax;
        this.defense = defense;
        this.fireResistance = fireResistance;
        this.iceResistance = iceResistance;
        this.lightningResistance = lightningResistance;
        this.lightResistance = lightResistance;
        this.darknessResistance = darknessResistance;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public int attack() {
        return attackBase + random.nextInt(attackBonusMax);
    }
    
    public int getDefense() {
        return defense;
    }
    
    public int getFireResistance() {
        return fireResistance;
    }
    
    public int getIceResistance() {
        return iceResistance;
    }
    
    public int getLightningResistance() {
        return lightningResistance;
    }
    
    public int getLightResistance() {
        return lightResistance;
    }
    
    public int getDarknessResistance() {
        return darknessResistance;
    }
}