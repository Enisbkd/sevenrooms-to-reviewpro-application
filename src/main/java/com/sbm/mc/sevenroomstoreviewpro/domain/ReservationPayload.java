package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Set;

public class ReservationPayload implements Serializable {

    @JsonProperty("entity")
    private Reservation reservation;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonIgnore
    private Set<UpdateField> updates;

    @JsonProperty("techSource")
    private String techSource;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public Set<UpdateField> getUpdates() {
        return updates;
    }

    public void setUpdates(Set<UpdateField> updates) {
        this.updates = updates;
    }

    public String getTechSource() {
        return techSource;
    }

    public void setTechSource(String techSource) {
        this.techSource = techSource;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ReservationPayload other = (ReservationPayload) obj;
        if (reservation == null) {
            if (other.reservation != null) return false;
        } else if (!reservation.equals(other.reservation)) return false;
        if (event_type == null) {
            if (other.event_type != null) return false;
        } else if (!event_type.equals(other.event_type)) return false;
        if (entity_type == null) {
            if (other.entity_type != null) return false;
        } else if (!entity_type.equals(other.entity_type)) return false;
        if (updates == null) {
            if (other.updates != null) return false;
        } else if (!updates.equals(other.updates)) return false;
        if (techSource == null) {
            if (other.techSource != null) return false;
        } else if (!techSource.equals(other.techSource)) return false;
        return true;
    }

    public ReservationPayload(Reservation reservation, String event_type, String entity_type, Set<UpdateField> updates, String techSource) {
        this.reservation = reservation;
        this.event_type = event_type;
        this.entity_type = entity_type;
        this.updates = updates;
        this.techSource = techSource;
    }

    @Override
    public String toString() {
        return (
            "ReservationPayload [reservation=" +
            reservation +
            ", event_type=" +
            event_type +
            ", entity_type=" +
            entity_type +
            ", updates=" +
            updates +
            ", techSource=" +
            techSource +
            "]"
        );
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
        result = prime * result + ((event_type == null) ? 0 : event_type.hashCode());
        result = prime * result + ((entity_type == null) ? 0 : entity_type.hashCode());
        result = prime * result + ((updates == null) ? 0 : updates.hashCode());
        result = prime * result + ((techSource == null) ? 0 : techSource.hashCode());
        return result;
    }

    public ReservationPayload() {}
}
