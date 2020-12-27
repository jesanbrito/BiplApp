package co.edu.unab.tas.ejuab.biplapp.model.entity;

import android.view.ViewDebug;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Book  implements Serializable {
    private String bid;
    private String title;
    private String author;
    private String editorial;
    private String category;
    private String cover;
    private Integer status;


    public Book(String title, String author, String editorial, String category,String cover, Integer status) {
        this.bid = "";
        this.title = title;
        this.author = author;
        this.editorial = editorial;
        this.category = category;
        this.cover = cover;
        this.status = status;
    }

    public Book() {
        this.bid = "";
        this.title = "";
        this.author = "";
        this.editorial = "";
        this.category = "";
        this.cover = "";
        this.status = 1;
    }

    @Exclude
    public String getBid() {
        return bid;
    }

    @Exclude
    public void setBid(String bid) {
        this.bid = bid;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                "bid='" + bid + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", editorial='" + editorial + '\'' +
                ", category='" + category + '\'' +
                ", cover='" + cover + '\'' +
                ", status=" + status +
                '}';
    }
}
