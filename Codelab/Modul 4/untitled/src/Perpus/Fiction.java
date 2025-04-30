package library;

public class Fiction extends Book {
    private String genre;

    public Fiction(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Buku Fiksi");
        System.out.println("Judul: " + title);
        System.out.println("Oleh: " + author);
        System.out.println("Genre: " + genre);
        System.out.println();
    }
}