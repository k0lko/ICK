import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AnimeComponent} from './anime/anime.component';
import {ReviewComponent} from './review/review.component';
import {FavouritesComponent} from './favourites/favourites.component';

const routes: Routes = [
  { path: 'anime', component: AnimeComponent, title: "Lista anime"},
  { path: 'anime/:animeId/reviews', component: ReviewComponent, title: "Lista recenzji"},
  { path: 'anime/favourites', component: FavouritesComponent, title: "Moje Anime"},
  { path: '', redirectTo: '/anime', pathMatch: 'full'},
  { path: '**', redirectTo: '/anime'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
