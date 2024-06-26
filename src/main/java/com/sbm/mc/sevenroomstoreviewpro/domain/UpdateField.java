package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.io.Serializable;
import java.util.Objects;

public class UpdateField implements Serializable {

    private String field;
    private Object previous;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateField that = (UpdateField) o;
        return Objects.equals(field, that.field) && Objects.equals(previous, that.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, previous);
    }

    @Override
    public String toString() {
        return "UpdateField{" + "field='" + field + '\'' + ", previous=" + previous + '}';
    }

    public UpdateField() {}
}
