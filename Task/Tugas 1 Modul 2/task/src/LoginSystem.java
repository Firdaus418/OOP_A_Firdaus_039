import java.util.Scanner;

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}

class Student {
    private String name;
    private String studentNIM;

    public Student(String name, String studentNIM) {
        this.name = name;
        this.studentNIM = studentNIM;
    }

    public boolean login(String inputName, String inputStudentNIM) {
        return this.name.equals(inputName) && this.studentNIM.equals(inputStudentNIM);
    }

    public void displayInfo() {
        System.out.println("Student Name: " + this.name);
        System.out.println("Student NIM: " + this.studentNIM);
    }
}

public class LoginSystem {
    private Admin admin;
    private Student student;

    public LoginSystem() {
        this.admin = new Admin("admin", "admin123");
        this.student = new Student("Daus", "039");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Login System");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Student");
        System.out.print("Choose an option: ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (admin.login(username, password)) {
                System.out.println("Admin login successful!");
            } else {
                System.out.println("Invalid username or password.");
            }
        } else if (choice.equals("2")) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student NIM: ");
            String studentNIM = scanner.nextLine();
            if (student.login(name, studentNIM)) {
                System.out.println("Student login successful!");
                student.displayInfo();
            } else {
                System.out.println("Invalid name or student NIM.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

        scanner.close();
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.run();
    }
}