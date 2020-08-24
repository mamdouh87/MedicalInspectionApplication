import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RequestInseptionDetailsService } from 'app/entities/request-inseption-details/request-inseption-details.service';
import { IRequestInseptionDetails, RequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

describe('Service Tests', () => {
  describe('RequestInseptionDetails Service', () => {
    let injector: TestBed;
    let service: RequestInseptionDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequestInseptionDetails;
    let expectedResult: IRequestInseptionDetails | IRequestInseptionDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequestInseptionDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RequestInseptionDetails(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RequestInseptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RequestInseptionDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RequestInseptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            rigthEye: 'BBBBBB',
            leftEye: 'BBBBBB',
            bloodGroup: 'BBBBBB',
            doctorComments: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RequestInseptionDetails', () => {
        const returnedFromService = Object.assign(
          {
            rigthEye: 'BBBBBB',
            leftEye: 'BBBBBB',
            bloodGroup: 'BBBBBB',
            doctorComments: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a RequestInseptionDetails', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
