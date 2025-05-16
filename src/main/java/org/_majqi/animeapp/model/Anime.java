package org._majqi.animeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("mal_id")
    private Long id;
    private String title;
    @Embedded
    private Images images;
    @ManyToMany(mappedBy = "favoriteAnime")
    private List<User> users; // Lista użytkowników, którzy dodali anime do ulubionych

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Embeddable
    private static class Images {

        @Embedded
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }

        public void setJpg(Jpg jpg) {
            this.jpg = jpg;
        }

        @Embeddable
        private static class Jpg {
            @JsonProperty("image_url")
            private String imageUrl;
            @JsonProperty("small_image_url")
            private String smallImageUrl;
            @JsonProperty("large_image_url")
            private String largeImageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getSmallImageUrl() {
                return smallImageUrl;
            }

            public void setSmallImageUrl(String smallImageUrl) {
                this.smallImageUrl = smallImageUrl;
            }

            public String getLargeImageUrl() {
                return largeImageUrl;
            }

            public void setLargeImageUrl(String largeImageUrl) {
                this.largeImageUrl = largeImageUrl;
            }
        }
    }

}
