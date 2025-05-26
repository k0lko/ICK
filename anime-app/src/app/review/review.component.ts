import {AfterViewInit, Component, OnInit, ViewChildren, ElementRef, QueryList, Renderer2} from '@angular/core';
import {ReviewService} from './review.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-review',
  standalone: false,
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit, AfterViewInit {
  response: any;
  @ViewChildren('contentDiv') contentElements!: QueryList<ElementRef>;

  constructor(
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private renderer: Renderer2
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

  ngAfterViewInit() {
    this.contentElements.changes.subscribe(() => {
      this.contentElements.forEach((element) => {
        const contentParagraph = element.nativeElement.children[1];
        if (contentParagraph.offsetHeight > 312) {
          this.renderer.addClass(contentParagraph, 'review-short');
          const button = this.renderer.createElement('button');
          const text = this.renderer.createText('Kliknij aby rozwinąć');
          this.renderer.listen(button, 'click', () => {
            const currentText = button.innerText;
            if (currentText === "Kliknij aby rozwinąć") {
              this.renderer.removeClass(contentParagraph, 'review-short');
              this.renderer.setProperty(button, 'innerText', "Kliknij aby zwinąć");
            } else {
              this.renderer.addClass(contentParagraph, 'review-short');
              this.renderer.setProperty(button, 'innerText', "Kliknij aby rozwinąć");
            }
          });
          this.renderer.appendChild(button, text);
          const buttonParagraph = this.renderer.createElement('p');
          this.renderer.appendChild(buttonParagraph, button);
          this.renderer.insertBefore(element.nativeElement, buttonParagraph, element.nativeElement.children[2]);
        }
      });
    });
  }

  handleExpandingReview() {

  }

}
