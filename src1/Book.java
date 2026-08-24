public class Book {
    private String author;
    private String title;
    private int pages;

    Book(String author, String title, int pages) {
        this.author = author;
        this.title = title;
        this.pages = pages;
    }

    String getAuthor() {
        return "Author: " + this.author;
    }

    String getTitle() {
        return "Title: " + this.title;
    }

    String getPages() {
        return "Pages: " + this.pages;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setPages(int pages) {
        this.pages = pages;
    }
}
