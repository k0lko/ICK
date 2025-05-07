package org._majqi.animeapp.controller;

import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.service.AnimeService;
import org._majqi.animeapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
class AnimeController {

    @Autowired
    private AnimeService animeService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Anime> getAnimeList() {
        return animeService.getAllAnime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        return animeService.getAnimeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    public List<Anime> getAnimeByGenre(@PathVariable String genre) {
        return animeService.getAnimeByGenre(genre);
    }

    @PostMapping
    public Anime addAnime(@RequestBody Anime anime) {
        return animeService.addAnime(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{title}")
    public String searchAnime(@PathVariable String title) {
        return animeService.fetchAnimeFromAPI(title);
    }

    @GetMapping("/{id}/average-rating")
    public Object getAverageRating(@PathVariable Long id) {
        var averageRatingOpt = reviewService.getAnimeAverageRating(id);
        if (averageRatingOpt.isPresent()) {
            return Math.round(averageRatingOpt.getAsDouble() * 100.0) / 100.0;
        } else {
            return null;
        }
    }
}