package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String editorial;
    private Integer state;

    public Book(Integer id, String title, String author, String editorial, Integer state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.state = state;
    }

    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.editorial = "";
        this.state = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editorial='" + editorial + '\'' +
                ", state=" + state +
                '}';
    }
}
