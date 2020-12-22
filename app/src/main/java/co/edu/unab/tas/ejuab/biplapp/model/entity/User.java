package co.edu.unab.tas.ejuab.biplapp.model.entity;

import com.google.firebase.firestore.Exclude;

public class User {
    private String uid;
    private String document;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String status;

    public User(String uid, String typeDocument, String document, String name, String lastName, String email, String phone, String status) {
        this.uid = "";
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public User() {
        this.uid = "";
        this.document = "";
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.status = "";
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    @Exclude
    public void setUid(String uid) {

        this.uid = uid;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
