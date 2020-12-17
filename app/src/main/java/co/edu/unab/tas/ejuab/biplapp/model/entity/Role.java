package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class Role {
    private Integer id;
    private String description;

    public Role(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Role() {
        this.id = 0;
        this.description = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
