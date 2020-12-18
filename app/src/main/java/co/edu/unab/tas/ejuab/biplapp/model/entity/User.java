package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class User {
    private String id;
    private String typeDocument;
    private String document;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String status;

    public User(String id, String typeDocument, String document, String name, String lastName, String email, String phone, String status) {
        this.id = id;
        this.typeDocument = typeDocument;
        this.document = document;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public User() {
        this.id = "";
        this.typeDocument = "";
        this.document = "";
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.status = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
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
