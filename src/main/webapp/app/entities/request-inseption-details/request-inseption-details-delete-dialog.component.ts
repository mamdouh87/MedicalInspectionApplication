import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';
import { RequestInseptionDetailsService } from './request-inseption-details.service';

@Component({
  templateUrl: './request-inseption-details-delete-dialog.component.html',
})
export class RequestInseptionDetailsDeleteDialogComponent {
  requestInseptionDetails?: IRequestInseptionDetails;

  constructor(
    protected requestInseptionDetailsService: RequestInseptionDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestInseptionDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('requestInseptionDetailsListModification');
      this.activeModal.close();
    });
  }
}
