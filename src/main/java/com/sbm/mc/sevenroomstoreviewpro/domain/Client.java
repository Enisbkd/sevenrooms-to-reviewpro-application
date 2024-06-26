package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Client.
 */
@Document(collection = "client")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("client_id")
    private String clientId;

    @Field("created_date")
    private String createdDate;

    @Field("updated_date")
    private String updatedDate;

    @Field("deleted_date")
    private String deletedDate;

    @Field("lastname")
    private String lastname;

    @Field("firstname")
    private String firstname;

    @Field("gender")
    private String gender;

    @Field("salutation")
    private String salutation;

    @Field("title")
    private String title;

    @Field("birthday_day")
    private Integer birthdayDay;

    @Field("birthday_month")
    private Integer birthdayMonth;

    @Field("birthday_alt_month")
    private Integer birthdayAltMonth;

    @Field("anniversary_day")
    private Integer anniversaryDay;

    @Field("anniversary_month")
    private Integer anniversaryMonth;

    @Field("company")
    private String company;

    @Field("email")
    private String email;

    @Field("email_alt")
    private String emailAlt;

    @Field("phone_number")
    private String phoneNumber;

    @Field("phone_numberlocale")
    private String phoneNumberlocale;

    @Field("phone_numberalt")
    private String phoneNumberalt;

    @Field("phone_numberaltlocale")
    private String phoneNumberaltlocale;

    @Field("address")
    private String address;

    @Field("address_2")
    private String address2;

    @Field("city")
    private String city;

    @Field("postal_code")
    private String postalCode;

    @Field("state")
    private String state;

    @Field("country")
    private String country;

    @Field("is_contact_private")
    private Boolean isContactPrivate;

    @Field("is_onetime_guest")
    private Boolean isOnetimeGuest;

    @Field("status")
    private String status;

    @Field("loyalty_id")
    private String loyaltyId;

    @Field("loyalty_rank")
    private Integer loyaltyRank;

    @Field("loyalty_tier")
    private String loyaltyTier;

    @Field("marketing_optin")
    private Boolean marketingOptin;

    @Field("marketing_optints")
    private String marketingOptints;

    @Field("marketing_opt_outts")
    private String marketingOptOutts;

    @Field("has_billing_profile")
    private Boolean hasBillingProfile;

    @Field("notes")
    private String notes;

    @Field("private_notes")
    private String privateNotes;

    @Field("tags")
    private String tags;

    @Field("total_visits")
    private Double totalVisits;

    @Field("total_covers")
    private Double totalCovers;

    @Field("total_cancellations")
    private Double totalCancellations;

    @Field("total_no_shows")
    private Double totalNoShows;

    @Field("total_spend")
    private Double totalSpend;

    @Field("total_spend_per_cover")
    private Double totalSpendPerCover;

    @Field("totalspend_per_visit")
    private Double totalspendPerVisit;

    @Field("avg_rating")
    private Double avgRating;

    @Field("reference_code")
    private String referenceCode;

    @Field("external_user_id")
    private String externalUserId;

    @Field("venue_group_id")
    private String venueGroupId;

    @Field("birthday_alt_day")
    private Integer birthdayAltDay;

    @Field("user_id")
    private String userId;

    @Field("user_name")
    private String userName;

    @Field("total_order_count")
    private Integer totalOrderCount;

    @Field("preferred_language_code")
    private String preferredLanguageCode;

    @DBRef
    @Field("clientPhoto")
    private ClientPhoto clientPhoto;

    @DBRef
    @Field("clientVenueStats")
    private ClientVenueStats clientVenueStats;

    @DBRef
    @Field("customField")
    @JsonIgnoreProperties(value = { "client" }, allowSetters = true)
    private Set<CustomField> customFields = new HashSet<>();

    @DBRef
    @Field("clientTag")
    @JsonIgnoreProperties(value = { "client" }, allowSetters = true)
    private Set<ClientTag> clientTags = new HashSet<>();

    @DBRef
    @Field("memberGroup")
    @JsonIgnoreProperties(value = { "client" }, allowSetters = true)
    private Set<MemberGroup> memberGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Client id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return this.clientId;
    }

    public Client clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public Client createdDate(String createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return this.updatedDate;
    }

    public Client updatedDate(String updatedDate) {
        this.setUpdatedDate(updatedDate);
        return this;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDeletedDate() {
        return this.deletedDate;
    }

    public Client deletedDate(String deletedDate) {
        this.setDeletedDate(deletedDate);
        return this;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Client lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Client firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return this.gender;
    }

    public Client gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalutation() {
        return this.salutation;
    }

    public Client salutation(String salutation) {
        this.setSalutation(salutation);
        return this;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getTitle() {
        return this.title;
    }

    public Client title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBirthdayDay() {
        return this.birthdayDay;
    }

    public Client birthdayDay(Integer birthdayDay) {
        this.setBirthdayDay(birthdayDay);
        return this;
    }

    public void setBirthdayDay(Integer birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public Integer getBirthdayMonth() {
        return this.birthdayMonth;
    }

    public Client birthdayMonth(Integer birthdayMonth) {
        this.setBirthdayMonth(birthdayMonth);
        return this;
    }

    public void setBirthdayMonth(Integer birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public Integer getBirthdayAltMonth() {
        return this.birthdayAltMonth;
    }

    public Client birthdayAltMonth(Integer birthdayAltMonth) {
        this.setBirthdayAltMonth(birthdayAltMonth);
        return this;
    }

    public void setBirthdayAltMonth(Integer birthdayAltMonth) {
        this.birthdayAltMonth = birthdayAltMonth;
    }

    public Integer getAnniversaryDay() {
        return this.anniversaryDay;
    }

    public Client anniversaryDay(Integer anniversaryDay) {
        this.setAnniversaryDay(anniversaryDay);
        return this;
    }

    public void setAnniversaryDay(Integer anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
    }

    public Integer getAnniversaryMonth() {
        return this.anniversaryMonth;
    }

    public Client anniversaryMonth(Integer anniversaryMonth) {
        this.setAnniversaryMonth(anniversaryMonth);
        return this;
    }

    public void setAnniversaryMonth(Integer anniversaryMonth) {
        this.anniversaryMonth = anniversaryMonth;
    }

    public String getCompany() {
        return this.company;
    }

    public Client company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return this.email;
    }

    public Client email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlt() {
        return this.emailAlt;
    }

    public Client emailAlt(String emailAlt) {
        this.setEmailAlt(emailAlt);
        return this;
    }

    public void setEmailAlt(String emailAlt) {
        this.emailAlt = emailAlt;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Client phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberlocale() {
        return this.phoneNumberlocale;
    }

    public Client phoneNumberlocale(String phoneNumberlocale) {
        this.setPhoneNumberlocale(phoneNumberlocale);
        return this;
    }

    public void setPhoneNumberlocale(String phoneNumberlocale) {
        this.phoneNumberlocale = phoneNumberlocale;
    }

    public String getPhoneNumberalt() {
        return this.phoneNumberalt;
    }

    public Client phoneNumberalt(String phoneNumberalt) {
        this.setPhoneNumberalt(phoneNumberalt);
        return this;
    }

    public void setPhoneNumberalt(String phoneNumberalt) {
        this.phoneNumberalt = phoneNumberalt;
    }

    public String getPhoneNumberaltlocale() {
        return this.phoneNumberaltlocale;
    }

    public Client phoneNumberaltlocale(String phoneNumberaltlocale) {
        this.setPhoneNumberaltlocale(phoneNumberaltlocale);
        return this;
    }

    public void setPhoneNumberaltlocale(String phoneNumberaltlocale) {
        this.phoneNumberaltlocale = phoneNumberaltlocale;
    }

    public String getAddress() {
        return this.address;
    }

    public Client address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return this.address2;
    }

    public Client address2(String address2) {
        this.setAddress2(address2);
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public Client city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Client postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return this.state;
    }

    public Client state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public Client country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsContactPrivate() {
        return this.isContactPrivate;
    }

    public Client isContactPrivate(Boolean isContactPrivate) {
        this.setIsContactPrivate(isContactPrivate);
        return this;
    }

    public void setIsContactPrivate(Boolean isContactPrivate) {
        this.isContactPrivate = isContactPrivate;
    }

    public Boolean getIsOnetimeGuest() {
        return this.isOnetimeGuest;
    }

    public Client isOnetimeGuest(Boolean isOnetimeGuest) {
        this.setIsOnetimeGuest(isOnetimeGuest);
        return this;
    }

    public void setIsOnetimeGuest(Boolean isOnetimeGuest) {
        this.isOnetimeGuest = isOnetimeGuest;
    }

    public String getStatus() {
        return this.status;
    }

    public Client status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoyaltyId() {
        return this.loyaltyId;
    }

    public Client loyaltyId(String loyaltyId) {
        this.setLoyaltyId(loyaltyId);
        return this;
    }

    public void setLoyaltyId(String loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public Integer getLoyaltyRank() {
        return this.loyaltyRank;
    }

    public Client loyaltyRank(Integer loyaltyRank) {
        this.setLoyaltyRank(loyaltyRank);
        return this;
    }

    public void setLoyaltyRank(Integer loyaltyRank) {
        this.loyaltyRank = loyaltyRank;
    }

    public String getLoyaltyTier() {
        return this.loyaltyTier;
    }

    public Client loyaltyTier(String loyaltyTier) {
        this.setLoyaltyTier(loyaltyTier);
        return this;
    }

    public void setLoyaltyTier(String loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }

    public Boolean getMarketingOptin() {
        return this.marketingOptin;
    }

    public Client marketingOptin(Boolean marketingOptin) {
        this.setMarketingOptin(marketingOptin);
        return this;
    }

    public void setMarketingOptin(Boolean marketingOptin) {
        this.marketingOptin = marketingOptin;
    }

    public String getMarketingOptints() {
        return this.marketingOptints;
    }

    public Client marketingOptints(String marketingOptints) {
        this.setMarketingOptints(marketingOptints);
        return this;
    }

    public void setMarketingOptints(String marketingOptints) {
        this.marketingOptints = marketingOptints;
    }

    public String getMarketingOptOutts() {
        return this.marketingOptOutts;
    }

    public Client marketingOptOutts(String marketingOptOutts) {
        this.setMarketingOptOutts(marketingOptOutts);
        return this;
    }

    public void setMarketingOptOutts(String marketingOptOutts) {
        this.marketingOptOutts = marketingOptOutts;
    }

    public Boolean getHasBillingProfile() {
        return this.hasBillingProfile;
    }

    public Client hasBillingProfile(Boolean hasBillingProfile) {
        this.setHasBillingProfile(hasBillingProfile);
        return this;
    }

    public void setHasBillingProfile(Boolean hasBillingProfile) {
        this.hasBillingProfile = hasBillingProfile;
    }

    public String getNotes() {
        return this.notes;
    }

    public Client notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrivateNotes() {
        return this.privateNotes;
    }

    public Client privateNotes(String privateNotes) {
        this.setPrivateNotes(privateNotes);
        return this;
    }

    public void setPrivateNotes(String privateNotes) {
        this.privateNotes = privateNotes;
    }

    public String getTags() {
        return this.tags;
    }

    public Client tags(String tags) {
        this.setTags(tags);
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getTotalVisits() {
        return this.totalVisits;
    }

    public Client totalVisits(Double totalVisits) {
        this.setTotalVisits(totalVisits);
        return this;
    }

    public void setTotalVisits(Double totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Double getTotalCovers() {
        return this.totalCovers;
    }

    public Client totalCovers(Double totalCovers) {
        this.setTotalCovers(totalCovers);
        return this;
    }

    public void setTotalCovers(Double totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Double getTotalCancellations() {
        return this.totalCancellations;
    }

    public Client totalCancellations(Double totalCancellations) {
        this.setTotalCancellations(totalCancellations);
        return this;
    }

    public void setTotalCancellations(Double totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Double getTotalNoShows() {
        return this.totalNoShows;
    }

    public Client totalNoShows(Double totalNoShows) {
        this.setTotalNoShows(totalNoShows);
        return this;
    }

    public void setTotalNoShows(Double totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Double getTotalSpend() {
        return this.totalSpend;
    }

    public Client totalSpend(Double totalSpend) {
        this.setTotalSpend(totalSpend);
        return this;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getTotalSpendPerCover() {
        return this.totalSpendPerCover;
    }

    public Client totalSpendPerCover(Double totalSpendPerCover) {
        this.setTotalSpendPerCover(totalSpendPerCover);
        return this;
    }

    public void setTotalSpendPerCover(Double totalSpendPerCover) {
        this.totalSpendPerCover = totalSpendPerCover;
    }

    public Double getTotalspendPerVisit() {
        return this.totalspendPerVisit;
    }

    public Client totalspendPerVisit(Double totalspendPerVisit) {
        this.setTotalspendPerVisit(totalspendPerVisit);
        return this;
    }

    public void setTotalspendPerVisit(Double totalspendPerVisit) {
        this.totalspendPerVisit = totalspendPerVisit;
    }

    public Double getAvgRating() {
        return this.avgRating;
    }

    public Client avgRating(Double avgRating) {
        this.setAvgRating(avgRating);
        return this;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getReferenceCode() {
        return this.referenceCode;
    }

    public Client referenceCode(String referenceCode) {
        this.setReferenceCode(referenceCode);
        return this;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getExternalUserId() {
        return this.externalUserId;
    }

    public Client externalUserId(String externalUserId) {
        this.setExternalUserId(externalUserId);
        return this;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getVenueGroupId() {
        return this.venueGroupId;
    }

    public Client venueGroupId(String venueGroupId) {
        this.setVenueGroupId(venueGroupId);
        return this;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public Integer getBirthdayAltDay() {
        return this.birthdayAltDay;
    }

    public Client birthdayAltDay(Integer birthdayAltDay) {
        this.setBirthdayAltDay(birthdayAltDay);
        return this;
    }

    public void setBirthdayAltDay(Integer birthdayAltDay) {
        this.birthdayAltDay = birthdayAltDay;
    }

    public String getUserId() {
        return this.userId;
    }

    public Client userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public Client userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalOrderCount() {
        return this.totalOrderCount;
    }

    public Client totalOrderCount(Integer totalOrderCount) {
        this.setTotalOrderCount(totalOrderCount);
        return this;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public String getPreferredLanguageCode() {
        return this.preferredLanguageCode;
    }

    public Client preferredLanguageCode(String preferredLanguageCode) {
        this.setPreferredLanguageCode(preferredLanguageCode);
        return this;
    }

    public void setPreferredLanguageCode(String preferredLanguageCode) {
        this.preferredLanguageCode = preferredLanguageCode;
    }

    public ClientPhoto getClientPhoto() {
        return this.clientPhoto;
    }

    public void setClientPhoto(ClientPhoto clientPhoto) {
        this.clientPhoto = clientPhoto;
    }

    public Client clientPhoto(ClientPhoto clientPhoto) {
        this.setClientPhoto(clientPhoto);
        return this;
    }

    public ClientVenueStats getClientVenueStats() {
        return this.clientVenueStats;
    }

    public void setClientVenueStats(ClientVenueStats clientVenueStats) {
        this.clientVenueStats = clientVenueStats;
    }

    public Client clientVenueStats(ClientVenueStats clientVenueStats) {
        this.setClientVenueStats(clientVenueStats);
        return this;
    }

    public Set<CustomField> getCustomFields() {
        return this.customFields;
    }

    public void setCustomFields(Set<CustomField> customFields) {
        if (this.customFields != null) {
            this.customFields.forEach(i -> i.setClient(null));
        }
        if (customFields != null) {
            customFields.forEach(i -> i.setClient(this));
        }
        this.customFields = customFields;
    }

    public Client customFields(Set<CustomField> customFields) {
        this.setCustomFields(customFields);
        return this;
    }

    public Client addCustomField(CustomField customField) {
        this.customFields.add(customField);
        customField.setClient(this);
        return this;
    }

    public Client removeCustomField(CustomField customField) {
        this.customFields.remove(customField);
        customField.setClient(null);
        return this;
    }

    public Set<ClientTag> getClientTags() {
        return this.clientTags;
    }

    public void setClientTags(Set<ClientTag> clientTags) {
        if (this.clientTags != null) {
            this.clientTags.forEach(i -> i.setClient(null));
        }
        if (clientTags != null) {
            clientTags.forEach(i -> i.setClient(this));
        }
        this.clientTags = clientTags;
    }

    public Client clientTags(Set<ClientTag> clientTags) {
        this.setClientTags(clientTags);
        return this;
    }

    public Client addClientTag(ClientTag clientTag) {
        this.clientTags.add(clientTag);
        clientTag.setClient(this);
        return this;
    }

    public Client removeClientTag(ClientTag clientTag) {
        this.clientTags.remove(clientTag);
        clientTag.setClient(null);
        return this;
    }

    public Set<MemberGroup> getMemberGroups() {
        return this.memberGroups;
    }

    public void setMemberGroups(Set<MemberGroup> memberGroups) {
        if (this.memberGroups != null) {
            this.memberGroups.forEach(i -> i.setClient(null));
        }
        if (memberGroups != null) {
            memberGroups.forEach(i -> i.setClient(this));
        }
        this.memberGroups = memberGroups;
    }

    public Client memberGroups(Set<MemberGroup> memberGroups) {
        this.setMemberGroups(memberGroups);
        return this;
    }

    public Client addMemberGroup(MemberGroup memberGroup) {
        this.memberGroups.add(memberGroup);
        memberGroup.setClient(this);
        return this;
    }

    public Client removeMemberGroup(MemberGroup memberGroup) {
        this.memberGroups.remove(memberGroup);
        memberGroup.setClient(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return getId() != null && getId().equals(((Client) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientId='" + getClientId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deletedDate='" + getDeletedDate() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", gender='" + getGender() + "'" +
            ", salutation='" + getSalutation() + "'" +
            ", title='" + getTitle() + "'" +
            ", birthdayDay=" + getBirthdayDay() +
            ", birthdayMonth=" + getBirthdayMonth() +
            ", birthdayAltMonth=" + getBirthdayAltMonth() +
            ", anniversaryDay=" + getAnniversaryDay() +
            ", anniversaryMonth=" + getAnniversaryMonth() +
            ", company='" + getCompany() + "'" +
            ", email='" + getEmail() + "'" +
            ", emailAlt='" + getEmailAlt() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", phoneNumberlocale='" + getPhoneNumberlocale() + "'" +
            ", phoneNumberalt='" + getPhoneNumberalt() + "'" +
            ", phoneNumberaltlocale='" + getPhoneNumberaltlocale() + "'" +
            ", address='" + getAddress() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", isContactPrivate='" + getIsContactPrivate() + "'" +
            ", isOnetimeGuest='" + getIsOnetimeGuest() + "'" +
            ", status='" + getStatus() + "'" +
            ", loyaltyId='" + getLoyaltyId() + "'" +
            ", loyaltyRank=" + getLoyaltyRank() +
            ", loyaltyTier='" + getLoyaltyTier() + "'" +
            ", marketingOptin='" + getMarketingOptin() + "'" +
            ", marketingOptints='" + getMarketingOptints() + "'" +
            ", marketingOptOutts='" + getMarketingOptOutts() + "'" +
            ", hasBillingProfile='" + getHasBillingProfile() + "'" +
            ", notes='" + getNotes() + "'" +
            ", privateNotes='" + getPrivateNotes() + "'" +
            ", tags='" + getTags() + "'" +
            ", totalVisits=" + getTotalVisits() +
            ", totalCovers=" + getTotalCovers() +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalNoShows=" + getTotalNoShows() +
            ", totalSpend=" + getTotalSpend() +
            ", totalSpendPerCover=" + getTotalSpendPerCover() +
            ", totalspendPerVisit=" + getTotalspendPerVisit() +
            ", avgRating=" + getAvgRating() +
            ", referenceCode='" + getReferenceCode() + "'" +
            ", externalUserId='" + getExternalUserId() + "'" +
            ", venueGroupId='" + getVenueGroupId() + "'" +
            ", birthdayAltDay=" + getBirthdayAltDay() +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", totalOrderCount=" + getTotalOrderCount() +
            ", preferredLanguageCode='" + getPreferredLanguageCode() + "'" +
            "}";
    }
}
