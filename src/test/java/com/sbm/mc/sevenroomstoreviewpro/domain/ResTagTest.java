package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResTagTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResTagTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResTag.class);
        ResTag resTag1 = getResTagSample1();
        ResTag resTag2 = new ResTag();
        assertThat(resTag1).isNotEqualTo(resTag2);

        resTag2.setId(resTag1.getId());
        assertThat(resTag1).isEqualTo(resTag2);

        resTag2 = getResTagSample2();
        assertThat(resTag1).isNotEqualTo(resTag2);
    }

    @Test
    void reservationTest() {
        ResTag resTag = getResTagRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        resTag.setReservation(reservationBack);
        assertThat(resTag.getReservation()).isEqualTo(reservationBack);

        resTag.reservation(null);
        assertThat(resTag.getReservation()).isNull();
    }
}
