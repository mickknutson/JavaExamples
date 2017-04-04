package com.baselogic.tutorials.domain;

import com.baselogic.tutorials.domain.enums.AuditOperation;

import java.io.Serializable;
import java.util.*;


/**
 * Audit Entry Class
 *
 * @author Mick Knutson
 * @see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * @see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * @see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * @see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 * @see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * @see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 * @since 2012
 */
public class AuditEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String auditUser;
    private Long eventId;
    private String tableName;
    private AuditOperation operation;
    private Calendar operationTime;
    private Collection<AuditField> fields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public AuditOperation getOperation() {
        return operation;
    }

    public void setOperation(AuditOperation operation) {
        this.operation = operation;
    }

    public Calendar getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Calendar operationTime) {
        this.operationTime = operationTime;
    }

    public Collection<AuditField> getFields() {
        return fields;
    }

    public void setFields(Collection<AuditField> fields) {
        this.fields = fields;
    }

    /**
     * Set hashCode to audit entry's ID
     */
    @Override
    public int hashCode() {
        return id.intValue();
    }

    /**
     * Assign equivalence based on audit entry's ID
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AuditEntry) {
            if (((AuditEntry) obj).getId().equals(id))
                return true;
        }
        return false;
    }
}
