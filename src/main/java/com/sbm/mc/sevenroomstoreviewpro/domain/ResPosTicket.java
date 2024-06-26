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
 * A ResPosTicket.
 */
@Document(collection = "res_pos_ticket")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResPosTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("status")
    private String status;

    @Field("admin_fee")
    private Double adminFee;

    @Field("code")
    private Integer code;

    @Field("table_no")
    private String tableNo;

    @Field("tax")
    private Double tax;

    @Field("business_id")
    private Integer businessId;

    @Field("ticket_id")
    private Integer ticketId;

    @Field("local_posticket_id")
    private String localPosticketId;

    @Field("employee_name")
    private String employeeName;

    @Field("total")
    private Double total;

    @Field("subtotal")
    private Double subtotal;

    @Field("start_time")
    private String startTime;

    @Field("service_charge")
    private Double serviceCharge;

    @Field("endtime")
    private String endtime;

    @DBRef
    @Field("resPosticketsItem")
    @JsonIgnoreProperties(value = { "resPosTicket" }, allowSetters = true)
    private Set<ResPosticketsItem> resPosticketsItems = new HashSet<>();

    @DBRef
    @Field("reservation")
    @JsonIgnoreProperties(value = { "resTags", "resPosTickets", "resCustomFields", "resTables" }, allowSetters = true)
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ResPosTicket id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public ResPosTicket status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAdminFee() {
        return this.adminFee;
    }

    public ResPosTicket adminFee(Double adminFee) {
        this.setAdminFee(adminFee);
        return this;
    }

    public void setAdminFee(Double adminFee) {
        this.adminFee = adminFee;
    }

    public Integer getCode() {
        return this.code;
    }

    public ResPosTicket code(Integer code) {
        this.setCode(code);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTableNo() {
        return this.tableNo;
    }

    public ResPosTicket tableNo(String tableNo) {
        this.setTableNo(tableNo);
        return this;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Double getTax() {
        return this.tax;
    }

    public ResPosTicket tax(Double tax) {
        this.setTax(tax);
        return this;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Integer getBusinessId() {
        return this.businessId;
    }

    public ResPosTicket businessId(Integer businessId) {
        this.setBusinessId(businessId);
        return this;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getTicketId() {
        return this.ticketId;
    }

    public ResPosTicket ticketId(Integer ticketId) {
        this.setTicketId(ticketId);
        return this;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getLocalPosticketId() {
        return this.localPosticketId;
    }

    public ResPosTicket localPosticketId(String localPosticketId) {
        this.setLocalPosticketId(localPosticketId);
        return this;
    }

    public void setLocalPosticketId(String localPosticketId) {
        this.localPosticketId = localPosticketId;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public ResPosTicket employeeName(String employeeName) {
        this.setEmployeeName(employeeName);
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getTotal() {
        return this.total;
    }

    public ResPosTicket total(Double total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubtotal() {
        return this.subtotal;
    }

    public ResPosTicket subtotal(Double subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public ResPosTicket startTime(String startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Double getServiceCharge() {
        return this.serviceCharge;
    }

    public ResPosTicket serviceCharge(Double serviceCharge) {
        this.setServiceCharge(serviceCharge);
        return this;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public ResPosTicket endtime(String endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Set<ResPosticketsItem> getResPosticketsItems() {
        return this.resPosticketsItems;
    }

    public void setResPosticketsItems(Set<ResPosticketsItem> resPosticketsItems) {
        if (this.resPosticketsItems != null) {
            this.resPosticketsItems.forEach(i -> i.setResPosTicket(null));
        }
        if (resPosticketsItems != null) {
            resPosticketsItems.forEach(i -> i.setResPosTicket(this));
        }
        this.resPosticketsItems = resPosticketsItems;
    }

    public ResPosTicket resPosticketsItems(Set<ResPosticketsItem> resPosticketsItems) {
        this.setResPosticketsItems(resPosticketsItems);
        return this;
    }

    public ResPosTicket addResPosticketsItem(ResPosticketsItem resPosticketsItem) {
        this.resPosticketsItems.add(resPosticketsItem);
        resPosticketsItem.setResPosTicket(this);
        return this;
    }

    public ResPosTicket removeResPosticketsItem(ResPosticketsItem resPosticketsItem) {
        this.resPosticketsItems.remove(resPosticketsItem);
        resPosticketsItem.setResPosTicket(null);
        return this;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ResPosTicket reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResPosTicket)) {
            return false;
        }
        return getId() != null && getId().equals(((ResPosTicket) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResPosTicket{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", adminFee=" + getAdminFee() +
            ", code=" + getCode() +
            ", tableNo='" + getTableNo() + "'" +
            ", tax=" + getTax() +
            ", businessId=" + getBusinessId() +
            ", ticketId=" + getTicketId() +
            ", localPosticketId='" + getLocalPosticketId() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", total=" + getTotal() +
            ", subtotal=" + getSubtotal() +
            ", startTime='" + getStartTime() + "'" +
            ", serviceCharge=" + getServiceCharge() +
            ", endtime='" + getEndtime() + "'" +
            "}";
    }
}
