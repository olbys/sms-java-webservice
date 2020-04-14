package main.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {


    private long id;

    private String name;

    private String lastName;

    private String cellPhone;

    private String email;

    private String avatar;

    private Date birthDate;

    private Date createdAt;

    private Adress adress;




    public Personne(){
        this.createdAt = new Date();
    }


    public Personne(String name, String lastName, String cellPhone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.email = email;
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthDate=" + birthDate +
                ", createdAt=" + createdAt +
                ", adress=" + adress +
                '}';
    }
}
