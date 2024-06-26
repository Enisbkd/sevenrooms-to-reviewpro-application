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
 * A ClientVenueStats.
 */
@Document(collection = "client_venue_stats")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientVenueStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("total_spend_localper_cover")
    private Double totalSpendLocalperCover;

    @Field("last_visit_date")
    private String lastVisitDate;

    @Field("total_cancellations")
    private Integer totalCancellations;

    @Field("total_covers")
    private Integer totalCovers;

    @Field("avg_rating")
    private Integer avgRating;

    @Field("total_spendper_cover")
    private Double totalSpendperCover;

    @Field("total_spend")
    private Double totalSpend;

    @Field("total_no_shows")
    private Integer totalNoShows;

    @Field("num_ratings")
    private Integer numRatings;

    @Field("total_spend_per_visit")
    private Double totalSpendPerVisit;

    @Field("total_spend_local")
    private Double totalSpendLocal;

    @Field("total_spend_local_per_visit")
    private Double totalSpendLocalPerVisit;

    @Field("total_visits")
    private Integer totalVisits;

    @Field("gross_total")
    private Double grossTotal;

    @Field("total_order_count")
    private Double totalOrderCount;

    @Field("total_order_cancellations")
    private Double totalOrderCancellations;

    @Field("total_order_spend")
    private Double totalOrderSpend;

    @Field("gross_order_total")
    private Double grossOrderTotal;

    @Field("total_order_spend_local")
    private Double totalOrderSpendLocal;

    @Field("last_order_date")
    private String lastOrderDate;

    @Field("total_spendper_order")
    private Double totalSpendperOrder;

    @Field("total_spend_localper_order")
    private Double totalSpendLocalperOrder;

    @Field("venue_id")
    private String venueId;

    @Field("venue_marketing_optin")
    private Boolean venueMarketingOptin;

    @Field("venue_marketing_optints")
    private String venueMarketingOptints;

    @DBRef
    @Field("bookingName")
    @JsonIgnoreProperties(value = { "clientVenueStats" }, allowSetters = true)
    private Set<BookingName> bookingNames = new HashSet<>();

    @DBRef
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ClientVenueStats id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotalSpendLocalperCover() {
        return this.totalSpendLocalperCover;
    }

    public ClientVenueStats totalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.setTotalSpendLocalperCover(totalSpendLocalperCover);
        return this;
    }

    public void setTotalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.totalSpendLocalperCover = totalSpendLocalperCover;
    }

    public String getLastVisitDate() {
        return this.lastVisitDate;
    }

    public ClientVenueStats lastVisitDate(String lastVisitDate) {
        this.setLastVisitDate(lastVisitDate);
        return this;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Integer getTotalCancellations() {
        return this.totalCancellations;
    }

    public ClientVenueStats totalCancellations(Integer totalCancellations) {
        this.setTotalCancellations(totalCancellations);
        return this;
    }

    public void setTotalCancellations(Integer totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Integer getTotalCovers() {
        return this.totalCovers;
    }

    public ClientVenueStats totalCovers(Integer totalCovers) {
        this.setTotalCovers(totalCovers);
        return this;
    }

    public void setTotalCovers(Integer totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Integer getAvgRating() {
        return this.avgRating;
    }

    public ClientVenueStats avgRating(Integer avgRating) {
        this.setAvgRating(avgRating);
        return this;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Double getTotalSpendperCover() {
        return this.totalSpendperCover;
    }

    public ClientVenueStats totalSpendperCover(Double totalSpendperCover) {
        this.setTotalSpendperCover(totalSpendperCover);
        return this;
    }

    public void setTotalSpendperCover(Double totalSpendperCover) {
        this.totalSpendperCover = totalSpendperCover;
    }

    public Double getTotalSpend() {
        return this.totalSpend;
    }

    public ClientVenueStats totalSpend(Double totalSpend) {
        this.setTotalSpend(totalSpend);
        return this;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Integer getTotalNoShows() {
        return this.totalNoShows;
    }

    public ClientVenueStats totalNoShows(Integer totalNoShows) {
        this.setTotalNoShows(totalNoShows);
        return this;
    }

    public void setTotalNoShows(Integer totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Integer getNumRatings() {
        return this.numRatings;
    }

    public ClientVenueStats numRatings(Integer numRatings) {
        this.setNumRatings(numRatings);
        return this;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Double getTotalSpendPerVisit() {
        return this.totalSpendPerVisit;
    }

    public ClientVenueStats totalSpendPerVisit(Double totalSpendPerVisit) {
        this.setTotalSpendPerVisit(totalSpendPerVisit);
        return this;
    }

    public void setTotalSpendPerVisit(Double totalSpendPerVisit) {
        this.totalSpendPerVisit = totalSpendPerVisit;
    }

    public Double getTotalSpendLocal() {
        return this.totalSpendLocal;
    }

    public ClientVenueStats totalSpendLocal(Double totalSpendLocal) {
        this.setTotalSpendLocal(totalSpendLocal);
        return this;
    }

    public void setTotalSpendLocal(Double totalSpendLocal) {
        this.totalSpendLocal = totalSpendLocal;
    }

    public Double getTotalSpendLocalPerVisit() {
        return this.totalSpendLocalPerVisit;
    }

    public ClientVenueStats totalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.setTotalSpendLocalPerVisit(totalSpendLocalPerVisit);
        return this;
    }

    public void setTotalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.totalSpendLocalPerVisit = totalSpendLocalPerVisit;
    }

    public Integer getTotalVisits() {
        return this.totalVisits;
    }

    public ClientVenueStats totalVisits(Integer totalVisits) {
        this.setTotalVisits(totalVisits);
        return this;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Double getGrossTotal() {
        return this.grossTotal;
    }

    public ClientVenueStats grossTotal(Double grossTotal) {
        this.setGrossTotal(grossTotal);
        return this;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public Double getTotalOrderCount() {
        return this.totalOrderCount;
    }

    public ClientVenueStats totalOrderCount(Double totalOrderCount) {
        this.setTotalOrderCount(totalOrderCount);
        return this;
    }

    public void setTotalOrderCount(Double totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Double getTotalOrderCancellations() {
        return this.totalOrderCancellations;
    }

    public ClientVenueStats totalOrderCancellations(Double totalOrderCancellations) {
        this.setTotalOrderCancellations(totalOrderCancellations);
        return this;
    }

    public void setTotalOrderCancellations(Double totalOrderCancellations) {
        this.totalOrderCancellations = totalOrderCancellations;
    }

    public Double getTotalOrderSpend() {
        return this.totalOrderSpend;
    }

    public ClientVenueStats totalOrderSpend(Double totalOrderSpend) {
        this.setTotalOrderSpend(totalOrderSpend);
        return this;
    }

    public void setTotalOrderSpend(Double totalOrderSpend) {
        this.totalOrderSpend = totalOrderSpend;
    }

    public Double getGrossOrderTotal() {
        return this.grossOrderTotal;
    }

    public ClientVenueStats grossOrderTotal(Double grossOrderTotal) {
        this.setGrossOrderTotal(grossOrderTotal);
        return this;
    }

    public void setGrossOrderTotal(Double grossOrderTotal) {
        this.grossOrderTotal = grossOrderTotal;
    }

    public Double getTotalOrderSpendLocal() {
        return this.totalOrderSpendLocal;
    }

    public ClientVenueStats totalOrderSpendLocal(Double totalOrderSpendLocal) {
        this.setTotalOrderSpendLocal(totalOrderSpendLocal);
        return this;
    }

    public void setTotalOrderSpendLocal(Double totalOrderSpendLocal) {
        this.totalOrderSpendLocal = totalOrderSpendLocal;
    }

    public String getLastOrderDate() {
        return this.lastOrderDate;
    }

    public ClientVenueStats lastOrderDate(String lastOrderDate) {
        this.setLastOrderDate(lastOrderDate);
        return this;
    }

    public void setLastOrderDate(String lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public Double getTotalSpendperOrder() {
        return this.totalSpendperOrder;
    }

    public ClientVenueStats totalSpendperOrder(Double totalSpendperOrder) {
        this.setTotalSpendperOrder(totalSpendperOrder);
        return this;
    }

    public void setTotalSpendperOrder(Double totalSpendperOrder) {
        this.totalSpendperOrder = totalSpendperOrder;
    }

    public Double getTotalSpendLocalperOrder() {
        return this.totalSpendLocalperOrder;
    }

    public ClientVenueStats totalSpendLocalperOrder(Double totalSpendLocalperOrder) {
        this.setTotalSpendLocalperOrder(totalSpendLocalperOrder);
        return this;
    }

    public void setTotalSpendLocalperOrder(Double totalSpendLocalperOrder) {
        this.totalSpendLocalperOrder = totalSpendLocalperOrder;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public ClientVenueStats venueId(String venueId) {
        this.setVenueId(venueId);
        return this;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public Boolean getVenueMarketingOptin() {
        return this.venueMarketingOptin;
    }

    public ClientVenueStats venueMarketingOptin(Boolean venueMarketingOptin) {
        this.setVenueMarketingOptin(venueMarketingOptin);
        return this;
    }

    public void setVenueMarketingOptin(Boolean venueMarketingOptin) {
        this.venueMarketingOptin = venueMarketingOptin;
    }

    public String getVenueMarketingOptints() {
        return this.venueMarketingOptints;
    }

    public ClientVenueStats venueMarketingOptints(String venueMarketingOptints) {
        this.setVenueMarketingOptints(venueMarketingOptints);
        return this;
    }

    public void setVenueMarketingOptints(String venueMarketingOptints) {
        this.venueMarketingOptints = venueMarketingOptints;
    }

    public Set<BookingName> getBookingNames() {
        return this.bookingNames;
    }

    public void setBookingNames(Set<BookingName> bookingNames) {
        if (this.bookingNames != null) {
            this.bookingNames.forEach(i -> i.setClientVenueStats(null));
        }
        if (bookingNames != null) {
            bookingNames.forEach(i -> i.setClientVenueStats(this));
        }
        this.bookingNames = bookingNames;
    }

    public ClientVenueStats bookingNames(Set<BookingName> bookingNames) {
        this.setBookingNames(bookingNames);
        return this;
    }

    public ClientVenueStats addBookingName(BookingName bookingName) {
        this.bookingNames.add(bookingName);
        bookingName.setClientVenueStats(this);
        return this;
    }

    public ClientVenueStats removeBookingName(BookingName bookingName) {
        this.bookingNames.remove(bookingName);
        bookingName.setClientVenueStats(null);
        return this;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.setClientVenueStats(null);
        }
        if (client != null) {
            client.setClientVenueStats(this);
        }
        this.client = client;
    }

    public ClientVenueStats client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientVenueStats)) {
            return false;
        }
        return getId() != null && getId().equals(((ClientVenueStats) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientVenueStats{" +
            "id=" + getId() +
            ", totalSpendLocalperCover=" + getTotalSpendLocalperCover() +
            ", lastVisitDate='" + getLastVisitDate() + "'" +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalCovers=" + getTotalCovers() +
            ", avgRating=" + getAvgRating() +
            ", totalSpendperCover=" + getTotalSpendperCover() +
            ", totalSpend=" + getTotalSpend() +
            ", totalNoShows=" + getTotalNoShows() +
            ", numRatings=" + getNumRatings() +
            ", totalSpendPerVisit=" + getTotalSpendPerVisit() +
            ", totalSpendLocal=" + getTotalSpendLocal() +
            ", totalSpendLocalPerVisit=" + getTotalSpendLocalPerVisit() +
            ", totalVisits=" + getTotalVisits() +
            ", grossTotal=" + getGrossTotal() +
            ", totalOrderCount=" + getTotalOrderCount() +
            ", totalOrderCancellations=" + getTotalOrderCancellations() +
            ", totalOrderSpend=" + getTotalOrderSpend() +
            ", grossOrderTotal=" + getGrossOrderTotal() +
            ", totalOrderSpendLocal=" + getTotalOrderSpendLocal() +
            ", lastOrderDate='" + getLastOrderDate() + "'" +
            ", totalSpendperOrder=" + getTotalSpendperOrder() +
            ", totalSpendLocalperOrder=" + getTotalSpendLocalperOrder() +
            ", venueId='" + getVenueId() + "'" +
            ", venueMarketingOptin='" + getVenueMarketingOptin() + "'" +
            ", venueMarketingOptints='" + getVenueMarketingOptints() + "'" +
            "}";
    }
}
