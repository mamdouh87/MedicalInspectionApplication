import { IRequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

export interface IMedicalInsepctionRequests {
  id?: number;
  requestNumber?: string;
  personId?: number;
  biometricdataId?: number;
  licenseCategoryId?: number;
  transacionTypeId?: number;
  trafficUnitId?: number;
  statusId?: number;
  medicalInsepctionRequests?: IRequestInseptionDetails[];
}

export class MedicalInsepctionRequests implements IMedicalInsepctionRequests {
  constructor(
    public id?: number,
    public requestNumber?: string,
    public personId?: number,
    public biometricdataId?: number,
    public licenseCategoryId?: number,
    public transacionTypeId?: number,
    public trafficUnitId?: number,
    public statusId?: number,
    public medicalInsepctionRequests?: IRequestInseptionDetails[]
  ) {}
}
