package jpa.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn
public class Utilisateur extends Personne {

    private long id;

    private String login;
    private String password;
    private String salt;

    public Utilisateur() {
        super();
    }

    public Utilisateur(String name, String lastName, String cellPhone, String email) {
        super(name, lastName, cellPhone, email);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // Protect user's password. The generated value can be stored in DB.
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @PreUpdate
    @PrePersist
    public void hashedPassword(){
            this.password = BCrypt.hashpw(getPassword(),BCrypt.gensalt());
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }


}
