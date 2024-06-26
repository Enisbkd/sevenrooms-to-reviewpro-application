package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RvpGuestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RvpGuest.class);
        RvpGuest rvpGuest1 = getRvpGuestSample1();
        RvpGuest rvpGuest2 = new RvpGuest();
        assertThat(rvpGuest1).isNotEqualTo(rvpGuest2);

        rvpGuest2.setId(rvpGuest1.getId());
        assertThat(rvpGuest1).isEqualTo(rvpGuest2);

        rvpGuest2 = getRvpGuestSample2();
        assertThat(rvpGuest1).isNotEqualTo(rvpGuest2);
    }
}
