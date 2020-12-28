package co.edu.unab.tas.ejuab.biplapp.model.entity;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
    private String lid;
    private String registry_date;
    private String user_id;
   /* private Integer state;
    private String deliver_date;
    private String return_date;*/

  //  public Loan(String lid, Date registry_date, Integer state, String deliver_date, String return_date) {
    public Loan(String lid, String registry_date, String user_id) {
        this.lid = lid;
        this.registry_date = registry_date;
        this.user_id = user_id;
       /* this.state = state;
        this.deliver_date = deliver_date;
        this.return_date = return_date;*/
    }

    public Loan() {
        this.lid = "";
        this.registry_date = "";
        this.user_id = "";
      /*  this.state = 1;
        this.deliver_date = "";
        this.return_date = "";*/
    }

    @Exclude
    public String geLid() {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /* public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    }*/

  /*  @Override
    public String toString() {
        return "Loan{" +
                "lid=" + lid +
                ", registry_date='" + registry_date + '\'' +
                ", state=" + state +
                ", deliver_date='" + deliver_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }*/
}
