import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestInseptionDetailsDetailComponent } from 'app/entities/request-inseption-details/request-inseption-details-detail.component';
import { RequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

describe('Component Tests', () => {
  describe('RequestInseptionDetails Management Detail Component', () => {
    let comp: RequestInseptionDetailsDetailComponent;
    let fixture: ComponentFixture<RequestInseptionDetailsDetailComponent>;
    const route = ({ data: of({ requestInseptionDetails: new RequestInseptionDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestInseptionDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequestInseptionDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequestInseptionDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requestInseptionDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requestInseptionDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
