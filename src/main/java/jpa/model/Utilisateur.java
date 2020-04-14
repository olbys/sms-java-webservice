package jpa.model;

import utils.security.PasswordUtils;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

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

        // Generate Salt. The generated value can be stored in DB.
        this.salt = PasswordUtils.getSalt();
        // Protect user's password. The generated value can be stored in DB.
        this.password = PasswordUtils.generateSecurePassword(password, salt);

    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
