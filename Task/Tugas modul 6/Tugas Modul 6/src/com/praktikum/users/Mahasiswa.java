// File: com/praktikum/users/Mahasiswa.java
package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import com.praktikum.app.LoginSystem;
import com.praktikum.data.Item;

import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {
    private String nim;

    public Mahasiswa(String name, String nim) {
        super(name);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n[Mahasiswa Menu]");
            System.out.println("1. Lapor Barang");
            System.out.println("2. Lihat Barang Dilaporkan");
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
                case 1 -> reportItem(); // Sesuai dengan interface (tanpa parameter)
                case 2 -> viewReportedItems();
                case 3 -> { return; }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Override dari interface MahasiswaActions
    @Override
    public void reportItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Barang: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi: ");
        String desc = scanner.nextLine();
        System.out.print("Lokasi: ");
        String loc = scanner.nextLine();

        Item item = new Item(name, desc, loc);
        LoginSystem.reportedItems.add(item);
        System.out.println("Barang berhasil dilaporkan.");
    }

    // Override dari interface MahasiswaActions
    @Override
    public void viewReportedItems() {
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Belum ada laporan barang.");
            return;
        }

        System.out.println("=== Barang Masih Belum Diklaim (Reported) ===");
        boolean adaReported = false;
        for (Item item : LoginSystem.reportedItems) {
            if ("Reported".equals(item.getStatus())) {
                System.out.println("Nama: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("Status: " + item.getStatus());
                System.out.println("------------------------");
                adaReported = true;
            }
        }
        if (!adaReported) {
            System.out.println("Tidak ada barang yang belum diklaim.");
        }

        System.out.println("\n=== Barang Sudah Diklaim (Claimed) ===");
        boolean adaClaimed = false;
        for (Item item : LoginSystem.reportedItems) {
            if ("Claimed".equals(item.getStatus())) {
                System.out.println("Nama: " + item.getItemName());
                System.out.println("Deskripsi: " + item.getDescription());
                System.out.println("Lokasi: " + item.getLocation());
                System.out.println("Status: " + item.getStatus());
                System.out.println("------------------------");
                adaClaimed = true;
            }
        }
        if (!adaClaimed) {
            System.out.println("Tidak ada barang yang sudah diklaim.");
        }
    }
}
