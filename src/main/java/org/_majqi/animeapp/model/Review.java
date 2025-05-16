package org._majqi.animeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("mal_id")
    private Long id;
    private String review;
    private int score;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }
}
