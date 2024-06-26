package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.VenueTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VenueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Venue.class);
        Venue venue1 = getVenueSample1();
        Venue venue2 = new Venue();
        assertThat(venue1).isNotEqualTo(venue2);

        venue2.setId(venue1.getId());
        assertThat(venue1).isEqualTo(venue2);

        venue2 = getVenueSample2();
        assertThat(venue1).isNotEqualTo(venue2);
    }
}
