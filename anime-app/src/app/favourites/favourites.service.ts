import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {
  public favourites: any;

  getValue(): any {
    console.log(this.favourites);
    return this.favourites;
  }

  setValue(favourites: any) {
    this.favourites = favourites;
    console.log(this.favourites)
  }
}
