package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicketTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItemTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResPosticketsItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResPosticketsItem.class);
        ResPosticketsItem resPosticketsItem1 = getResPosticketsItemSample1();
        ResPosticketsItem resPosticketsItem2 = new ResPosticketsItem();
        assertThat(resPosticketsItem1).isNotEqualTo(resPosticketsItem2);

        resPosticketsItem2.setId(resPosticketsItem1.getId());
        assertThat(resPosticketsItem1).isEqualTo(resPosticketsItem2);

        resPosticketsItem2 = getResPosticketsItemSample2();
        assertThat(resPosticketsItem1).isNotEqualTo(resPosticketsItem2);
    }

    @Test
    void resPosTicketTest() {
        ResPosticketsItem resPosticketsItem = getResPosticketsItemRandomSampleGenerator();
        ResPosTicket resPosTicketBack = getResPosTicketRandomSampleGenerator();

        resPosticketsItem.setResPosTicket(resPosTicketBack);
        assertThat(resPosticketsItem.getResPosTicket()).isEqualTo(resPosTicketBack);

        resPosticketsItem.resPosTicket(null);
        assertThat(resPosticketsItem.getResPosTicket()).isNull();
    }
}
