package org._majqi.animeapp.service;

import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.model.Review;
import org._majqi.animeapp.repository.AnimeRepository;
import org._majqi.animeapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AnimeRepository animeRepository;

    public Review addReview(Long animeId, Review review) {
        Anime anime = animeRepository.findById(animeId).orElseThrow(() -> new RuntimeException("Anime not found"));
        review.setAnime(anime);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByAnimeId(Long animeId) {
        return reviewRepository.findByAnimeId(animeId);
    }

    public OptionalDouble getAnimeAverageRating(Long animeId) {
        return reviewRepository.findByAnimeId(animeId).stream()
                .mapToDouble(Review::getRating)
                .average();
    }

}
