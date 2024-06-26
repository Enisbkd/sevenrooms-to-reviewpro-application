package com.sbm.mc.sevenroomstoreviewpro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Set;

public class ClientPayload implements Serializable {

    @JsonProperty("entity")
    private Client client;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonProperty("techSource")
    private String techSource;

    @JsonIgnore
    private Set<UpdateField> updates;

    public String getTechSource() {
        return techSource;
    }

    public void setTechSource(String techSource) {
        this.techSource = techSource;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientPayload(Client client, String event_type, String entity_type, Set<UpdateField> updates, String techSource) {
        this.client = client;
        this.event_type = event_type;
        this.entity_type = entity_type;
        this.updates = updates;
        this.techSource = techSource;
    }

    public ClientPayload() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((event_type == null) ? 0 : event_type.hashCode());
        result = prime * result + ((entity_type == null) ? 0 : entity_type.hashCode());
        result = prime * result + ((techSource == null) ? 0 : techSource.hashCode());
        result = prime * result + ((updates == null) ? 0 : updates.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ClientPayload other = (ClientPayload) obj;
        if (client == null) {
            if (other.client != null) return false;
        } else if (!client.equals(other.client)) return false;
        if (event_type == null) {
            if (other.event_type != null) return false;
        } else if (!event_type.equals(other.event_type)) return false;
        if (entity_type == null) {
            if (other.entity_type != null) return false;
        } else if (!entity_type.equals(other.entity_type)) return false;
        if (techSource == null) {
            if (other.techSource != null) return false;
        } else if (!techSource.equals(other.techSource)) return false;
        if (updates == null) {
            if (other.updates != null) return false;
        } else if (!updates.equals(other.updates)) return false;
        return true;
    }

    @Override
    public String toString() {
        return (
            "ClientPayload [client=" +
            client +
            ", event_type=" +
            event_type +
            ", entity_type=" +
            entity_type +
            ", techSource=" +
            techSource +
            ", updates=" +
            updates +
            "]"
        );
    }
}
