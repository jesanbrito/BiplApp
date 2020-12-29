package co.edu.unab.tas.ejuab.biplapp.model.entity;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
    private String lid;
    private String registry_date;
    private Book book;
    private String user_id;
    private boolean status;
    private String deliver_date;
    private String return_date;
    private String codigo_reserva;

    public Loan() {
        this.lid = "";
        this.registry_date = "";
        this.book = new Book();
        this.user_id = "";
        this.status = true;
        this.deliver_date = "";
        this.return_date = "";
        this.codigo_reserva = "";
    }

    @Exclude
    public String getLid() {
        return lid;
    }

    @Exclude
    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getRegistry_date() {
        return registry_date;
    }

    public void setRegistry_date(String registry_date) {
        this.registry_date = registry_date;
    }

    @Exclude
    public Book getBook() {
        return book;
    }

    @Exclude
    public void setBook(Book book) {
        this.book = book;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDeliver_date() {
        return deliver_date;
    }

    public void setDeliver_date(String deliver_date) {
        this.deliver_date = deliver_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getCodigo_reserva() {
        return codigo_reserva;
    }

    public void setCodigo_reserva(String codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "lid='" + lid + '\'' +
                ", registry_date='" + registry_date + '\'' +
                ", book=" + book +
                ", user_id='" + user_id + '\'' +
                ", status=" + status +
                ", deliver_date='" + deliver_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", codigo_reserva='" + codigo_reserva + '\'' +
                '}';
    }
}
