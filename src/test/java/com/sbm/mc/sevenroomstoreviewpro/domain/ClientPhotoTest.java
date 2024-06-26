package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhotoTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientPhotoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientPhoto.class);
        ClientPhoto clientPhoto1 = getClientPhotoSample1();
        ClientPhoto clientPhoto2 = new ClientPhoto();
        assertThat(clientPhoto1).isNotEqualTo(clientPhoto2);

        clientPhoto2.setId(clientPhoto1.getId());
        assertThat(clientPhoto1).isEqualTo(clientPhoto2);

        clientPhoto2 = getClientPhotoSample2();
        assertThat(clientPhoto1).isNotEqualTo(clientPhoto2);
    }

    @Test
    void clientTest() {
        ClientPhoto clientPhoto = getClientPhotoRandomSampleGenerator();
        Client clientBack = getClientRandomSampleGenerator();

        clientPhoto.setClient(clientBack);
        assertThat(clientPhoto.getClient()).isEqualTo(clientBack);
        assertThat(clientBack.getClientPhoto()).isEqualTo(clientPhoto);

        clientPhoto.client(null);
        assertThat(clientPhoto.getClient()).isNull();
        assertThat(clientBack.getClientPhoto()).isNull();
    }
}
