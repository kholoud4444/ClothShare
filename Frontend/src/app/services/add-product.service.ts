import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MessageDto} from '../interfaces/AddImage/message-dto';
import {ImageUrl} from '../interfaces/AddImage/image-url';
import {ItemDto} from '../interfaces/item-dto';


@Injectable({
  providedIn: 'root'
})
export class AddProductService {

  constructor(private http: HttpClient) { }

  public uploadImage(image: File): Observable<MessageDto<ImageUrl>> {
    const formData = new FormData();
    formData.append('image', image, image.name);

    // Post the form data to the backend
    return this.http.post<MessageDto<ImageUrl>>("http://localhost:8080/item/uploadImage", formData);
  }
  public createItem(itemDto: ItemDto): Observable<ItemDto> {
    return this.http.post<ItemDto>("http://localhost:8080/item/createItem", itemDto);
  }
}

