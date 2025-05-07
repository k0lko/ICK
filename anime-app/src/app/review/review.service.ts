import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Review} from './review.model';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  constructor(private http: HttpClient) {}

  getReviews(animeId: string | null): Observable<any> {
    return this.http.get(`/api/anime/${animeId}/reviews`);
  }

  addReview(animeId: string | null, review: Review): Observable<Review> {
    return this.http.post<Review>(`/api/anime/${animeId}/reviews`, review);
  }

}
