package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class Loan {
    private Integer id;
    private String registry_date;
    private Integer state;
    private String deliver_date;
    private String return_date;

    public Loan(Integer id, String registry_date, Integer state, String deliver_date, String return_date) {
        this.id = id;
        this.registry_date = registry_date;
        this.state = state;
        this.deliver_date = deliver_date;
        this.return_date = return_date;
    }

    public Loan() {
        this.id = 0;
        this.registry_date = "";
        this.state = 1;
        this.deliver_date = "";
        this.return_date = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistry_date() {
        return registry_date;
    }

    public void setRegistry_date(String registry_date) {
        this.registry_date = registry_date;
    }

    public Integer getState() {
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
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", registry_date='" + registry_date + '\'' +
                ", state=" + state +
                ", deliver_date='" + deliver_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}
