package co.edu.unab.tas.ejuab.biplapp.model.entity;

public class Editorial {
    private Integer id;
    private String name;

    public Editorial(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Editorial() {
        this.id = 0;
        this.name = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
