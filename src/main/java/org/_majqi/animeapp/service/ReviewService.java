package org._majqi.animeapp.service;

import org._majqi.animeapp.dto.AnimeApiResponseDto;
import org._majqi.animeapp.dto.ReviewApiResponseDto;
import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.model.Review;
import org._majqi.animeapp.repository.AnimeRepository;
import org._majqi.animeapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Review addReview(Long animeId, Review review) {
        Anime anime = animeRepository.findById(animeId).orElseThrow(() -> new RuntimeException("Anime not found"));
        review.setAnime(anime);
        return reviewRepository.save(review);
    }

    public ReviewApiResponseDto getReviewsByAnimeId(Long animeId) {
        ResponseEntity<ReviewApiResponseDto> response = restTemplate.exchange(
                String.format("https://api.jikan.moe/v4/anime/%d/reviews", animeId),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

//    public OptionalDouble getAnimeAverageRating(Long animeId) {
//        return reviewRepository.findByAnimeId(animeId).stream()
//                .mapToDouble(Review::getRating)
//                .average();
//    }

}
