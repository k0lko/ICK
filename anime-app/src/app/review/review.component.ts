import {Component, OnInit} from '@angular/core';
import {ReviewService} from './review.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-review',
  standalone: false,
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {
  response: any;

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const animeId = this.route.snapshot.paramMap.get('animeId');
    this.reviewService.getReviews(animeId).subscribe({
      next: (reviews) => {
        const { pagination, data } = reviews;
        this.response = data;
      },
      error: (err) => console.log(err)
    })
  }


}
