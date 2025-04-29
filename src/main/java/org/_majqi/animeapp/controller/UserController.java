package org._majqi.animeapp.controller;

import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.service.UserService;
import org._majqi.animeapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Rejestracja użytkownika
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Dodawanie ulubionego anime
    @PostMapping("/{userId}/favorite/{animeId}")
    public ResponseEntity<Void> addFavoriteAnime(@PathVariable Long userId, @PathVariable Long animeId) {
        userService.addFavoriteAnime(userId, animeId);
        return ResponseEntity.ok().build();
    }

    // Pobieranie ulubionych anime użytkownika
    @GetMapping("/{userId}/favorite")
    public List<Anime> getFavoriteAnime(@PathVariable Long userId) {
        return userService.getFavoriteAnime(userId);
    }
}
