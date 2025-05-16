import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnimeComponent } from './anime/anime.component';
import {provideHttpClient} from '@angular/common/http';
import {NgFor} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ReviewComponent } from './review/review.component';
import { NavComponent } from './nav/nav.component';
import { ReviewAddComponent } from './review/review-add.component';
import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    AnimeComponent,
    ReviewComponent,
    NavComponent,
    ReviewAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgFor,
    FormsModule,
    NgxPaginationModule,
    ReactiveFormsModule
  ],
  providers: [provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }
