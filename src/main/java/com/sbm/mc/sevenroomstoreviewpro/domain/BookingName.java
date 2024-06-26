package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A BookingName.
 */
@Document(collection = "booking_name")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BookingName implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @DBRef
    @Field("clientVenueStats")
    @JsonIgnoreProperties(value = { "bookingNames", "client" }, allowSetters = true)
    private ClientVenueStats clientVenueStats;

    public BookingName(String name) {
        this.setName(name);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public BookingName id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public BookingName name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientVenueStats getClientVenueStats() {
        return this.clientVenueStats;
    }

    public void setClientVenueStats(ClientVenueStats clientVenueStats) {
        this.clientVenueStats = clientVenueStats;
    }

    public BookingName clientVenueStats(ClientVenueStats clientVenueStats) {
        this.setClientVenueStats(clientVenueStats);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookingName)) {
            return false;
        }
        return getId() != null && getId().equals(((BookingName) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookingName{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
