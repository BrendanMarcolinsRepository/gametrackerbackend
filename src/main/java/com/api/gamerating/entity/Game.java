package com.api.gamerating.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="publisher")
    private String publisher;

    @Column(name="description")
    private String description;

    @Column(name="release_date")
    private Timestamp releaseDate;

    @OneToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "rating_id")
    private Rating rating;


    @OneToMany(
        //fetch = FetchType.EAGER, //not good for one to many - better to use lazy
        fetch = FetchType.LAZY, //default for one to many
        mappedBy = "game",
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH }
   )
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Review> reviews;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH }
    )

    @JoinTable(
            name = "game_user",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<User> users;




    public Game() {}

    public Game(String title, String publisher, String description, Timestamp  releaseDate) {
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp  getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp  releaseDate) {
        this.releaseDate = releaseDate;
    }
    @JsonManagedReference
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
/*
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void add(Category category){
        if(categories == null){

            categories = new ArrayList<>();

        }

        if(category != null){
            categories.add(category);
            category.setGame(this);
        }

    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review){
        if(reviews == null){reviews = new ArrayList<>();}

        if(review != null){reviews.add(review);}

    }
*/
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addGameUser(User user){
        if(users == null){users = new ArrayList<>();}

        if(user != null){users.add(user);}

    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                '}';
    }
}
