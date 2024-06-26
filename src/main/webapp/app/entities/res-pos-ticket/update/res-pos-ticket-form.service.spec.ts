import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../res-pos-ticket.test-samples';

import { ResPosTicketFormService } from './res-pos-ticket-form.service';

describe('ResPosTicket Form Service', () => {
  let service: ResPosTicketFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResPosTicketFormService);
  });

  describe('Service methods', () => {
    describe('createResPosTicketFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createResPosTicketFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            adminFee: expect.any(Object),
            code: expect.any(Object),
            tableNo: expect.any(Object),
            tax: expect.any(Object),
            businessId: expect.any(Object),
            ticketId: expect.any(Object),
            localPosticketId: expect.any(Object),
            employeeName: expect.any(Object),
            total: expect.any(Object),
            subtotal: expect.any(Object),
            startTime: expect.any(Object),
            serviceCharge: expect.any(Object),
            endtime: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });

      it('passing IResPosTicket should create a new form with FormGroup', () => {
        const formGroup = service.createResPosTicketFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            adminFee: expect.any(Object),
            code: expect.any(Object),
            tableNo: expect.any(Object),
            tax: expect.any(Object),
            businessId: expect.any(Object),
            ticketId: expect.any(Object),
            localPosticketId: expect.any(Object),
            employeeName: expect.any(Object),
            total: expect.any(Object),
            subtotal: expect.any(Object),
            startTime: expect.any(Object),
            serviceCharge: expect.any(Object),
            endtime: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });
    });

    describe('getResPosTicket', () => {
      it('should return NewResPosTicket for default ResPosTicket initial value', () => {
        const formGroup = service.createResPosTicketFormGroup(sampleWithNewData);

        const resPosTicket = service.getResPosTicket(formGroup) as any;

        expect(resPosTicket).toMatchObject(sampleWithNewData);
      });

      it('should return NewResPosTicket for empty ResPosTicket initial value', () => {
        const formGroup = service.createResPosTicketFormGroup();

        const resPosTicket = service.getResPosTicket(formGroup) as any;

        expect(resPosTicket).toMatchObject({});
      });

      it('should return IResPosTicket', () => {
        const formGroup = service.createResPosTicketFormGroup(sampleWithRequiredData);

        const resPosTicket = service.getResPosTicket(formGroup) as any;

        expect(resPosTicket).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IResPosTicket should not enable id FormControl', () => {
        const formGroup = service.createResPosTicketFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewResPosTicket should disable id FormControl', () => {
        const formGroup = service.createResPosTicketFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
