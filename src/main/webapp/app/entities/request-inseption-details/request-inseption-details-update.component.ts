import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRequestInseptionDetails, RequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';
import { RequestInseptionDetailsService } from './request-inseption-details.service';
import { IMedicalCondition } from 'app/shared/model/medical-condition.model';
import { MedicalConditionService } from 'app/entities/medical-condition/medical-condition.service';
import { IInspectionType } from 'app/shared/model/inspection-type.model';
import { InspectionTypeService } from 'app/entities/inspection-type/inspection-type.service';
import { IInspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from 'app/entities/inspection-result/inspection-result.service';
import { IMedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';
import { MedicalInsepctionRequestsService } from 'app/entities/medical-insepction-requests/medical-insepction-requests.service';

type SelectableEntity = IMedicalCondition | IInspectionType | IInspectionResult | IMedicalInsepctionRequests;

@Component({
  selector: 'jhi-request-inseption-details-update',
  templateUrl: './request-inseption-details-update.component.html',
})
export class RequestInseptionDetailsUpdateComponent implements OnInit {
  isSaving = false;
  medicalconditions: IMedicalCondition[] = [];
  inspectiontypes: IInspectionType[] = [];
  inspectionresults: IInspectionResult[] = [];
  medicalinsepctionrequests: IMedicalInsepctionRequests[] = [];

  editForm = this.fb.group({
    id: [],
    rigthEye: [],
    leftEye: [],
    bloodGroup: [],
    doctorComments: [],
    medicalConditionId: [],
    inspectionTypeId: [],
    inspectionResultId: [],
    medicalInsepctionRequestsId: [],
  });

  constructor(
    protected requestInseptionDetailsService: RequestInseptionDetailsService,
    protected medicalConditionService: MedicalConditionService,
    protected inspectionTypeService: InspectionTypeService,
    protected inspectionResultService: InspectionResultService,
    protected medicalInsepctionRequestsService: MedicalInsepctionRequestsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestInseptionDetails }) => {
      this.updateForm(requestInseptionDetails);

      this.medicalConditionService
        .query({ filter: 'requestinseptiondetails-is-null' })
        .pipe(
          map((res: HttpResponse<IMedicalCondition[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMedicalCondition[]) => {
          if (!requestInseptionDetails.medicalConditionId) {
            this.medicalconditions = resBody;
          } else {
            this.medicalConditionService
              .find(requestInseptionDetails.medicalConditionId)
              .pipe(
                map((subRes: HttpResponse<IMedicalCondition>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMedicalCondition[]) => (this.medicalconditions = concatRes));
          }
        });

      this.inspectionTypeService
        .query({ filter: 'requestinseptiondetails-is-null' })
        .pipe(
          map((res: HttpResponse<IInspectionType[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInspectionType[]) => {
          if (!requestInseptionDetails.inspectionTypeId) {
            this.inspectiontypes = resBody;
          } else {
            this.inspectionTypeService
              .find(requestInseptionDetails.inspectionTypeId)
              .pipe(
                map((subRes: HttpResponse<IInspectionType>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInspectionType[]) => (this.inspectiontypes = concatRes));
          }
        });

      this.inspectionResultService
        .query({ filter: 'requestinseptiondetails-is-null' })
        .pipe(
          map((res: HttpResponse<IInspectionResult[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInspectionResult[]) => {
          if (!requestInseptionDetails.inspectionResultId) {
            this.inspectionresults = resBody;
          } else {
            this.inspectionResultService
              .find(requestInseptionDetails.inspectionResultId)
              .pipe(
                map((subRes: HttpResponse<IInspectionResult>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInspectionResult[]) => (this.inspectionresults = concatRes));
          }
        });

      this.medicalInsepctionRequestsService
        .query()
        .subscribe((res: HttpResponse<IMedicalInsepctionRequests[]>) => (this.medicalinsepctionrequests = res.body || []));
    });
  }

  updateForm(requestInseptionDetails: IRequestInseptionDetails): void {
    this.editForm.patchValue({
      id: requestInseptionDetails.id,
      rigthEye: requestInseptionDetails.rigthEye,
      leftEye: requestInseptionDetails.leftEye,
      bloodGroup: requestInseptionDetails.bloodGroup,
      doctorComments: requestInseptionDetails.doctorComments,
      medicalConditionId: requestInseptionDetails.medicalConditionId,
      inspectionTypeId: requestInseptionDetails.inspectionTypeId,
      inspectionResultId: requestInseptionDetails.inspectionResultId,
      medicalInsepctionRequestsId: requestInseptionDetails.medicalInsepctionRequestsId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestInseptionDetails = this.createFromForm();
    if (requestInseptionDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.requestInseptionDetailsService.update(requestInseptionDetails));
    } else {
      this.subscribeToSaveResponse(this.requestInseptionDetailsService.create(requestInseptionDetails));
    }
  }

  private createFromForm(): IRequestInseptionDetails {
    return {
      ...new RequestInseptionDetails(),
      id: this.editForm.get(['id'])!.value,
      rigthEye: this.editForm.get(['rigthEye'])!.value,
      leftEye: this.editForm.get(['leftEye'])!.value,
      bloodGroup: this.editForm.get(['bloodGroup'])!.value,
      doctorComments: this.editForm.get(['doctorComments'])!.value,
      medicalConditionId: this.editForm.get(['medicalConditionId'])!.value,
      inspectionTypeId: this.editForm.get(['inspectionTypeId'])!.value,
      inspectionResultId: this.editForm.get(['inspectionResultId'])!.value,
      medicalInsepctionRequestsId: this.editForm.get(['medicalInsepctionRequestsId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestInseptionDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
