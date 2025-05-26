// File: com/praktikum/users/Admin.java
package com.praktikum.users;

import com.praktikum.actions.AdminActions;
import com.praktikum.data.Item;
import com.praktikum.app.LoginSystem;

import java.util.Iterator;
import java.util.Scanner;

public class Admin extends User implements AdminActions {
    private String username;
    private String password;

    public Admin(String name, String username, String password) {
        super(name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[Admin Menu]");
            System.out.println("1. Kelola Barang");
            System.out.println("2. Kelola Pengguna");
            System.out.println("3. Logout");
            System.out.print("Pilihan: ");
            int pilihan;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1 -> manageItems(); // Sesuai interface
                case 2 -> manageUsers(); // Sesuai interface
                case 3 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Override method dari interface
    @Override
    public void manageItems() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[Kelola Barang]");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Tandai Barang Telah Diambil");
            System.out.println("3. Kembali");
            System.out.print("Pilihan: ");
            int pilihan;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1 -> {
                    for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
                        Item item = LoginSystem.reportedItems.get(i);
                        System.out.printf("[%d] %s - %s (%s) [%s]\n", i, item.getItemName(), item.getDescription(), item.getLocation(), item.getStatus());
                    }
                }
                case 2 -> {
                    System.out.println("Pilih barang yang ingin ditandai sebagai 'Claimed':");
                    for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
                        Item item = LoginSystem.reportedItems.get(i);
                        if ("Reported".equals(item.getStatus())) {
                            System.out.printf("[%d] %s - %s\n", i, item.getItemName(), item.getLocation());
                        }
                    }
                    try {
                        System.out.print("Masukkan indeks barang: ");
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        LoginSystem.reportedItems.get(index).setStatus("Claimed");
                        System.out.println("Barang berhasil ditandai.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Indeks tidak valid!");
                    } catch (Exception e) {
                        System.out.println("Input harus berupa angka!");
                        scanner.nextLine();
                    }
                }
                case 3 -> { return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Override method dari interface
    @Override
    public void manageUsers() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[Kelola Pengguna]");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Kembali");
            System.out.print("Pilihan: ");
            int pilihan;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
                continue;
            }

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Nama Mahasiswa: ");
                    String name = scanner.nextLine();
                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();
                    Mahasiswa mhs = new Mahasiswa(name, nim);
                    LoginSystem.userList.add(mhs);
                    System.out.println("Mahasiswa berhasil ditambahkan.");
                }
                case 2 -> {
                    System.out.print("Masukkan NIM Mahasiswa yang ingin dihapus: ");
                    String nim = scanner.nextLine();
                    Iterator<User> iterator = LoginSystem.userList.iterator();
                    boolean found = false;
                    while (iterator.hasNext()) {
                        User user = iterator.next();
                        if (user instanceof Mahasiswa m && m.getNim().equals(nim)) {
                            iterator.remove();
                            found = true;
                            System.out.println("Mahasiswa berhasil dihapus.");
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Mahasiswa tidak ditemukan.");
                    }
                }
                case 3 -> { return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
