import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequestInseptionDetails, RequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';
import { RequestInseptionDetailsService } from './request-inseption-details.service';
import { RequestInseptionDetailsComponent } from './request-inseption-details.component';
import { RequestInseptionDetailsDetailComponent } from './request-inseption-details-detail.component';
import { RequestInseptionDetailsUpdateComponent } from './request-inseption-details-update.component';

@Injectable({ providedIn: 'root' })
export class RequestInseptionDetailsResolve implements Resolve<IRequestInseptionDetails> {
  constructor(private service: RequestInseptionDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequestInseptionDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requestInseptionDetails: HttpResponse<RequestInseptionDetails>) => {
          if (requestInseptionDetails.body) {
            return of(requestInseptionDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequestInseptionDetails());
  }
}

export const requestInseptionDetailsRoute: Routes = [
  {
    path: '',
    component: RequestInseptionDetailsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'medicalInspectionApplicationApp.requestInseptionDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequestInseptionDetailsDetailComponent,
    resolve: {
      requestInseptionDetails: RequestInseptionDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestInseptionDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequestInseptionDetailsUpdateComponent,
    resolve: {
      requestInseptionDetails: RequestInseptionDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestInseptionDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequestInseptionDetailsUpdateComponent,
    resolve: {
      requestInseptionDetails: RequestInseptionDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'medicalInspectionApplicationApp.requestInseptionDetails.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
