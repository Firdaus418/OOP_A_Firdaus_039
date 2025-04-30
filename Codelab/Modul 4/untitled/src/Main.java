import library.*;

public class Main {
    public static void main(String[] args) {
        // Create books (your versions)
        Fiction fictionBook = new Fiction("Naruto", "Genshin", "Boboiboy");
        NonFiction nonFictionBook = new NonFiction("Sejarah bebek UMM", "Tutorial backflip", "Edukasi Valo");

        // Create members
        Member member1 = new Member("Daus", "2A039");
        Member member2 = new Member("Akmal", "2A157");

        // Display book info
        fictionBook.displayInfo();
        nonFictionBook.displayInfo();

        // Test borrowing and returning books (using YOUR book titles)
        member1.borrowBook("Naruto");
        member1.borrowBook("Sejarah bebek UMM", 14);

        member2.borrowBook("Naruto", 7);

        member1.returnBook("Naruto");
        member2.returnBook("Sejarah bebek UMM");
    }
}