package org._majqi.animeapp.controller;

import org._majqi.animeapp.model.Review;
import org._majqi.animeapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Review> getReviews(@PathVariable Long animeId) {
        return reviewService.getReviewsByAnimeId(animeId);
    }
}