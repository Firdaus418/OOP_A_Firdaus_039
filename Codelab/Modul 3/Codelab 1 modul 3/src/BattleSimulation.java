// Superclass: GameCharacter
class GameCharacter {
    private String name;
    private int health;

    public GameCharacter(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(GameCharacter target) {
        // This method will be overridden by subclasses
    }
}

// Subclass: Hero
class Hero extends GameCharacter {
    public Hero(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " attacks " + target.getName() + " using a Vandal!");
        target.setHealth(target.getHealth() - 40);
        System.out.println(target.getName() + " now has " + target.getHealth() + " health.");
    }
}

// Subclass: Enemy
class Enemy extends GameCharacter {
    public Enemy(String name, int health) {
        super(name, health);
    }

    @Override
    public void attack(GameCharacter target) {
        System.out.println(getName() + " attacked " + target.getName() + " using a Spectre!");
        target.setHealth(target.getHealth() - 26);
        System.out.println(target.getName() + " now has " + target.getHealth() + " health.");
    }
}

// Main Class
public class BattleSimulation {
    public static void main(String[] args) {
        // Creating objects
        GameCharacter generalCharacter = new GameCharacter("General Character", 100);
        Hero hero = new Hero("Omen", 125);
        Enemy enemy = new Enemy("Clove", 150);

        // Display initial status
        System.out.println("Initial status:");
        System.out.println(hero.getName() + " has health: " + hero.getHealth());
        System.out.println(enemy.getName() + " has health: " + enemy.getHealth());

        // Simulating attacks
        hero.attack(enemy);
        hero.attack(enemy);
        enemy.attack(hero);
    }
}
