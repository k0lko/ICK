export class Anime {
  title: string;
  episodes: number;
  status: string;
  genre: string;
  rating: number;

  constructor(title: string, episodes: number, status: string, genre: string, rating: number) {
    this.title = title;
    this.episodes = episodes;
    this.status = status;
    this.genre = genre;
    this.rating = rating;
  }
}
