package com.cristian.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String manufacturer;
    private String model;
    private String year;
    private String msrp;
    private String carURL;
    private String user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Car() {
    }

    public Car(long id, String manufacturer, String model, String year, String msrp, String carURL, String user){
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.msrp = msrp;
        this.carURL = carURL;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCarURL() {
        return carURL;
    }

    public void setCarURL(String carURL) {
        this.carURL = carURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

