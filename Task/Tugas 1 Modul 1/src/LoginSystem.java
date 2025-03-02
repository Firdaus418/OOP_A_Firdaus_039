import java.util.Scanner;

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
            // Login Admin
            System.out.print("Masukkan username: ");
            String adminUsername = scanner.nextLine();
            System.out.print("Masukkan password: ");
            String adminPassword = scanner.nextLine();

            if (adminUsername.equals("admin039") && adminPassword.equals("password039")) {
                System.out.println("Login Admin berhasil!");
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }
        } else if (pilihan == 2) {
            // Login Mahasiswa
            System.out.print("Masukkan Nama: ");
            String mahasiswaNama = scanner.nextLine();
            System.out.print("Masukkan NIM: ");
            String mahasiswaNIM = scanner.nextLine();

            if (mahasiswaNama.equals("Firdaus Firmansyah Emha") && mahasiswaNIM.equals("202410370110039")) {
                System.out.println("Login Mahasiswa berhasil!");
                System.out.println("Nama: " + mahasiswaNama);
                System.out.println("NIM: " + mahasiswaNIM);
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        } else {
            System.out.println("Pilihan mboten valid.");
        }

        scanner.close();
    }
}
