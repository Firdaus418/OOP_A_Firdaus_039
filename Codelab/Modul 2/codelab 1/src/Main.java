// Animal class
class Animal {
    // Attributes
    private String name;
    private String type;
    private String sound;

    // Constructor
    public Animal(String name, String type, String sound) {
        this.name = name;
        this.type = type;
        this.sound = sound;
    }

    // Method to display animal information
    public void displayInfo() {
        System.out.println("Name: " + name );
        System.out.println("Type: " + type );
        System.out.println("Sound: " + sound );
        System.out.println();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create two Animal objects
        Animal animal1 = new Animal("Cat", "Mammal", "Nyann~~");
        Animal animal2 = new Animal("Dog", "Mammal", "Woof-Woof!!");

        // Call the displayInfo method for both objects
        animal1.displayInfo();
        animal2.displayInfo();
    }
}