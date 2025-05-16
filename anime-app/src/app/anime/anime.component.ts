import {Component, OnInit} from '@angular/core';
import {AnimeService} from './anime.service';
import {Router} from '@angular/router';
import {FormControl} from '@angular/forms';
import {debounceTime} from 'rxjs';

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
  pageSizes: number[] = [5, 10, 15, 20, 25];
  pageSize: number = 5;
  page: number = 1;
  count: number = 0;
  searchControl = new FormControl('');

  constructor(
    private animeService: AnimeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getPaginatedAnimeList();
    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(searchedAnime => {
        this.searchAnime(searchedAnime);
      });
  }

  getRequestParams(page: number, pageSize: number): any {
    let params: any = {};

    if (page) {
      params[`page`] = page;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  getPaginatedAnimeList(): void {
    const params = this.getRequestParams(this.page, this.pageSize);
      this.animeService.getPaginatedAnimeList(params).subscribe({
        next: (response) => {
          const { pagination, data } = response;
          this.animes = data;
          this.pageSize = pagination.items.per_page;
          this.page = pagination.current_page;
          this.count = pagination.items.total;
          // (animes as any[]).forEach(anime =>  {
          //   this.animeService.getAnimeAverageRating(anime.id).subscribe({
          //     next: (averageRating) => {
          //       this.ratings.set(anime.id, averageRating);
          //     },
          //     error: (err) => console.log(err)
          //   })
          // })
        },
        error: (err) => console.log(err)
      });
  }

  // filterAnimeByRating(rating: number) {
  //   this.animeService.getAnimeList().subscribe({
  //     next: (animes) => {
  //     this.animes = (animes as any[]).filter(anime => {
  //       const animeRating= this.ratings.get(anime.id);
  //       return animeRating != null && animeRating <= rating;
  //     });
  //   }
  //   })
  // }

  navigateToReviews(animeId: string) {
    this.router.navigate([`/anime/${animeId}/reviews`]);
  }

  navigateToAddNewReview(animeId: string) {
    this.router.navigate([`/anime/${animeId}/reviews/add`]);
  }

  handlePageSizeChange(event: any): void {
    this.pageSize = event.target.value;
    this.page = 1;
    const animeToSearch = this.searchControl.value;
    if (animeToSearch === "") {
      this.getPaginatedAnimeList();
    } else {
      this.searchAnime(animeToSearch)
    }
  }

  handlePageChange(event: number): void {
    this.page = event;
    const animeToSearch = this.searchControl.value;
    if (animeToSearch === "") {
      this.getPaginatedAnimeList();
    } else {
      this.searchAnime(animeToSearch)
    }
  }

  searchAnime(searchedAnime: string | null): void {
    if (searchedAnime === null || searchedAnime === "") {
      this.getPaginatedAnimeList();
    } else {
      const params = this.getRequestParams(this.page, this.pageSize);
      this.animeService.getAnimeByTitle(searchedAnime, params).subscribe({
          next: (response) => {
            const { pagination, data } = response;
            this.animes = data;
            this.pageSize = pagination.items.per_page;
            this.page = pagination.current_page;
            this.count = pagination.items.total;
          },
          error: err => console.log(err)
        })
    }
  }

}
