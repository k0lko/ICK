package org._majqi.animeapp.controller;

import org._majqi.animeapp.dto.ReviewApiResponseDto;
import org._majqi.animeapp.model.Review;
import org._majqi.animeapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anime/{animeId}/reviews")
class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review addReview(@PathVariable Long animeId, @RequestBody Review review) {
        return reviewService.addReview(animeId, review);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getReviews(@PathVariable Long animeId) {
        var reviewApiResponse = reviewService.getReviewsByAnimeId(animeId);
        var pagination = reviewApiResponse.getPagination();

        Map<String, Object> response = new HashMap<>();
        response.put("pagination", pagination);
        response.put("data", reviewApiResponse.getData());
        response.put("currentPage", pagination.getCurrentPage());
        if (pagination.getItems() != null) {
            response.put("totalItems", pagination.getItems().getTotal());
        }
        response.put("totalPages", pagination.getLastVisiblePage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}