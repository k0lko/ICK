import {Component, OnInit} from '@angular/core';
import {AnimeService} from './anime.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-anime',
  standalone: false,
  templateUrl: './anime.component.html',
  styleUrl: './anime.component.css'
})
export class AnimeComponent implements OnInit{
  animes: any;
  ratings: Map<string, number | null> = new Map;
  ratingFilter: number = 0;

  constructor(
    private animeService: AnimeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.animeService.getAnimeList().subscribe({
      next: (animes) => {
        this.animes = animes;
        (animes as any[]).forEach(anime =>  {
          this.animeService.getAnimeAverageRating(anime.id).subscribe({
            next: (averageRating) => {
              this.ratings.set(anime.id, averageRating);
            },
            error: (err) => console.log(err)
          })
        })
      },
      error: (err) => console.log(err)
    });
  }

  filterAnimeByRating(rating: number) {
    this.animeService.filterAnimeByRating(rating).subscribe({
      next: (filteredAnimes) => this.animes = filteredAnimes,
      error: (err) => console.log(err)
    })
  }

  navigateToReviews(animeId: string) {
    this.router.navigate([`/anime/${animeId}/reviews`]);
  }

  navigateToAddNewReview(animeId: string) {
    this.router.navigate([`/anime/${animeId}/reviews/add`]);
  }

}
