package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicketTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItemTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ResPosTicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResPosTicket.class);
        ResPosTicket resPosTicket1 = getResPosTicketSample1();
        ResPosTicket resPosTicket2 = new ResPosTicket();
        assertThat(resPosTicket1).isNotEqualTo(resPosTicket2);

        resPosTicket2.setId(resPosTicket1.getId());
        assertThat(resPosTicket1).isEqualTo(resPosTicket2);

        resPosTicket2 = getResPosTicketSample2();
        assertThat(resPosTicket1).isNotEqualTo(resPosTicket2);
    }

    @Test
    void resPosticketsItemTest() {
        ResPosTicket resPosTicket = getResPosTicketRandomSampleGenerator();
        ResPosticketsItem resPosticketsItemBack = getResPosticketsItemRandomSampleGenerator();

        resPosTicket.addResPosticketsItem(resPosticketsItemBack);
        assertThat(resPosTicket.getResPosticketsItems()).containsOnly(resPosticketsItemBack);
        assertThat(resPosticketsItemBack.getResPosTicket()).isEqualTo(resPosTicket);

        resPosTicket.removeResPosticketsItem(resPosticketsItemBack);
        assertThat(resPosTicket.getResPosticketsItems()).doesNotContain(resPosticketsItemBack);
        assertThat(resPosticketsItemBack.getResPosTicket()).isNull();

        resPosTicket.resPosticketsItems(new HashSet<>(Set.of(resPosticketsItemBack)));
        assertThat(resPosTicket.getResPosticketsItems()).containsOnly(resPosticketsItemBack);
        assertThat(resPosticketsItemBack.getResPosTicket()).isEqualTo(resPosTicket);

        resPosTicket.setResPosticketsItems(new HashSet<>());
        assertThat(resPosTicket.getResPosticketsItems()).doesNotContain(resPosticketsItemBack);
        assertThat(resPosticketsItemBack.getResPosTicket()).isNull();
    }

    @Test
    void reservationTest() {
        ResPosTicket resPosTicket = getResPosTicketRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        resPosTicket.setReservation(reservationBack);
        assertThat(resPosTicket.getReservation()).isEqualTo(reservationBack);

        resPosTicket.reservation(null);
        assertThat(resPosTicket.getReservation()).isNull();
    }
}
