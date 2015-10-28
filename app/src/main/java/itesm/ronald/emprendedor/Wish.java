package itesm.ronald.emprendedor;

/**
 * Created by Ronald on 9/9/2015.
 */
public class Wish {
    private String name, description;
    private String phone, cost, url;

    public Wish(String name, String description, String cost, String phone, String url) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.cost = cost;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
