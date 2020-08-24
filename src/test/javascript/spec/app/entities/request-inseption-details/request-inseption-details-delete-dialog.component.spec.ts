import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { RequestInseptionDetailsDeleteDialogComponent } from 'app/entities/request-inseption-details/request-inseption-details-delete-dialog.component';
import { RequestInseptionDetailsService } from 'app/entities/request-inseption-details/request-inseption-details.service';

describe('Component Tests', () => {
  describe('RequestInseptionDetails Management Delete Component', () => {
    let comp: RequestInseptionDetailsDeleteDialogComponent;
    let fixture: ComponentFixture<RequestInseptionDetailsDeleteDialogComponent>;
    let service: RequestInseptionDetailsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestInseptionDetailsDeleteDialogComponent],
      })
        .overrideTemplate(RequestInseptionDetailsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequestInseptionDetailsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequestInseptionDetailsService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
