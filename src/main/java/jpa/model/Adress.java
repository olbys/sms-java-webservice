package main.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Adress implements Serializable {

    private long id;
    private String street;
    private String city;
    private long postalcode;
    private String country;


    /**
     * constructeur par defaut
     */
    public Adress(){

    }

    public Adress(String street, String city, long postalcode, String country) {
        this.street = street;
        this.city = city;
        this.postalcode = postalcode;
        this.country = country;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(long postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalcode=" + postalcode +
                ", country='" + country + '\'' +
                '}';
    }
}
