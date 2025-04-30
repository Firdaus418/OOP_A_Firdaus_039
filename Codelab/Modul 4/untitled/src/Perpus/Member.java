package library;

public class Member implements BookLoan {
    private String name;
    private String memberID;

    public Member(String name, String memberID) {
        this.name = name;
        this.memberID = memberID;
    }

    // Overloaded method (version 1)
    public void borrowBook(String title) {
        System.out.println(name + " (ID: " + memberID + ") meminjam buku: " + title + " untuk durasi standar (7 hari)");
    }

    // Overloaded method (version 2)
    public void borrowBook(String title, int duration) {
        System.out.println(name + " (ID: " + memberID + ") meminjam buku: " + title + " untuk " + duration + " hari");
    }

    @Override
    public void returnBook(String title) {
        System.out.println(name + " (ID: " + memberID + ") mengembalikan buku: " + title);
    }
}