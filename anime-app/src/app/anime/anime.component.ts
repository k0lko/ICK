import {Component, OnInit} from '@angular/core';
import {AnimeService} from './anime.service';
import {Router} from '@angular/router';
import {FormControl} from '@angular/forms';
import {debounceTime} from 'rxjs';
import {FavouritesService} from '../favourites/favourites.service';
import {Anime} from './anime.model';

@Component({
  selector: 'app-anime',
  standalone: false,
  templateUrl: './anime.component.html',
  styleUrl: './anime.component.css'
})
export class AnimeComponent implements OnInit{
  animes: any;
  genres: any;
  checkedGenresIds: string[] = [];
  searchedAnime: string | null = "";
  pageSizes: number[] = [5, 10, 15, 20, 25];
  pageSize: number = 5;
  page: number = 1;
  count: number = 0;
  searchControl = new FormControl('');
  favourites: Anime[] = [];

  constructor(
    private animeService: AnimeService,
    private favouritesService: FavouritesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getPaginatedAnimeList();
    this.searchControl.valueChanges
      .pipe(debounceTime(500))
      .subscribe(searchedAnime => {
        this.searchedAnime = searchedAnime;
        this.searchAnime(searchedAnime);
      });
    if (this.favouritesService.getValue() === undefined) {
      this.favourites = [];
    } else {
      this.favourites = this.favouritesService.getValue();
    }
  }

  getRequestParams(page: number, pageSize: number, title?: string | null, genresIds?: string[] | null): any {
    let params: any = {};

    if (page) {
      params[`page`] = page;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    if (title) {
      params[`title`] = title;
    }

    if (genresIds) {
      params[`genresIds`] = genresIds;
    }

    return params;
  }

  getPaginatedAnimeList(): void {
    const params = this.getRequestParams(this.page, this.pageSize, this.searchedAnime, this.checkedGenresIds);
      this.animeService.getPaginatedAnimeList(params).subscribe({
        next: (response) => {
          const { pagination, data } = response;
          this.animes = data;
          this.pageSize = pagination.items.per_page;
          this.page = pagination.current_page;
          this.count = pagination.items.total;
        },
        error: (err) => console.log(err)
      });
      this.animeService.getAnimeGenres().subscribe({
        next: (response) => this.genres = response.data,
        error: (err) => console.log(err)
      })
  }

  navigateToReviews(animeId: string) {
    this.router.navigate([`/anime/${animeId}/reviews`]);
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
      const params = this.getRequestParams(this.page, this.pageSize, searchedAnime, this.checkedGenresIds);
      this.animeService.getPaginatedAnimeList(params).subscribe({
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

  handleFilterByGenre(genreId: string, event: Event): void {
    const isChecked = (event.target as HTMLInputElement).checked;
    if (isChecked) {
      this.checkedGenresIds.push(genreId);
    } else {
      this.checkedGenresIds = this.checkedGenresIds.filter(id => id !== genreId);
    }
    const params = this.getRequestParams(this.page, this.pageSize, this.searchedAnime, this.checkedGenresIds);
    this.animeService.getPaginatedAnimeList(params).subscribe({
      next: (response) => {
        const { pagination, data } = response;
        this.animes = data;
        this.pageSize = pagination.items.per_page;
        this.page = pagination.current_page;
        this.count = pagination.items.total;
      },
      error: err => console.log(err)
    });
  }

  isCheckboxChecked(genreId: string): boolean {
    return this.checkedGenresIds.includes(genreId);
  }

  isFavourite(anime: Anime): boolean | undefined {
    let favouritesIds: string[] = [];
    this.favourites.map( favourite => {
      favouritesIds.push(favourite.mal_id);
    });
    return favouritesIds?.includes(anime.mal_id);
  }

  toggleLike(event: MouseEvent, anime: Anime): void {
    event.stopPropagation();
    if (this.isFavourite(anime)) {
      let favouritesIds: string[] = [];
      this.favourites.map( favourite => {
        favouritesIds.push(favourite.mal_id);
      });
      const index: number = favouritesIds.indexOf(anime.mal_id);
      if (index !== -1) {
        this.favourites.splice(index, 1);
      }
    } else {
      this.favourites.push(anime);
    }
    this.favouritesService.setValue(this.favourites);
  }
}
