package org._majqi.animeapp.service;

import org._majqi.animeapp.dto.AnimeApiResponseDto;
import org._majqi.animeapp.dto.GenreDto;
import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Anime> getAllAnime() {
        return repository.findAll();
    }

    public Optional<Anime> getAnimeById(Long id) {
        return repository.findById(id);
    }

    public Anime addAnime(Anime anime) {
        return repository.save(anime);
    }

    public void deleteAnime(Long id) {
        repository.deleteById(id);
    }

//    public AnimeApiResponseDto fetchAnimeFromAPI(int page, int size, String title) {
//        ResponseEntity<AnimeApiResponseDto> response = restTemplate.exchange(
//                String.format("https://api.jikan.moe/v4/anime?q=%s&page=%s&limit=%s", title, page, size),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {}
//        );
//        return response.getBody();
//    }

    public AnimeApiResponseDto fetchPaginatedAnimeFromAPI(int page, int size, String title, List<String> genresIds) {
        var url = "";
        if (title != null && genresIds != null) {
            var genresIdsString = String.join(", ", genresIds);
            url = String.format(
                    "https://api.jikan.moe/v4/anime?page=%s&limit=%s&q=%s&genres=%s",
                    page,
                    size,
                    title,
                    genresIdsString
            );
        } else if (title != null) {
            url = String.format(
                    "https://api.jikan.moe/v4/anime?page=%s&limit=%s&q=%s",
                    page,
                    size,
                    title
            );
        } else if (genresIds != null) {
            var genresIdsString = String.join(", ", genresIds);
            url = String.format(
                    "https://api.jikan.moe/v4/anime?page=%s&limit=%s&genres=%s",
                    page,
                    size,
                    genresIdsString
            );
        } else {
            url = String.format(
                    "https://api.jikan.moe/v4/anime?page=%s&limit=%s",
                    page,
                    size
            );
        }
        ResponseEntity<AnimeApiResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public GenreDto fetchAnimesGenres() {
        ResponseEntity<GenreDto> response = restTemplate.exchange(
                String.format("https://api.jikan.moe/v4/genres/anime"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

}
