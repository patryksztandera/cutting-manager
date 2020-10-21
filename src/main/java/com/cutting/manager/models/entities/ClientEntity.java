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

    public ClientEntity(String name, String surname, @Email String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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
}
