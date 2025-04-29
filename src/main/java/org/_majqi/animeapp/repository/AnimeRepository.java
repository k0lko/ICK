package org._majqi.animeapp.repository;

import org._majqi.animeapp.model.Anime;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;


import java.awt.print.Pageable;
import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByGenre(String genre);
    List<Anime> findByTitleContainingIgnoreCase(String title);
    List<Anime> findByRatingGreaterThanEqual(double rating);
}
