import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PageDto} from '../interfaces/volunteer';
import {ItemDtoForProduct} from '../interfaces/item-dto-for-product';
import {RequestStatusDto} from '../interfaces/request-status-dto';
import {MessageDto} from '../interfaces/AddImage/message-dto';
import {RequestWithNeedyInfo} from '../interfaces/request-with-needy-info';
import {ItemDto} from '../interfaces/item-dto';



@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor(private http:HttpClient) { }

  public getAllItemDetailsByVolunteerId(volunteerId: Number ,pageNo: number, pageSize: number): Observable<PageDto<ItemDtoForProduct>> {
    return this.http.get<PageDto<ItemDtoForProduct>>(`http://localhost:8080/volunteer/allItemsDetailsById/${volunteerId}`, {
      params: { pageNo: pageNo, pageSize: pageSize }
    });
  }

  public changeRequestStatus(requestId: number, requestDto: RequestStatusDto): Observable<MessageDto<RequestStatusDto>> {

    return this.http.put<MessageDto<RequestStatusDto>>(`http://localhost:8080/volunteer/changeRequestStatus/${requestId}`, requestDto);
  }
 public deleteVolunteerItemByItemId(id: number): Observable<MessageDto<string>> {
    const url = `http://localhost:8080/item/${id}`;
    return this.http.delete<MessageDto<string>>(url);
  }
  // Method to update an item
  updateItem(id: number, itemDto: any): Observable<MessageDto<any>> {
    return this.http.put<MessageDto<any>>(`http://localhost:8080/item/${id}`, itemDto);
  }
  public getItemRequestsWithNeedyInfo(itemId: number ,pageNo: number, pageSize: number): Observable<PageDto<RequestWithNeedyInfo>> {
    // const params = new HttpParams().set('pageNo',pageNo ).set('pageSize',pageSize);

    return this.http.get<PageDto<RequestWithNeedyInfo>>(`http://localhost:8080/request/requestsWithNeedyDetailsByItemId/${itemId}`,
      {params:{pageNo:pageNo, pageSize:pageSize}});
  }
  getItemById(id: number): Observable<ItemDto> {
    return this.http.get<ItemDto>(`http://localhost:8080/item/${id}`);
  }


}
