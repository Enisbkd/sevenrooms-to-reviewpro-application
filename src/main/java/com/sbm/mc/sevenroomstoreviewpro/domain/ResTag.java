package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ResTag.
 */
@Document(collection = "res_tag")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tag")
    private String tag;

    @Field("tag_display")
    private String tagDisplay;

    @Field("group")
    private String group;

    @Field("group_display")
    private String groupDisplay;

    @Field("color")
    private String color;

    @Field("tag_search_query")
    private String tagSearchQuery;

    @DBRef
    @Field("reservation")
    @JsonIgnoreProperties(value = { "resTags", "resPosTickets", "resCustomFields", "resTables" }, allowSetters = true)
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ResTag id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public ResTag tag(String tag) {
        this.setTag(tag);
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagDisplay() {
        return this.tagDisplay;
    }

    public ResTag tagDisplay(String tagDisplay) {
        this.setTagDisplay(tagDisplay);
        return this;
    }

    public void setTagDisplay(String tagDisplay) {
        this.tagDisplay = tagDisplay;
    }

    public String getGroup() {
        return this.group;
    }

    public ResTag group(String group) {
        this.setGroup(group);
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDisplay() {
        return this.groupDisplay;
    }

    public ResTag groupDisplay(String groupDisplay) {
        this.setGroupDisplay(groupDisplay);
        return this;
    }

    public void setGroupDisplay(String groupDisplay) {
        this.groupDisplay = groupDisplay;
    }

    public String getColor() {
        return this.color;
    }

    public ResTag color(String color) {
        this.setColor(color);
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTagSearchQuery() {
        return this.tagSearchQuery;
    }

    public ResTag tagSearchQuery(String tagSearchQuery) {
        this.setTagSearchQuery(tagSearchQuery);
        return this;
    }

    public void setTagSearchQuery(String tagSearchQuery) {
        this.tagSearchQuery = tagSearchQuery;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ResTag reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResTag)) {
            return false;
        }
        return getId() != null && getId().equals(((ResTag) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResTag{" +
            "id=" + getId() +
            ", tag='" + getTag() + "'" +
            ", tagDisplay='" + getTagDisplay() + "'" +
            ", group='" + getGroup() + "'" +
            ", groupDisplay='" + getGroupDisplay() + "'" +
            ", color='" + getColor() + "'" +
            ", tagSearchQuery='" + getTagSearchQuery() + "'" +
            "}";
    }
}
