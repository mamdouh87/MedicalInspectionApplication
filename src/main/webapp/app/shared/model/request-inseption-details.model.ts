export interface IRequestInseptionDetails {
  id?: number;
  rigthEye?: string;
  leftEye?: string;
  bloodGroup?: string;
  doctorComments?: string;
  medicalConditionId?: number;
  inspectionTypeId?: number;
  inspectionResultId?: number;
  medicalInsepctionRequestsId?: number;
}

export class RequestInseptionDetails implements IRequestInseptionDetails {
  constructor(
    public id?: number,
    public rigthEye?: string,
    public leftEye?: string,
    public bloodGroup?: string,
    public doctorComments?: string,
    public medicalConditionId?: number,
    public inspectionTypeId?: number,
    public inspectionResultId?: number,
    public medicalInsepctionRequestsId?: number
  ) {}
}
