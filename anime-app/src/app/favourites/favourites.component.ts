import {Component, Input, OnInit} from '@angular/core';
import {AnimeService} from '../anime/anime.service';
import {Router} from '@angular/router';
import {FavouritesService} from './favourites.service';
import {Anime} from '../anime/anime.model';

@Component({
  selector: 'app-favourites',
  standalone: false,
  templateUrl: './favourites.component.html',
  styleUrl: './favourites.component.css'
})
export class FavouritesComponent implements OnInit {

  @Input() favourites: any;

  constructor(
    private animeService: AnimeService,
    private favouritesService: FavouritesService,
    private router: Router
  ) {}

  navigateToReviews(favouriteId: string) {
    this.router.navigate([`/anime/${favouriteId}/reviews`]);
  }

  ngOnInit(): void {
    this.favourites = this.favouritesService.getValue();
  }

}
