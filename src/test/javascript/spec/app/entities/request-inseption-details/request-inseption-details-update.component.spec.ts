import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestInseptionDetailsUpdateComponent } from 'app/entities/request-inseption-details/request-inseption-details-update.component';
import { RequestInseptionDetailsService } from 'app/entities/request-inseption-details/request-inseption-details.service';
import { RequestInseptionDetails } from 'app/shared/model/request-inseption-details.model';

describe('Component Tests', () => {
  describe('RequestInseptionDetails Management Update Component', () => {
    let comp: RequestInseptionDetailsUpdateComponent;
    let fixture: ComponentFixture<RequestInseptionDetailsUpdateComponent>;
    let service: RequestInseptionDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestInseptionDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequestInseptionDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequestInseptionDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestInseptionDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequestInseptionDetails(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new RequestInseptionDetails();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
