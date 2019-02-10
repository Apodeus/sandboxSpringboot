package fr.pfe.visgen.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Voiture {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "marque")
    private String brand;

    @Column(name = "modele")
    private String model;

    @Column(name = "prix")
    private Integer price;

    public Voiture(){
        brand = "default";
        model = "default";
        price = -1;
    }

    public Voiture(String brand, String model, int price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Voiture(long id, String brand, String model, int price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
