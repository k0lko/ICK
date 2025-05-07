import { Component } from '@angular/core';
import {Review} from './review.model';
import {ReviewService} from './review.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-review-add',
  standalone: false,
  templateUrl: './review-add.component.html',
  styleUrl: './review-add.component.css'
})
export class ReviewAddComponent {
  animeId: string | null;
  review: Review = {
    content: "",
    rating: ""
  }
  constructor(
    private reviewService: ReviewService,
    private router: Router,
    private route: ActivatedRoute
) {
    this.animeId = this.route.snapshot.paramMap.get('animeId');
  }

  addReview() {
    this.reviewService.addReview(this.animeId, this.review).subscribe({
      next: () => this.router.navigate([`/anime/${this.animeId}/reviews`]),
      error: (err) => console.log(err)
    });
  }

}
