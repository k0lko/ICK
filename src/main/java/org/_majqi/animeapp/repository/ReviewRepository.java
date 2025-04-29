package org._majqi.animeapp.repository;

import org._majqi.animeapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAnimeId(Long animeId);
}