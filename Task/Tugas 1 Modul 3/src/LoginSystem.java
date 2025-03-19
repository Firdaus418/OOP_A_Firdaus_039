import java.util.Scanner;

// Superclass User
class User {
    private String name;
    private String studentID;

    public User(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public boolean login(String inputName, String inputID) {
        return false; // To be overridden
    }

    public void displayInfo() {
        System.out.println("User Information");
    }
}

// Subclass Admin
class Admin extends User {
    private String username;
    private String password;

    public Admin(String name, String studentID, String username, String password) {
        super(name, studentID);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Admin berhasil!");
    }
}

// Subclass Student
class Student extends User {
    public Student(String name, String studentID) {
        super(name, studentID);
    }

    @Override
    public boolean login(String inputName, String inputID) {
        return getName().equals(inputName) && getStudentID().equals(inputID);
    }

    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        System.out.println("Nama: " + getName());
        System.out.println("NIM: " + getStudentID());
    }
}

// Main class
public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pilih jenis login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan == 1) {
            System.out.print("Masukkan username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String password = scanner.nextLine();

            Admin admin = new Admin("Admin User", "000000", "Admin039", "password039");
            if (admin.login(username, password)) {
                admin.displayInfo();
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }
        } else if (pilihan == 2) {
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();

            Student student = new Student("Firdaus Firmansyah Emha", "202410370110039");
            if (student.login(nama, nim)) {
                student.displayInfo();
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }
}
