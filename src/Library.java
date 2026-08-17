public class Library {
    String name;
    int year;
    Book[] books;

    Library(String name, int year, Book[] books) {
        this.name = name;
        this.year = year;
        this.books = books;
    }

    void displayInfo() {
        System.out.println("Library Name: " + this.name + ", Year Built: " + this.year + ".");

        System.out.println("===============================================================");

        System.out.println();
        System.out.println("Books Available");

        System.out.println();
        for (Book book : books) {
            System.out.println(book.getAuthor());
            System.out.println(book.getTitle());
            System.out.println(book.getPages());

            System.out.println();
        }
    }
}
