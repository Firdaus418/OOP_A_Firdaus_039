package library;

public class NonFiction extends Book {
    private String category;

    public NonFiction(String title, String author, String category) {
        super(title, author);
        this.category = category;
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku Non-Fiksi");
        System.out.println("Judul: " + title);
        System.out.println("Oleh: " + author);
        System.out.println("Kategori: " + category);
        System.out.println();
    }
}