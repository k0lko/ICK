package org._majqi.animeapp.controller;

import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.service.AnimeService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
class AnimeController {
    @Autowired
    private AnimeService service;

    @GetMapping
    public List<Anime> getAnimeList() {
        return service.getAllAnime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        return service.getAnimeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    public List<Anime> getAnimeByGenre(@PathVariable String genre) {
        return service.getAnimeByGenre(genre);
    }

    @PostMapping
    public Anime addAnime(@RequestBody Anime anime) {
        return service.addAnime(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        service.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{title}")
    public String searchAnime(@PathVariable String title) {
        return service.fetchAnimeFromAPI(title);
    }

    @GetMapping("/filter")
    public List<Anime> filterAnimeByRating(@RequestParam double minRating) {
        return service.filterAnimeByRating(minRating);
    }

    @GetMapping("/stats/average-rating")
    public double getAverageRating() {
        return service.getAverageRating();
    }
}