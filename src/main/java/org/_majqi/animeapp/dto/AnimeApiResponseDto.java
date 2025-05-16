package org._majqi.animeapp.dto;

import org._majqi.animeapp.model.Anime;

import java.util.List;

public class AnimeApiResponseDto {
    private PaginationDto pagination;
    private List<Anime> data;

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }
}
