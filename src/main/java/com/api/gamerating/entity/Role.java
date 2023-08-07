package com.api.gamerating.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="roles")
    private String roles;



    public Role() {
    }

    public Role(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return roles;
    }

    public void setTitle(String title) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + roles + '\'' +
                '}';
    }
}
