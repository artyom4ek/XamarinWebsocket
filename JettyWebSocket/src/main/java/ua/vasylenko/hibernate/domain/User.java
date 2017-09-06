package ua.vasylenko.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Модель таблицы User.
 * @Created by Тёма on 05.08.2017.
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
 
    @Column(name = "login")
    private String login;
 
    @Column(name = "password")
    private String password;
     
    @Column(name = "email")
    private String email;
 
    public Integer getId() {
        return this.id;
    }
     
    public String getLogin() {
        return this.login;
    }
     
    public void setLogin(String login) {
        this.login = login;
    }
     
    public String getPassword() {
        return this.password;
    }
     
    public void setPassword(String password) {
        this.password = password;
    }
     
    public String getEmail() {
        return this.email;
    }
 
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

}
