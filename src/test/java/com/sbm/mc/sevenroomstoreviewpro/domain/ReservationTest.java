package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomFieldTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicketTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ResTableTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ResTagTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReservationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reservation.class);
        Reservation reservation1 = getReservationSample1();
        Reservation reservation2 = new Reservation();
        assertThat(reservation1).isNotEqualTo(reservation2);

        reservation2.setId(reservation1.getId());
        assertThat(reservation1).isEqualTo(reservation2);

        reservation2 = getReservationSample2();
        assertThat(reservation1).isNotEqualTo(reservation2);
    }

    @Test
    void resTagTest() {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResTag resTagBack = getResTagRandomSampleGenerator();

        reservation.addResTag(resTagBack);
        assertThat(reservation.getResTags()).containsOnly(resTagBack);
        assertThat(resTagBack.getReservation()).isEqualTo(reservation);

        reservation.removeResTag(resTagBack);
        assertThat(reservation.getResTags()).doesNotContain(resTagBack);
        assertThat(resTagBack.getReservation()).isNull();

        reservation.resTags(new HashSet<>(Set.of(resTagBack)));
        assertThat(reservation.getResTags()).containsOnly(resTagBack);
        assertThat(resTagBack.getReservation()).isEqualTo(reservation);

        reservation.setResTags(new HashSet<>());
        assertThat(reservation.getResTags()).doesNotContain(resTagBack);
        assertThat(resTagBack.getReservation()).isNull();
    }

    @Test
    void resPosTicketTest() {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResPosTicket resPosTicketBack = getResPosTicketRandomSampleGenerator();

        reservation.addResPosTicket(resPosTicketBack);
        assertThat(reservation.getResPosTickets()).containsOnly(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isEqualTo(reservation);

        reservation.removeResPosTicket(resPosTicketBack);
        assertThat(reservation.getResPosTickets()).doesNotContain(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isNull();

        reservation.resPosTickets(new HashSet<>(Set.of(resPosTicketBack)));
        assertThat(reservation.getResPosTickets()).containsOnly(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isEqualTo(reservation);

        reservation.setResPosTickets(new HashSet<>());
        assertThat(reservation.getResPosTickets()).doesNotContain(resPosTicketBack);
        assertThat(resPosTicketBack.getReservation()).isNull();
    }

    @Test
    void resCustomFieldTest() {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResCustomField resCustomFieldBack = getResCustomFieldRandomSampleGenerator();

        reservation.addResCustomField(resCustomFieldBack);
        assertThat(reservation.getResCustomFields()).containsOnly(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isEqualTo(reservation);

        reservation.removeResCustomField(resCustomFieldBack);
        assertThat(reservation.getResCustomFields()).doesNotContain(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isNull();

        reservation.resCustomFields(new HashSet<>(Set.of(resCustomFieldBack)));
        assertThat(reservation.getResCustomFields()).containsOnly(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isEqualTo(reservation);

        reservation.setResCustomFields(new HashSet<>());
        assertThat(reservation.getResCustomFields()).doesNotContain(resCustomFieldBack);
        assertThat(resCustomFieldBack.getReservation()).isNull();
    }

    @Test
    void resTableTest() {
        Reservation reservation = getReservationRandomSampleGenerator();
        ResTable resTableBack = getResTableRandomSampleGenerator();

        reservation.addResTable(resTableBack);
        assertThat(reservation.getResTables()).containsOnly(resTableBack);
        assertThat(resTableBack.getReservation()).isEqualTo(reservation);

        reservation.removeResTable(resTableBack);
        assertThat(reservation.getResTables()).doesNotContain(resTableBack);
        assertThat(resTableBack.getReservation()).isNull();

        reservation.resTables(new HashSet<>(Set.of(resTableBack)));
        assertThat(reservation.getResTables()).containsOnly(resTableBack);
        assertThat(resTableBack.getReservation()).isEqualTo(reservation);

        reservation.setResTables(new HashSet<>());
        assertThat(reservation.getResTables()).doesNotContain(resTableBack);
        assertThat(resTableBack.getReservation()).isNull();
    }
}
