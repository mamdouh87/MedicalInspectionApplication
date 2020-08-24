import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

@Component({
  selector: 'jhi-request-inseption-details-detail',
  templateUrl: './request-inseption-details-detail.component.html',
})
export class RequestInseptionDetailsDetailComponent implements OnInit {
  requestInseptionDetails: IRequestInseptionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestInseptionDetails }) => (this.requestInseptionDetails = requestInseptionDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
