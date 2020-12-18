package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private String editorial;
    private String cover;
    private Integer status;

    public Book(String title, String author, String editorial, String cover, Integer status) {
        this.id = 0;
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.cover = cover;
        this.status = status;
    }

    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.editorial = "";
        this.cover = "";
        this.status = 1;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editorial='" + editorial + '\'' +
                ", status=" + status +
                '}';
    }
}
