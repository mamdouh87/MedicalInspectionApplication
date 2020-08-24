import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { RequestInseptionDetailsComponent } from './request-inseption-details.component';
import { RequestInseptionDetailsDetailComponent } from './request-inseption-details-detail.component';
import { RequestInseptionDetailsUpdateComponent } from './request-inseption-details-update.component';
import { RequestInseptionDetailsDeleteDialogComponent } from './request-inseption-details-delete-dialog.component';
import { requestInseptionDetailsRoute } from './request-inseption-details.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(requestInseptionDetailsRoute)],
  declarations: [
    RequestInseptionDetailsComponent,
    RequestInseptionDetailsDetailComponent,
    RequestInseptionDetailsUpdateComponent,
    RequestInseptionDetailsDeleteDialogComponent,
  ],
  entryComponents: [RequestInseptionDetailsDeleteDialogComponent],
})
export class MedicalInspectionApplicationRequestInseptionDetailsModule {}
