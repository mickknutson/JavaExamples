package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractValueObject implements java.io.Serializable, Cloneable {

    @Override
    public AbstractValueObject clone() {
        try {
            return CloneUtils.deepClone((AbstractValueObject) super.clone());
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
