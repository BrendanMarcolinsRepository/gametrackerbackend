package com.api.gamerating.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="level")
    private String level;

    @Column(name="description")
    private String description;

    @Column(name="organisation")
    private String organisation;
    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "rating", orphanRemoval = true, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Game game;

    public Rating() {
    }

    public Rating(String level, String description, String organisation) {
        this.level = level;
        this.description = description;
        this.organisation = organisation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", description='" + description + '\'' +
                ", organisation='" + organisation + '\'' +
                '}';
    }
}
