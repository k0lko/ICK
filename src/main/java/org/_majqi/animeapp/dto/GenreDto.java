package org._majqi.animeapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GenreDto {
    private List<Genre> data;

    public List<Genre> getData() {
        return data;
    }

    public void setData(List<Genre> data) {
        this.data = data;
    }

    public static class Genre {
        @JsonProperty("mal_id")
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
