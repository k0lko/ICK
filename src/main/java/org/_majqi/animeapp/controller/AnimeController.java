package org._majqi.animeapp.controller;

import org._majqi.animeapp.dto.AnimeApiResponseDto;
import org._majqi.animeapp.dto.GenreDto;
import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.service.AnimeService;
import org._majqi.animeapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public Anime addAnime(@RequestBody Anime anime) {
        return animeService.addAnime(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/search/{title}")
//    public ResponseEntity<Map<String, Object>> searchAnimeByTitle(
//            @PathVariable String title,
//            @RequestParam int page,
//            @RequestParam int size
//    ) {
//        try {
//            var animeApiResponse = animeService.fetchAnimeFromAPI(page, size, title);
//            return getResponseEntity(animeApiResponse);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchAnime(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<String> genresIds
    ) {
        try {
            var animeApiResponse = animeService.fetchPaginatedAnimeFromAPI(page, size, title, genresIds);
            return getResponseEntity(animeApiResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/genres")
    public ResponseEntity<GenreDto> getAnimesGenres() {
        return new ResponseEntity<>(animeService.fetchAnimesGenres(), HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> getResponseEntity(AnimeApiResponseDto animeApiResponse) {
        var pagination = animeApiResponse.getPagination();

        Map<String, Object> response = new HashMap<>();
        response.put("pagination", pagination);
        response.put("data", animeApiResponse.getData());
        response.put("currentPage", pagination.getCurrentPage());
        response.put("totalItems", pagination.getItems().getTotal());
        response.put("totalPages", pagination.getLastVisiblePage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}