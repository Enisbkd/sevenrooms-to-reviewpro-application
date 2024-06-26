package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;

public interface ConstructGuestService {
    RvpGuest constructGuest(ReservationPayload reservationPayload);

    Boolean validateReservation(ReservationPayload reservationPayload);
}
