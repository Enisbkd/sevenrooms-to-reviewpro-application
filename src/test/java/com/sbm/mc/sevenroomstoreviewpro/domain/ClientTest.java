package com.sbm.mc.sevenroomstoreviewpro.domain;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhotoTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientTagTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStatsTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.CustomFieldTestSamples.*;
import static com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroupTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Client.class);
        Client client1 = getClientSample1();
        Client client2 = new Client();
        assertThat(client1).isNotEqualTo(client2);

        client2.setId(client1.getId());
        assertThat(client1).isEqualTo(client2);

        client2 = getClientSample2();
        assertThat(client1).isNotEqualTo(client2);
    }

    @Test
    void clientPhotoTest() {
        Client client = getClientRandomSampleGenerator();
        ClientPhoto clientPhotoBack = getClientPhotoRandomSampleGenerator();

        client.setClientPhoto(clientPhotoBack);
        assertThat(client.getClientPhoto()).isEqualTo(clientPhotoBack);

        client.clientPhoto(null);
        assertThat(client.getClientPhoto()).isNull();
    }

    @Test
    void clientVenueStatsTest() {
        Client client = getClientRandomSampleGenerator();
        ClientVenueStats clientVenueStatsBack = getClientVenueStatsRandomSampleGenerator();

        client.setClientVenueStats(clientVenueStatsBack);
        assertThat(client.getClientVenueStats()).isEqualTo(clientVenueStatsBack);

        client.clientVenueStats(null);
        assertThat(client.getClientVenueStats()).isNull();
    }

    @Test
    void customFieldTest() {
        Client client = getClientRandomSampleGenerator();
        CustomField customFieldBack = getCustomFieldRandomSampleGenerator();

        client.addCustomField(customFieldBack);
        assertThat(client.getCustomFields()).containsOnly(customFieldBack);
        assertThat(customFieldBack.getClient()).isEqualTo(client);

        client.removeCustomField(customFieldBack);
        assertThat(client.getCustomFields()).doesNotContain(customFieldBack);
        assertThat(customFieldBack.getClient()).isNull();

        client.customFields(new HashSet<>(Set.of(customFieldBack)));
        assertThat(client.getCustomFields()).containsOnly(customFieldBack);
        assertThat(customFieldBack.getClient()).isEqualTo(client);

        client.setCustomFields(new HashSet<>());
        assertThat(client.getCustomFields()).doesNotContain(customFieldBack);
        assertThat(customFieldBack.getClient()).isNull();
    }

    @Test
    void clientTagTest() {
        Client client = getClientRandomSampleGenerator();
        ClientTag clientTagBack = getClientTagRandomSampleGenerator();

        client.addClientTag(clientTagBack);
        assertThat(client.getClientTags()).containsOnly(clientTagBack);
        assertThat(clientTagBack.getClient()).isEqualTo(client);

        client.removeClientTag(clientTagBack);
        assertThat(client.getClientTags()).doesNotContain(clientTagBack);
        assertThat(clientTagBack.getClient()).isNull();

        client.clientTags(new HashSet<>(Set.of(clientTagBack)));
        assertThat(client.getClientTags()).containsOnly(clientTagBack);
        assertThat(clientTagBack.getClient()).isEqualTo(client);

        client.setClientTags(new HashSet<>());
        assertThat(client.getClientTags()).doesNotContain(clientTagBack);
        assertThat(clientTagBack.getClient()).isNull();
    }

    @Test
    void memberGroupTest() {
        Client client = getClientRandomSampleGenerator();
        MemberGroup memberGroupBack = getMemberGroupRandomSampleGenerator();

        client.addMemberGroup(memberGroupBack);
        assertThat(client.getMemberGroups()).containsOnly(memberGroupBack);
        assertThat(memberGroupBack.getClient()).isEqualTo(client);

        client.removeMemberGroup(memberGroupBack);
        assertThat(client.getMemberGroups()).doesNotContain(memberGroupBack);
        assertThat(memberGroupBack.getClient()).isNull();

        client.memberGroups(new HashSet<>(Set.of(memberGroupBack)));
        assertThat(client.getMemberGroups()).containsOnly(memberGroupBack);
        assertThat(memberGroupBack.getClient()).isEqualTo(client);

        client.setMemberGroups(new HashSet<>());
        assertThat(client.getMemberGroups()).doesNotContain(memberGroupBack);
        assertThat(memberGroupBack.getClient()).isNull();
    }
}
