import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

type EntityResponseType = HttpResponse<IRequestInseptionDetails>;
type EntityArrayResponseType = HttpResponse<IRequestInseptionDetails[]>;

@Injectable({ providedIn: 'root' })
export class RequestInseptionDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/request-inseption-details';

  constructor(protected http: HttpClient) {}

  create(requestInseptionDetails: IRequestInseptionDetails): Observable<EntityResponseType> {
    return this.http.post<IRequestInseptionDetails>(this.resourceUrl, requestInseptionDetails, { observe: 'response' });
  }

  update(requestInseptionDetails: IRequestInseptionDetails): Observable<EntityResponseType> {
    return this.http.put<IRequestInseptionDetails>(this.resourceUrl, requestInseptionDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequestInseptionDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequestInseptionDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
