import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMedicalInsepctionRequests, MedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';
import { MedicalInsepctionRequestsService } from './medical-insepction-requests.service';
import { IPersons } from 'app/shared/model/persons.model';
import { PersonsService } from 'app/entities/persons/persons.service';
import { IRequestBiometricData } from 'app/shared/model/request-biometric-data.model';
import { RequestBiometricDataService } from 'app/entities/request-biometric-data/request-biometric-data.service';
import { ILicenseCategory } from 'app/shared/model/license-category.model';
import { LicenseCategoryService } from 'app/entities/license-category/license-category.service';
import { ITransactionType } from 'app/shared/model/transaction-type.model';
import { TransactionTypeService } from 'app/entities/transaction-type/transaction-type.service';
import { ITrafficUnits } from 'app/shared/model/traffic-units.model';
import { TrafficUnitsService } from 'app/entities/traffic-units/traffic-units.service';
import { IRequestStatus } from 'app/shared/model/request-status.model';
import { RequestStatusService } from 'app/entities/request-status/request-status.service';

type SelectableEntity = IPersons | IRequestBiometricData | ILicenseCategory | ITransactionType | ITrafficUnits | IRequestStatus;

@Component({
  selector: 'jhi-medical-insepction-requests-update',
  templateUrl: './medical-insepction-requests-update.component.html',
})
export class MedicalInsepctionRequestsUpdateComponent implements OnInit {
  isSaving = false;
  people: IPersons[] = [];
  biometricdata: IRequestBiometricData[] = [];
  licensecategories: ILicenseCategory[] = [];
  transaciontypes: ITransactionType[] = [];
  trafficunits: ITrafficUnits[] = [];
  statuses: IRequestStatus[] = [];

  editForm = this.fb.group({
    id: [],
    requestNumber: [],
    personId: [],
    biometricdataId: [],
    licenseCategoryId: [],
    transacionTypeId: [],
    trafficUnitId: [],
    statusId: [],
  });

  constructor(
    protected medicalInsepctionRequestsService: MedicalInsepctionRequestsService,
    protected personsService: PersonsService,
    protected requestBiometricDataService: RequestBiometricDataService,
    protected licenseCategoryService: LicenseCategoryService,
    protected transactionTypeService: TransactionTypeService,
    protected trafficUnitsService: TrafficUnitsService,
    protected requestStatusService: RequestStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalInsepctionRequests }) => {
      this.updateForm(medicalInsepctionRequests);

      this.personsService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<IPersons[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersons[]) => {
          if (!medicalInsepctionRequests.personId) {
            this.people = resBody;
          } else {
            this.personsService
              .find(medicalInsepctionRequests.personId)
              .pipe(
                map((subRes: HttpResponse<IPersons>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersons[]) => (this.people = concatRes));
          }
        });

      this.requestBiometricDataService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<IRequestBiometricData[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRequestBiometricData[]) => {
          if (!medicalInsepctionRequests.biometricdataId) {
            this.biometricdata = resBody;
          } else {
            this.requestBiometricDataService
              .find(medicalInsepctionRequests.biometricdataId)
              .pipe(
                map((subRes: HttpResponse<IRequestBiometricData>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRequestBiometricData[]) => (this.biometricdata = concatRes));
          }
        });

      this.licenseCategoryService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<ILicenseCategory[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILicenseCategory[]) => {
          if (!medicalInsepctionRequests.licenseCategoryId) {
            this.licensecategories = resBody;
          } else {
            this.licenseCategoryService
              .find(medicalInsepctionRequests.licenseCategoryId)
              .pipe(
                map((subRes: HttpResponse<ILicenseCategory>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILicenseCategory[]) => (this.licensecategories = concatRes));
          }
        });

      this.transactionTypeService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<ITransactionType[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITransactionType[]) => {
          if (!medicalInsepctionRequests.transacionTypeId) {
            this.transaciontypes = resBody;
          } else {
            this.transactionTypeService
              .find(medicalInsepctionRequests.transacionTypeId)
              .pipe(
                map((subRes: HttpResponse<ITransactionType>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITransactionType[]) => (this.transaciontypes = concatRes));
          }
        });

      this.trafficUnitsService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<ITrafficUnits[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITrafficUnits[]) => {
          if (!medicalInsepctionRequests.trafficUnitId) {
            this.trafficunits = resBody;
          } else {
            this.trafficUnitsService
              .find(medicalInsepctionRequests.trafficUnitId)
              .pipe(
                map((subRes: HttpResponse<ITrafficUnits>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITrafficUnits[]) => (this.trafficunits = concatRes));
          }
        });

      this.requestStatusService
        .query({ filter: 'medicalinsepctionrequests-is-null' })
        .pipe(
          map((res: HttpResponse<IRequestStatus[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRequestStatus[]) => {
          if (!medicalInsepctionRequests.statusId) {
            this.statuses = resBody;
          } else {
            this.requestStatusService
              .find(medicalInsepctionRequests.statusId)
              .pipe(
                map((subRes: HttpResponse<IRequestStatus>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRequestStatus[]) => (this.statuses = concatRes));
          }
        });
    });
  }

  updateForm(medicalInsepctionRequests: IMedicalInsepctionRequests): void {
    this.editForm.patchValue({
      id: medicalInsepctionRequests.id,
      requestNumber: medicalInsepctionRequests.requestNumber,
      personId: medicalInsepctionRequests.personId,
      biometricdataId: medicalInsepctionRequests.biometricdataId,
      licenseCategoryId: medicalInsepctionRequests.licenseCategoryId,
      transacionTypeId: medicalInsepctionRequests.transacionTypeId,
      trafficUnitId: medicalInsepctionRequests.trafficUnitId,
      statusId: medicalInsepctionRequests.statusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalInsepctionRequests = this.createFromForm();
    if (medicalInsepctionRequests.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalInsepctionRequestsService.update(medicalInsepctionRequests));
    } else {
      this.subscribeToSaveResponse(this.medicalInsepctionRequestsService.create(medicalInsepctionRequests));
    }
  }

  private createFromForm(): IMedicalInsepctionRequests {
    return {
      ...new MedicalInsepctionRequests(),
      id: this.editForm.get(['id'])!.value,
      requestNumber: this.editForm.get(['requestNumber'])!.value,
      personId: this.editForm.get(['personId'])!.value,
      biometricdataId: this.editForm.get(['biometricdataId'])!.value,
      licenseCategoryId: this.editForm.get(['licenseCategoryId'])!.value,
      transacionTypeId: this.editForm.get(['transacionTypeId'])!.value,
      trafficUnitId: this.editForm.get(['trafficUnitId'])!.value,
      statusId: this.editForm.get(['statusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalInsepctionRequests>>): void {
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
