package itesm.ronald.emprendedor;

/**
 * Created by Ronald on 9/8/2015.
 */
public class Promo {
    private String title, shortDescription, description, location;
    private String phone, picture, price;

    public Promo(String title, String shortDescription, String description, String location, String phone, String picture, String price) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.picture = picture;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
