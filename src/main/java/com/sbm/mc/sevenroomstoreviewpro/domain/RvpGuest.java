package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RvpGuest.
 */
@Document(collection = "rvp_guest")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RvpGuest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("pms_id")
    private String pmsId;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("language")
    private String language;

    @Field("checkin")
    private LocalDate checkin;

    @Field("checkout")
    private LocalDate checkout;

    @Field("email")
    private String email;

    @Field("email_alt")
    private String emailAlt;

    @Field("salutation")
    private String salutation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RvpGuest id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPmsId() {
        return this.pmsId;
    }

    public RvpGuest pmsId(String pmsId) {
        this.setPmsId(pmsId);
        return this;
    }

    public void setPmsId(String pmsId) {
        this.pmsId = pmsId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public RvpGuest firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public RvpGuest lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return this.language;
    }

    public RvpGuest language(String language) {
        this.setLanguage(language);
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getCheckin() {
        return this.checkin;
    }

    public RvpGuest checkin(LocalDate checkin) {
        this.setCheckin(checkin);
        return this;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return this.checkout;
    }

    public RvpGuest checkout(LocalDate checkout) {
        this.setCheckout(checkout);
        return this;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public String getEmail() {
        return this.email;
    }

    public RvpGuest email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlt() {
        return this.emailAlt;
    }

    public RvpGuest emailAlt(String emailAlt) {
        this.setEmailAlt(emailAlt);
        return this;
    }

    public void setEmailAlt(String emailAlt) {
        this.emailAlt = emailAlt;
    }

    public String getSalutation() {
        return this.salutation;
    }

    public RvpGuest salutation(String salutation) {
        this.setSalutation(salutation);
        return this;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RvpGuest)) {
            return false;
        }
        return getId() != null && getId().equals(((RvpGuest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RvpGuest{" +
            "id=" + getId() +
            ", pmsId='" + getPmsId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", language='" + getLanguage() + "'" +
            ", checkin='" + getCheckin() + "'" +
            ", checkout='" + getCheckout() + "'" +
            ", email='" + getEmail() + "'" +
            ", emailAlt='" + getEmailAlt() + "'" +
            ", salutation='" + getSalutation() + "'" +
            "}";
    }
}
