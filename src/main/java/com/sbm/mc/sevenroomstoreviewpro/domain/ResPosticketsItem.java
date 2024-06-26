package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ResPosticketsItem.
 */
@Document(collection = "res_postickets_item")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResPosticketsItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("price")
    private Double price;

    @Field("name")
    private String name;

    @Field("quantity")
    private Integer quantity;

    @DBRef
    @Field("resPosTicket")
    @JsonIgnoreProperties(value = { "resPosticketsItems", "reservation" }, allowSetters = true)
    private ResPosTicket resPosTicket;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ResPosticketsItem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }

    public ResPosticketsItem price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public ResPosticketsItem name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public ResPosticketsItem quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ResPosTicket getResPosTicket() {
        return this.resPosTicket;
    }

    public void setResPosTicket(ResPosTicket resPosTicket) {
        this.resPosTicket = resPosTicket;
    }

    public ResPosticketsItem resPosTicket(ResPosTicket resPosTicket) {
        this.setResPosTicket(resPosTicket);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResPosticketsItem)) {
            return false;
        }
        return getId() != null && getId().equals(((ResPosticketsItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResPosticketsItem{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", name='" + getName() + "'" +
            ", quantity=" + getQuantity() +
            "}";
    }
}
