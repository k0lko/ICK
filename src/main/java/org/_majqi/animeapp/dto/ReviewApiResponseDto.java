package org._majqi.animeapp.dto;

import org._majqi.animeapp.model.Review;

import java.util.List;

public class ReviewApiResponseDto {
    private PaginationDto pagination;
    private List<Review> data;

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    public List<Review> getData() {
        return data;
    }

    public void setData(List<Review> data) {
        this.data = data;
    }
}
