import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

import {ItemDtoForProduct} from '../components/interfaces/item-dto-for-product';
import {PageDto} from '../components/interfaces/volunteer';
import {RequestDto} from '../components/interfaces/request-dto';
import {MessageDto} from '../components/interfaces/AddImage/message-dto';
import {ItemDto} from '../components/interfaces/item-dto';
import {RequestWithNeedyInfo} from '../components/interfaces/request-with-needy-info';
import {RequestStatusDto} from '../components/interfaces/request-status-dto';
import {FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor(private http:HttpClient) { }

  public getAllItemDetailsByVolunteerId(volunteerId: Number ,pageNo: number, pageSize: number, filterForm: FormGroup): Observable<PageDto<ItemDtoForProduct>> {
    const filterValues = filterForm.value;
    const queryParams = new HttpParams()
      .set('pageNo', pageNo)
      .set('pageSize', pageSize)
      .set('status', filterValues.status || '')
      .set('genderSuitability', filterValues.genderSuitability || '');
    return this.http.get<PageDto<ItemDtoForProduct>>(`/volunteer/allItemsDetailsById/${volunteerId}`, {
      params: queryParams
    });
  }

  public changeRequestStatus(requestId: number, requestDto: RequestStatusDto): Observable<MessageDto<RequestStatusDto>> {

    return this.http.put<MessageDto<RequestStatusDto>>(`volunteer/changeRequestStatus/${requestId}`, requestDto);
  }
 public deleteVolunteerItemByItemId(id: number): Observable<MessageDto<string>> {
    const url = `/item/${id}`;
    return this.http.delete<MessageDto<string>>(url);
  }
  // Method to update an item
  updateItem(id: number, itemDto: any): Observable<MessageDto<any>> {
    return this.http.put<MessageDto<any>>(`item/${id}`, itemDto);
  }
  public getItemRequestsWithNeedyInfo(itemId: number ,pageNo: number, pageSize: number): Observable<PageDto<RequestWithNeedyInfo>> {
    // const params = new HttpParams().set('pageNo',pageNo ).set('pageSize',pageSize);

    return this.http.get<PageDto<RequestWithNeedyInfo>>(`/request/requestsWithNeedyDetailsByItemId/${itemId}`,
      {params:{pageNo:pageNo, pageSize:pageSize}});
  }
  getItemById(id: number): Observable<ItemDto> {
    return this.http.get<ItemDto>(`/item/${id}`);
  }


}
