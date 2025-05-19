package com.praktikum.app;

import com.praktikum.data.Item;
import com.praktikum.users.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();

    public static void main(String[] args) {
        // Data default
        userList.add(new Admin("Admin", "admin", "admin039"));
        userList.add(new Mahasiswa("daus", "039"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Sistem Login =====");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Username: ");
                    String user = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    User admin = loginAdmin(user, pass);
                    if (admin != null) {
                        admin.showMenu();
                    } else {
                        System.out.println("Login gagal.");
                    }
                }
                case 2 -> {
                    System.out.print("Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();
                    User mhs = loginMahasiswa(name, nim);
                    if (mhs != null) {
                        mhs.showMenu();
                    } else {
                        System.out.println("Login gagal.");
                    }
                }
                case 3 -> {
                    System.out.println("Keluar dari sistem...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static User loginAdmin(String username, String password) {
        for (User user : userList) {
            if (user instanceof Admin a && a.getUsername().equals(username) && a.getPassword().equals(password)) {
                return a;
            }
        }
        return null;
    }

    private static User loginMahasiswa(String name, String nim) {
        for (User user : userList) {
            if (user instanceof Mahasiswa m && m.getName().equals(name) && m.getNim().equals(nim)) {
                return m;
            }
        }
        return null;
    }
}
