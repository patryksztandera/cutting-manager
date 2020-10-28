package com.cutting.manager.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @Email
    private String email;

    @Column
    private String password;

    @Column(name = "admin_role")
    private Boolean admin;

    public ClientEntity(String name, String surname, @Email String email, String password, Boolean admin) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public ClientEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return admin;
    }
}
