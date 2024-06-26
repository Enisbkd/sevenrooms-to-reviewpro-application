package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A CustomField.
 */
@Document(collection = "custom_field")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomField implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("system_name")
    private String systemName;

    @Field("display_order")
    private Integer displayOrder;

    @Field("name")
    private String name;

    @Field("value")
    private String value;

    @DBRef
    @Field("client")
    @JsonIgnoreProperties(value = { "clientPhoto", "clientVenueStats", "customFields", "clientTags", "memberGroups" }, allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public CustomField id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public CustomField systemName(String systemName) {
        this.setSystemName(systemName);
        return this;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public CustomField displayOrder(Integer displayOrder) {
        this.setDisplayOrder(displayOrder);
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getName() {
        return this.name;
    }

    public CustomField name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public CustomField value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CustomField client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomField)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomField) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomField{" +
            "id=" + getId() +
            ", systemName='" + getSystemName() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
