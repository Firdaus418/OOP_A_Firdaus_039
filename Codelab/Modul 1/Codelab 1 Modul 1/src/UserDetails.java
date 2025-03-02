import java.util.Scanner;
import java.time.LocalDate;


public class UserDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //input nama
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        //validasi input
        char gender;
        while (true) {
            System.out.print("Enter your gender (M/F): ");
            gender = scanner.next().charAt(0);
            if (gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f') {
                break;
            }
            System.out.println("Invalid input. Please enter 'M' or 'F'.");
        }

        //input tahun kelahiran
        System.out.print("Enter your year of birth: ");
        int yearOfBirth = scanner.nextInt();

        //kalkulasi umur
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - yearOfBirth;

        String genderDescription = (gender == 'M' || gender == 'm') ? "Male" : "Female";

        //output
        System.out.println("\nUser Details:");
        System.out.println("Name: " + name);
        System.out.println("Gender: " + genderDescription);
        System.out.println("Age: " + age);

        scanner.close();
    }
}
