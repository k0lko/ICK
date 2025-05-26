class Jpg {
  private _image_url: string;
  private _large_image_url: string;
  private _small_image_url: string;

  get image_url(): string {
    return this._image_url;
  }

  set image_url(value: string) {
    this._image_url = value;
  }

  get large_image_url(): string {
    return this._large_image_url;
  }

  set large_image_url(value: string) {
    this._large_image_url = value;
  }

  get small_image_url(): string {
    return this._small_image_url;
  }

  set small_image_url(value: string) {
    this._small_image_url = value;
  }

  constructor(image_url: string, large_image_url: string, small_image_url: string) {
    this._image_url = image_url;
    this._large_image_url = large_image_url;
    this._small_image_url = small_image_url;
  }
}

class Images {
  private _Jpg: Jpg;

  get Jpg() {
    return this._Jpg;
  }

  set Jpg(value) {
    this._Jpg = value;
  }

  constructor(jpg: Jpg) {
    this._Jpg = jpg;
  }
}
export class Anime {
  images: Images;
  mal_id: string;
  title: string;
  users: any;

  constructor(images: Images, mal_id: string, title: string, users: any) {
    this.images = images;
    this.mal_id = mal_id;
    this.title = title;
    this.users = users;
  }
}
