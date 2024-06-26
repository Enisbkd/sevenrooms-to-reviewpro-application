package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbm.mc.sevenroomstoreviewpro.utils.TimestampUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Reservation.
 */
@Document(collection = "reservation")
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonIgnore
    private Long id;

    @Field("resv_id_name")
    @JsonProperty("id")
    private String resvId;

    @Field("created")
    @JsonProperty("created")
    private ZonedDateTime created;

    @Field("updated")
    @JsonProperty("updated")
    private ZonedDateTime updated;

    @Field("deleted")
    @JsonProperty("deleted")
    private ZonedDateTime deleted;

    @JsonProperty("client_id")
    @Field("client_id")
    private String clientId;

    @Field("venue_group_client_id")
    @JsonProperty("venue_group_client_id")
    private String venueGroupClientId;

    @Field("venue_group_id")
    @JsonProperty("venue_group_id")
    private String venueGroupId;

    @Field("venue_id")
    @JsonProperty("venue_id")
    private String venueId;

    @JsonProperty("real_datetime_of_slot")
    private LocalDateTime realDateTimeOfSlot;

    @Field("resv_date")
    private LocalDate date;

    @Field("duration")
    private Integer duration;

    @Field("check_numbers")
    @JsonProperty("check_numbers")
    private String checkNumbers;

    @Field("shift_category")
    @JsonProperty("shift_category")
    private String shiftCategory;

    @Field("shift_persistent_id")
    @JsonProperty("shift_persistent_id")
    private String shiftPersistentId;

    @Field("max_guests")
    @JsonProperty("max_guests")
    private Integer maxGuests;

    @Field("mfratio_male")
    @JsonProperty("mf_ratio_male")
    private Integer mfratioMale;

    @Field("mfratio_female")
    @JsonProperty("mf_ratio_female")
    private Integer mfratioFemale;

    @Field("status")
    private String status;

    @Field("status_display")
    @JsonProperty("status_display")
    private String statusDisplay;

    @Field("status_simple")
    @JsonProperty("status_simple")
    private String statusSimple;

    @Field("access_persistent_id")
    @JsonProperty("access_persistent_id")
    private String accessPersistentId;

    @Field("arrived_guests")
    @JsonProperty("arrived_guests")
    private Integer arrivedGuests;

    @Field("isvip")
    @JsonProperty("is_vip")
    private Boolean isvip;

    @Field("bookedby")
    @JsonProperty("booked_by")
    private String bookedby;

    @Field("client_reference_code")
    @JsonProperty("client_reference_code")
    private String clientReferenceCode;

    @Field("lastname")
    @JsonProperty("last_name")
    private String lastname;

    @Field("firstname")
    @JsonProperty("first_name")
    private String firstname;

    @Field("email")
    private String email;

    @Field("phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Field("address")
    private String address;

    @Field("address_2")
    @JsonProperty("address_2")
    private String address2;

    @Field("city")
    private String city;

    @Field("postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @Field("state")
    private String state;

    @Field("country")
    private String country;

    @Field("loyalty_id")
    @JsonProperty("loyalty_id")
    private String loyaltyId;

    @Field("loyalty_rank")
    @JsonProperty("loyalty_rank")
    private Integer loyaltyRank;

    @Field("loyalty_tier")
    @JsonProperty("loyalty_tier")
    private String loyaltyTier;

    @Field("notes")
    private String notes;

    @Field("arrival_time")
    @JsonProperty("arrival_time")
    private String arrivalTime;

    @Field("seated_time")
    @JsonProperty("seated_time")
    private String seatedTime;

    @Field("left_time")
    @JsonProperty("left_time")
    private String leftTime;

    @Field("client_requests")
    @JsonProperty("client_requests")
    private String clientRequests;

    @Field("comps")
    private Integer comps;

    @Field("comps_price_type")
    @JsonProperty("comps_price_type")
    private String compsPriceType;

    @Field("cost_option")
    @JsonProperty("cost_option")
    private Integer costOption;

    @Field("policy")
    private String policy;

    @Field("min_price")
    @JsonProperty("min_price")
    private Integer minPrice;

    @Field("pre_payment")
    @JsonProperty("prepayment")
    private Double prePayment;

    @Field("onsite_payment")
    @JsonProperty("onsite_payment")
    private Double onsitePayment;

    @Field("total_payment")
    @JsonProperty("total_payment")
    private Integer totalPayment;

    @Field("paid_by")
    @JsonProperty("paid_by")
    private String paidBy;

    @Field("served_by")
    @JsonProperty("served_by")
    private String servedBy;

    @Field("rating")
    private Integer rating;

    @Field("problems")
    private String problems;

    @Field("auto_assignments")
    @JsonProperty("auto_assignments")
    private String autoAssignments;

    @Field("external_client_id")
    @JsonProperty("external_client_id")
    private String externalClientId;

    @Field("external_id")
    @JsonProperty("external_id")
    private String externalId;

    @Field("external_reference_code")
    @JsonProperty("external_reference_code")
    private String externalReferenceCode;

    @Field("external_user_id")
    @JsonProperty("external_user_id")
    private String externalUserId;

    @Field("modify_reservation_link")
    @JsonProperty("modify_reservation_link")
    private String modifyReservationLink;

    @Field("reference_code")
    @JsonProperty("reference_code")
    private String referenceCode;

    @Field("reservation_sms_optin")
    @JsonProperty("reservation_sms_opt_in")
    private Boolean reservationSmsOptin;

    @Field("reservation_type")
    @JsonProperty("reservation_type")
    private String reservationType;

    @Field("send_reminder_email")
    @JsonProperty("send_reminder_email")
    private Boolean sendReminderEmail;

    @Field("sendreminder_sms")
    @JsonProperty("send_reminder_sms")
    private Boolean sendreminderSms;

    @Field("source_client_id")
    @JsonProperty("source_client_id")
    private String sourceClientId;

    @Field("user_id")
    private String userId;

    @Field("user_name")
    private String userName;

    @JsonProperty("tags")
    private Set<ResTag> resTags = new HashSet<>();

    @JsonProperty("pos_tickets")
    private Set<ResPosTicket> resPosTickets = new HashSet<>();

    @JsonProperty("custom_fields")
    private Set<ResCustomField> resCustomFields = new HashSet<>();

    private Set<ResTable> resTables = new HashSet<>();

    public LocalDateTime getRealDateTimeOfSlot() {
        return realDateTimeOfSlot;
    }

    public void setRealDateTimeOfSlot(String realDateTimeOfSlot) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(realDateTimeOfSlot, dateTimeFormatter);
        this.realDateTimeOfSlot = localDateTime;
    }

    public void setRealDateTimeOfSlot(LocalDateTime realDateTimeOfSlot) {
        this.realDateTimeOfSlot = realDateTimeOfSlot;
    }

    public Long getId() {
        return this.id;
    }

    public Reservation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResvId() {
        return this.resvId;
    }

    public Reservation resvId(String resvId) {
        this.setResvId(resvId);
        return this;
    }

    public void setResvId(String resvId) {
        this.resvId = resvId;
    }

    public ZonedDateTime getCreated() {
        return this.created;
    }

    public Reservation created(ZonedDateTime created) {
        this.setCreated(created);
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public void setCreated(String created) {
        this.created = TimestampUtils.convertToZonedDateTime(created);
    }

    public ZonedDateTime getUpdated() {
        return this.updated;
    }

    public Reservation updated(ZonedDateTime updated) {
        this.setUpdated(updated);
        return this;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public void setUpdated(String updated) {
        this.updated = TimestampUtils.convertToZonedDateTime(updated);
    }

    public ZonedDateTime getDeleted() {
        return this.deleted;
    }

    public Reservation deleted(ZonedDateTime deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(ZonedDateTime deleted) {
        this.deleted = deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = TimestampUtils.convertToZonedDateTime(deleted);
    }

    public String getVenueGroupClientId() {
        return this.venueGroupClientId;
    }

    public Reservation venueGroupClientId(String venueGroupClientId) {
        this.setVenueGroupClientId(venueGroupClientId);
        return this;
    }

    public void setVenueGroupClientId(String venueGroupClientId) {
        this.venueGroupClientId = venueGroupClientId;
    }

    public String getVenueGroupId() {
        return this.venueGroupId;
    }

    public Reservation venueGroupId(String venueGroupId) {
        this.setVenueGroupId(venueGroupId);
        return this;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public Reservation venueId(String venueId) {
        this.setVenueId(venueId);
        return this;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Reservation date(String date) {
        this.setDate(date);
        return this;
    }

    public void setDate(String date) {
        this.date = TimestampUtils.convertToLocalDate(date);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public Reservation duration(Integer duration) {
        this.setDuration(duration);
        return this;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCheckNumbers() {
        return this.checkNumbers;
    }

    public Reservation checkNumbers(String checkNumbers) {
        this.setCheckNumbers(checkNumbers);
        return this;
    }

    public void setCheckNumbers(String checkNumbers) {
        this.checkNumbers = checkNumbers;
    }

    public String getShiftCategory() {
        return this.shiftCategory;
    }

    public Reservation shiftCategory(String shiftCategory) {
        this.setShiftCategory(shiftCategory);
        return this;
    }

    public void setShiftCategory(String shiftCategory) {
        this.shiftCategory = shiftCategory;
    }

    public String getShiftPersistentId() {
        return this.shiftPersistentId;
    }

    public Reservation shiftPersistentId(String shiftPersistentId) {
        this.setShiftPersistentId(shiftPersistentId);
        return this;
    }

    public void setShiftPersistentId(String shiftPersistentId) {
        this.shiftPersistentId = shiftPersistentId;
    }

    public Integer getMaxGuests() {
        return this.maxGuests;
    }

    public Reservation maxGuests(Integer maxGuests) {
        this.setMaxGuests(maxGuests);
        return this;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public Integer getMfratioMale() {
        return this.mfratioMale;
    }

    public Reservation mfratioMale(Integer mfratioMale) {
        this.setMfratioMale(mfratioMale);
        return this;
    }

    public void setMfratioMale(Integer mfratioMale) {
        this.mfratioMale = mfratioMale;
    }

    public Integer getMfratioFemale() {
        return this.mfratioFemale;
    }

    public Reservation mfratioFemale(Integer mfratioFemale) {
        this.setMfratioFemale(mfratioFemale);
        return this;
    }

    public void setMfratioFemale(Integer mfratioFemale) {
        this.mfratioFemale = mfratioFemale;
    }

    public String getStatus() {
        return this.status;
    }

    public Reservation status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return this.statusDisplay;
    }

    public Reservation statusDisplay(String statusDisplay) {
        this.setStatusDisplay(statusDisplay);
        return this;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getStatusSimple() {
        return this.statusSimple;
    }

    public Reservation statusSimple(String statusSimple) {
        this.setStatusSimple(statusSimple);
        return this;
    }

    public void setStatusSimple(String statusSimple) {
        this.statusSimple = statusSimple;
    }

    public String getAccessPersistentId() {
        return this.accessPersistentId;
    }

    public Reservation accessPersistentId(String accessPersistentId) {
        this.setAccessPersistentId(accessPersistentId);
        return this;
    }

    public void setAccessPersistentId(String accessPersistentId) {
        this.accessPersistentId = accessPersistentId;
    }

    public Integer getArrivedGuests() {
        return this.arrivedGuests;
    }

    public Reservation arrivedGuests(Integer arrivedGuests) {
        this.setArrivedGuests(arrivedGuests);
        return this;
    }

    public void setArrivedGuests(Integer arrivedGuests) {
        this.arrivedGuests = arrivedGuests;
    }

    public Boolean getIsvip() {
        return this.isvip;
    }

    public Reservation isvip(Boolean isvip) {
        this.setIsvip(isvip);
        return this;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public String getBookedby() {
        return this.bookedby;
    }

    public Reservation bookedby(String bookedby) {
        this.setBookedby(bookedby);
        return this;
    }

    public void setBookedby(String bookedby) {
        this.bookedby = bookedby;
    }

    public String getClientReferenceCode() {
        return this.clientReferenceCode;
    }

    public Reservation clientReferenceCode(String clientReferenceCode) {
        this.setClientReferenceCode(clientReferenceCode);
        return this;
    }

    public void setClientReferenceCode(String clientReferenceCode) {
        this.clientReferenceCode = clientReferenceCode;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Reservation lastname(String lastname) {
        this.setLastname(lastname);
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Reservation firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return this.email;
    }

    public Reservation email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Reservation phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public Reservation address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return this.address2;
    }

    public Reservation address2(String address2) {
        this.setAddress2(address2);
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return this.city;
    }

    public Reservation city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Reservation postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return this.state;
    }

    public Reservation state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public Reservation country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLoyaltyId() {
        return this.loyaltyId;
    }

    public Reservation loyaltyId(String loyaltyId) {
        this.setLoyaltyId(loyaltyId);
        return this;
    }

    public void setLoyaltyId(String loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public Integer getLoyaltyRank() {
        return this.loyaltyRank;
    }

    public Reservation loyaltyRank(Integer loyaltyRank) {
        this.setLoyaltyRank(loyaltyRank);
        return this;
    }

    public void setLoyaltyRank(Integer loyaltyRank) {
        this.loyaltyRank = loyaltyRank;
    }

    public String getLoyaltyTier() {
        return this.loyaltyTier;
    }

    public Reservation loyaltyTier(String loyaltyTier) {
        this.setLoyaltyTier(loyaltyTier);
        return this;
    }

    public void setLoyaltyTier(String loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }

    public String getNotes() {
        return this.notes;
    }

    public Reservation notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public Reservation arrivalTime(String arrivalTime) {
        this.setArrivalTime(arrivalTime);
        return this;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getSeatedTime() {
        return this.seatedTime;
    }

    public Reservation seatedTime(String seatedTime) {
        this.setSeatedTime(seatedTime);
        return this;
    }

    public void setSeatedTime(String seatedTime) {
        this.seatedTime = seatedTime;
    }

    public String getLeftTime() {
        return this.leftTime;
    }

    public Reservation leftTime(String leftTime) {
        this.setLeftTime(leftTime);
        return this;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public String getClientRequests() {
        return this.clientRequests;
    }

    public Reservation clientRequests(String clientRequests) {
        this.setClientRequests(clientRequests);
        return this;
    }

    public void setClientRequests(String clientRequests) {
        this.clientRequests = clientRequests;
    }

    public Integer getComps() {
        return this.comps;
    }

    public Reservation comps(Integer comps) {
        this.setComps(comps);
        return this;
    }

    public void setComps(Integer comps) {
        this.comps = comps;
    }

    public String getCompsPriceType() {
        return this.compsPriceType;
    }

    public Reservation compsPriceType(String compsPriceType) {
        this.setCompsPriceType(compsPriceType);
        return this;
    }

    public void setCompsPriceType(String compsPriceType) {
        this.compsPriceType = compsPriceType;
    }

    public Integer getCostOption() {
        return this.costOption;
    }

    public Reservation costOption(Integer costOption) {
        this.setCostOption(costOption);
        return this;
    }

    public void setCostOption(Integer costOption) {
        this.costOption = costOption;
    }

    public String getPolicy() {
        return this.policy;
    }

    public Reservation policy(String policy) {
        this.setPolicy(policy);
        return this;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Integer getMinPrice() {
        return this.minPrice;
    }

    public Reservation minPrice(Integer minPrice) {
        this.setMinPrice(minPrice);
        return this;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Double getPrePayment() {
        return this.prePayment;
    }

    public Reservation prePayment(Double prePayment) {
        this.setPrePayment(prePayment);
        return this;
    }

    public void setPrePayment(Double prePayment) {
        this.prePayment = prePayment;
    }

    public Double getOnsitePayment() {
        return this.onsitePayment;
    }

    public Reservation onsitePayment(Double onsitePayment) {
        this.setOnsitePayment(onsitePayment);
        return this;
    }

    public void setOnsitePayment(Double onsitePayment) {
        this.onsitePayment = onsitePayment;
    }

    public Integer getTotalPayment() {
        return this.totalPayment;
    }

    public Reservation totalPayment(Integer totalPayment) {
        this.setTotalPayment(totalPayment);
        return this;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaidBy() {
        return this.paidBy;
    }

    public Reservation paidBy(String paidBy) {
        this.setPaidBy(paidBy);
        return this;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getServedBy() {
        return this.servedBy;
    }

    public Reservation servedBy(String servedBy) {
        this.setServedBy(servedBy);
        return this;
    }

    public void setServedBy(String servedBy) {
        this.servedBy = servedBy;
    }

    public Integer getRating() {
        return this.rating;
    }

    public Reservation rating(Integer rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getProblems() {
        return this.problems;
    }

    public Reservation problems(String problems) {
        this.setProblems(problems);
        return this;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getAutoAssignments() {
        return this.autoAssignments;
    }

    public Reservation autoAssignments(String autoAssignments) {
        this.setAutoAssignments(autoAssignments);
        return this;
    }

    public void setAutoAssignments(String autoAssignments) {
        this.autoAssignments = autoAssignments;
    }

    public String getExternalClientId() {
        return this.externalClientId;
    }

    public Reservation externalClientId(String externalClientId) {
        this.setExternalClientId(externalClientId);
        return this;
    }

    public void setExternalClientId(String externalClientId) {
        this.externalClientId = externalClientId;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public Reservation externalId(String externalId) {
        this.setExternalId(externalId);
        return this;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalReferenceCode() {
        return this.externalReferenceCode;
    }

    public Reservation externalReferenceCode(String externalReferenceCode) {
        this.setExternalReferenceCode(externalReferenceCode);
        return this;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public String getExternalUserId() {
        return this.externalUserId;
    }

    public Reservation externalUserId(String externalUserId) {
        this.setExternalUserId(externalUserId);
        return this;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getModifyReservationLink() {
        return this.modifyReservationLink;
    }

    public Reservation modifyReservationLink(String modifyReservationLink) {
        this.setModifyReservationLink(modifyReservationLink);
        return this;
    }

    public void setModifyReservationLink(String modifyReservationLink) {
        this.modifyReservationLink = modifyReservationLink;
    }

    public String getReferenceCode() {
        return this.referenceCode;
    }

    public Reservation referenceCode(String referenceCode) {
        this.setReferenceCode(referenceCode);
        return this;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public Boolean getReservationSmsOptin() {
        return this.reservationSmsOptin;
    }

    public Reservation reservationSmsOptin(Boolean reservationSmsOptin) {
        this.setReservationSmsOptin(reservationSmsOptin);
        return this;
    }

    public void setReservationSmsOptin(Boolean reservationSmsOptin) {
        this.reservationSmsOptin = reservationSmsOptin;
    }

    public String getReservationType() {
        return this.reservationType;
    }

    public Reservation reservationType(String reservationType) {
        this.setReservationType(reservationType);
        return this;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public Boolean getSendReminderEmail() {
        return this.sendReminderEmail;
    }

    public Reservation sendReminderEmail(Boolean sendReminderEmail) {
        this.setSendReminderEmail(sendReminderEmail);
        return this;
    }

    public void setSendReminderEmail(Boolean sendReminderEmail) {
        this.sendReminderEmail = sendReminderEmail;
    }

    public Boolean getSendreminderSms() {
        return this.sendreminderSms;
    }

    public Reservation sendreminderSms(Boolean sendreminderSms) {
        this.setSendreminderSms(sendreminderSms);
        return this;
    }

    public void setSendreminderSms(Boolean sendreminderSms) {
        this.sendreminderSms = sendreminderSms;
    }

    public String getSourceClientId() {
        return this.sourceClientId;
    }

    public Reservation sourceClientId(String sourceClientId) {
        this.setSourceClientId(sourceClientId);
        return this;
    }

    public void setSourceClientId(String sourceClientId) {
        this.sourceClientId = sourceClientId;
    }

    public String getUserId() {
        return this.userId;
    }

    public Reservation userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public Reservation userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<ResTag> getResTags() {
        return this.resTags;
    }

    public void setResTags(Set<ResTag> resTags) {
        if (this.resTags != null) {
            this.resTags.forEach(i -> i.setReservation(null));
        }
        if (resTags != null) {
            resTags.forEach(i -> i.setReservation(this));
        }
        this.resTags = resTags;
    }

    public Reservation resTags(Set<ResTag> resTags) {
        this.setResTags(resTags);
        return this;
    }

    public Reservation addResTag(ResTag resTag) {
        this.resTags.add(resTag);
        resTag.setReservation(this);
        return this;
    }

    public Reservation removeResTag(ResTag resTag) {
        this.resTags.remove(resTag);
        resTag.setReservation(null);
        return this;
    }

    public Set<ResPosTicket> getResPosTickets() {
        return this.resPosTickets;
    }

    public void setResPosTickets(Set<ResPosTicket> resPosTickets) {
        if (this.resPosTickets != null) {
            this.resPosTickets.forEach(i -> i.setReservation(null));
        }
        if (resPosTickets != null) {
            resPosTickets.forEach(i -> i.setReservation(this));
        }
        this.resPosTickets = resPosTickets;
    }

    public Reservation resPosTickets(Set<ResPosTicket> resPosTickets) {
        this.setResPosTickets(resPosTickets);
        return this;
    }

    public Reservation addResPosTicket(ResPosTicket resPosTicket) {
        this.resPosTickets.add(resPosTicket);
        return this;
    }

    public Reservation removeResPosTicket(ResPosTicket resPosTicket) {
        this.resPosTickets.remove(resPosTicket);
        return this;
    }

    public Set<ResCustomField> getResCustomFields() {
        return this.resCustomFields;
    }

    public void setResCustomFields(Set<ResCustomField> resCustomFields) {
        if (this.resCustomFields != null) {
            this.resCustomFields.forEach(i -> i.setReservation(null));
        }
        if (resCustomFields != null) {
            resCustomFields.forEach(i -> i.setReservation(this));
        }
        this.resCustomFields = resCustomFields;
    }

    public Reservation resCustomFields(Set<ResCustomField> resCustomFields) {
        this.setResCustomFields(resCustomFields);
        return this;
    }

    public Reservation addResCustomField(ResCustomField resCustomField) {
        this.resCustomFields.add(resCustomField);
        resCustomField.setReservation(this);
        return this;
    }

    public Reservation removeResCustomField(ResCustomField resCustomField) {
        this.resCustomFields.remove(resCustomField);
        resCustomField.setReservation(null);
        return this;
    }

    public Set<ResTable> getResTables() {
        return this.resTables;
    }

    public void setResTables(Set<ResTable> resTables) {
        if (this.resTables != null) {
            this.resTables.forEach(i -> i.setReservation(null));
        }
        if (resTables != null) {
            resTables.forEach(i -> i.setReservation(this));
        }
        this.resTables = resTables;
    }

    public Reservation resTables(Set<ResTable> resTables) {
        this.setResTables(resTables);
        return this;
    }

    public Reservation addResTable(ResTable resTable) {
        this.resTables.add(resTable);
        resTable.setReservation(this);
        return this;
    }

    public Reservation removeResTable(ResTable resTable) {
        this.resTables.remove(resTable);
        resTable.setReservation(null);
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(resvId, that.resvId) &&
            Objects.equals(created, that.created) &&
            Objects.equals(updated, that.updated) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(clientId, that.clientId) &&
            Objects.equals(venueGroupClientId, that.venueGroupClientId) &&
            Objects.equals(venueGroupId, that.venueGroupId) &&
            Objects.equals(venueId, that.venueId) &&
            Objects.equals(date, that.date) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(checkNumbers, that.checkNumbers) &&
            Objects.equals(shiftCategory, that.shiftCategory) &&
            Objects.equals(shiftPersistentId, that.shiftPersistentId) &&
            Objects.equals(maxGuests, that.maxGuests) &&
            Objects.equals(mfratioMale, that.mfratioMale) &&
            Objects.equals(mfratioFemale, that.mfratioFemale) &&
            Objects.equals(status, that.status) &&
            Objects.equals(statusDisplay, that.statusDisplay) &&
            Objects.equals(statusSimple, that.statusSimple) &&
            Objects.equals(accessPersistentId, that.accessPersistentId) &&
            Objects.equals(arrivedGuests, that.arrivedGuests) &&
            Objects.equals(isvip, that.isvip) &&
            Objects.equals(bookedby, that.bookedby) &&
            Objects.equals(clientReferenceCode, that.clientReferenceCode) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(email, that.email) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(address, that.address) &&
            Objects.equals(address2, that.address2) &&
            Objects.equals(city, that.city) &&
            Objects.equals(postalCode, that.postalCode) &&
            Objects.equals(state, that.state) &&
            Objects.equals(country, that.country) &&
            Objects.equals(loyaltyId, that.loyaltyId) &&
            Objects.equals(loyaltyRank, that.loyaltyRank) &&
            Objects.equals(loyaltyTier, that.loyaltyTier) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(arrivalTime, that.arrivalTime) &&
            Objects.equals(seatedTime, that.seatedTime) &&
            Objects.equals(leftTime, that.leftTime) &&
            Objects.equals(clientRequests, that.clientRequests) &&
            Objects.equals(comps, that.comps) &&
            Objects.equals(compsPriceType, that.compsPriceType) &&
            Objects.equals(costOption, that.costOption) &&
            Objects.equals(policy, that.policy) &&
            Objects.equals(minPrice, that.minPrice) &&
            Objects.equals(prePayment, that.prePayment) &&
            Objects.equals(onsitePayment, that.onsitePayment) &&
            Objects.equals(totalPayment, that.totalPayment) &&
            Objects.equals(paidBy, that.paidBy) &&
            Objects.equals(servedBy, that.servedBy) &&
            Objects.equals(rating, that.rating) &&
            Objects.equals(problems, that.problems) &&
            Objects.equals(autoAssignments, that.autoAssignments) &&
            Objects.equals(externalClientId, that.externalClientId) &&
            Objects.equals(externalId, that.externalId) &&
            Objects.equals(externalReferenceCode, that.externalReferenceCode) &&
            Objects.equals(externalUserId, that.externalUserId) &&
            Objects.equals(modifyReservationLink, that.modifyReservationLink) &&
            Objects.equals(referenceCode, that.referenceCode) &&
            Objects.equals(reservationSmsOptin, that.reservationSmsOptin) &&
            Objects.equals(reservationType, that.reservationType) &&
            Objects.equals(sendReminderEmail, that.sendReminderEmail) &&
            Objects.equals(sendreminderSms, that.sendreminderSms) &&
            Objects.equals(sourceClientId, that.sourceClientId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(resTags, that.resTags) &&
            Objects.equals(resPosTickets, that.resPosTickets) &&
            Objects.equals(resCustomFields, that.resCustomFields) &&
            Objects.equals(resTables, that.resTables)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            resvId,
            created,
            updated,
            deleted,
            clientId,
            venueGroupClientId,
            venueGroupId,
            venueId,
            date,
            duration,
            checkNumbers,
            shiftCategory,
            shiftPersistentId,
            maxGuests,
            mfratioMale,
            mfratioFemale,
            status,
            statusDisplay,
            statusSimple,
            accessPersistentId,
            arrivedGuests,
            isvip,
            bookedby,
            clientReferenceCode,
            lastname,
            firstname,
            email,
            phoneNumber,
            address,
            address2,
            city,
            postalCode,
            state,
            country,
            loyaltyId,
            loyaltyRank,
            loyaltyTier,
            notes,
            arrivalTime,
            seatedTime,
            leftTime,
            clientRequests,
            comps,
            compsPriceType,
            costOption,
            policy,
            minPrice,
            prePayment,
            onsitePayment,
            totalPayment,
            paidBy,
            servedBy,
            rating,
            problems,
            autoAssignments,
            externalClientId,
            externalId,
            externalReferenceCode,
            externalUserId,
            modifyReservationLink,
            referenceCode,
            reservationSmsOptin,
            reservationType,
            sendReminderEmail,
            sendreminderSms,
            sourceClientId,
            userId,
            userName
        );
    }

    @Override
    public String toString() {
        return (
            "Reservation{" +
            "id=" +
            id +
            ", resvId='" +
            resvId +
            '\'' +
            ", created=" +
            created +
            ", updated=" +
            updated +
            ", deleted=" +
            deleted +
            ", clientId='" +
            clientId +
            '\'' +
            ", venueGroupClientId='" +
            venueGroupClientId +
            '\'' +
            ", venueGroupId='" +
            venueGroupId +
            '\'' +
            ", venueId='" +
            venueId +
            '\'' +
            ", realDateTimeOfSlot=" +
            realDateTimeOfSlot +
            ", date=" +
            date +
            ", duration=" +
            duration +
            ", checkNumbers='" +
            checkNumbers +
            '\'' +
            ", shiftCategory='" +
            shiftCategory +
            '\'' +
            ", shiftPersistentId='" +
            shiftPersistentId +
            '\'' +
            ", maxGuests=" +
            maxGuests +
            ", mfratioMale=" +
            mfratioMale +
            ", mfratioFemale=" +
            mfratioFemale +
            ", status='" +
            status +
            '\'' +
            ", statusDisplay='" +
            statusDisplay +
            '\'' +
            ", statusSimple='" +
            statusSimple +
            '\'' +
            ", accessPersistentId='" +
            accessPersistentId +
            '\'' +
            ", arrivedGuests=" +
            arrivedGuests +
            ", isvip=" +
            isvip +
            ", bookedby='" +
            bookedby +
            '\'' +
            ", clientReferenceCode='" +
            clientReferenceCode +
            '\'' +
            ", lastname='" +
            lastname +
            '\'' +
            ", firstname='" +
            firstname +
            '\'' +
            ", email='" +
            email +
            '\'' +
            ", phoneNumber='" +
            phoneNumber +
            '\'' +
            ", address='" +
            address +
            '\'' +
            ", address2='" +
            address2 +
            '\'' +
            ", city='" +
            city +
            '\'' +
            ", postalCode='" +
            postalCode +
            '\'' +
            ", state='" +
            state +
            '\'' +
            ", country='" +
            country +
            '\'' +
            ", loyaltyId='" +
            loyaltyId +
            '\'' +
            ", loyaltyRank=" +
            loyaltyRank +
            ", loyaltyTier='" +
            loyaltyTier +
            '\'' +
            ", notes='" +
            notes +
            '\'' +
            ", arrivalTime='" +
            arrivalTime +
            '\'' +
            ", seatedTime='" +
            seatedTime +
            '\'' +
            ", leftTime='" +
            leftTime +
            '\'' +
            ", clientRequests='" +
            clientRequests +
            '\'' +
            ", comps=" +
            comps +
            ", compsPriceType='" +
            compsPriceType +
            '\'' +
            ", costOption=" +
            costOption +
            ", policy='" +
            policy +
            '\'' +
            ", minPrice=" +
            minPrice +
            ", prePayment=" +
            prePayment +
            ", onsitePayment=" +
            onsitePayment +
            ", totalPayment=" +
            totalPayment +
            ", paidBy='" +
            paidBy +
            '\'' +
            ", servedBy='" +
            servedBy +
            '\'' +
            ", rating=" +
            rating +
            ", problems='" +
            problems +
            '\'' +
            ", autoAssignments='" +
            autoAssignments +
            '\'' +
            ", externalClientId='" +
            externalClientId +
            '\'' +
            ", externalId='" +
            externalId +
            '\'' +
            ", externalReferenceCode='" +
            externalReferenceCode +
            '\'' +
            ", externalUserId='" +
            externalUserId +
            '\'' +
            ", modifyReservationLink='" +
            modifyReservationLink +
            '\'' +
            ", referenceCode='" +
            referenceCode +
            '\'' +
            ", reservationSmsOptin=" +
            reservationSmsOptin +
            ", reservationType='" +
            reservationType +
            '\'' +
            ", sendReminderEmail=" +
            sendReminderEmail +
            ", sendreminderSms=" +
            sendreminderSms +
            ", sourceClientId='" +
            sourceClientId +
            '\'' +
            ", userId='" +
            userId +
            '\'' +
            ", userName='" +
            userName +
            '\'' +
            ", resTags=" +
            resTags +
            ", resPosTickets=" +
            resPosTickets +
            ", resCustomFields=" +
            resCustomFields +
            ", resTables=" +
            resTables +
            '}'
        );
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
