import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnimeService {

  constructor(private http: HttpClient) {}

  getAnimeList(): Observable<any> {
    return this.http.get('/api/anime');
  }

  filterAnimeByRating(rating: number): Observable<any> {
    const params = new HttpParams().set('minRating', rating);
    return this.http.get('api/anime/filter', {params});
  }

  getAnimeAverageRating(animeId: string): Observable<any> {
    return this.http.get(`api/anime/${animeId}/average-rating`);
  }

}
