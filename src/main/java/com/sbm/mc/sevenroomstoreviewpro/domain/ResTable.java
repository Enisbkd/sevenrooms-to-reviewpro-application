package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ResTable.
 */
@Document(collection = "res_table")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("table_number")
    private String tableNumber;

    @DBRef
    @Field("reservation")
    @JsonIgnoreProperties(value = { "resTags", "resPosTickets", "resCustomFields", "resTables" }, allowSetters = true)
    private Reservation reservation;

    public ResTable(String tableNumber, Reservation reservation) {
        this.tableNumber = tableNumber;
        this.reservation = reservation;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ResTable id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableNumber() {
        return this.tableNumber;
    }

    public ResTable tableNumber(String tableNumber) {
        this.setTableNumber(tableNumber);
        return this;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ResTable reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResTable)) {
            return false;
        }
        return getId() != null && getId().equals(((ResTable) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResTable{" +
            "id=" + getId() +
            ", tableNumber='" + getTableNumber() + "'" +
            "}";
    }
}
