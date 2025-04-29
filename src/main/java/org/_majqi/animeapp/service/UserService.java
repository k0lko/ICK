package org._majqi.animeapp.service;


import org._majqi.animeapp.model.Anime;
import org._majqi.animeapp.repository.AnimeRepository;
import org._majqi.animeapp.repository.UserRepository;
import org._majqi.animeapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Rejestracja użytkownika
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Dodawanie ulubionego anime
    public void addFavoriteAnime(Long userId, Long animeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Anime anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime not found"));

        user.getFavoriteAnime().add(anime);
        userRepository.save(user);
    }

    // Pobieranie ulubionych anime użytkownika
    public List<Anime> getFavoriteAnime(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteAnime();
    }
}