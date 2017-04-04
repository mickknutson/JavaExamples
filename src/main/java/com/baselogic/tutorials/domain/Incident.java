package com.baselogic.tutorials.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * See
 * http://www.petrikainulainen.net/spring-data-jpa-tutorial/
 * <p/>
 * http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/
 *
 * @author Mick Knutson
 */
public class Incident extends AbstractEntity
        implements Serializable {

    private String name;
    private String baseNumber;
    private String bflNumber;
    private String nationality;
    private String location;
    private String equipment;
    private String baseRelated;
    private String subjectLine;
    private String description;
    private String referenceUrl;
    private Date creationTime;
    private Date modificationTime;
//    private List<Comment> comments;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident", cascade = CascadeType.ALL)
    private Collection<Comment> comments = new LinkedHashSet<Comment>();

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column
//    private Date date;

//    @Enumerated(EnumType.STRING)
//    private ObjectType objectType;


//    @ElementCollection
//    @CollectionTable(name = JpaConstants.ARTIFACTS,
//            joinColumns = @JoinColumn(name = JpaConstants.SUD_ID))
//    @Column(name = JpaConstants.SUD_ARTIFACTS, nullable = true)
//    private Malfunction malfunction; // simple String ID
    //private String malfunctions; // simple String ID

//    @Column
//    @Embeddable
//    private List<Tag> tags;

//    @Column
//    private List<Comment> comments;


    public Incident() {
//        malfunctions = new ArrayList<Malfunction>();
//        comments = new ArrayList<Comment>();
//        deployResults = new ArrayList<DeployResult>();
//        equipments = new ArrayList<Equipment>();
    }


//    @ElementCollection
//    @CollectionTable(name = JpaConstants.COMMENT,
//            joinColumns = @JoinColumn(name = JpaConstants.SUD_ID))
//    @Column(name = JpaConstants.SUD_COMMENTS, nullable = true)
//    private List<Image> images;


//    @ElementCollection
//    @CollectionTable(name = JpaConstants.DEPLOY_RESULT,
//            joinColumns = @JoinColumn(name = JpaConstants.SUD_ID))
//    @Column(name = JpaConstants.SUD_DEPLOY_RESULTS, nullable = true)
//    private List<DeployResult> deployResults;


//    @ElementCollection
//    @CollectionTable(name = JpaConstants.TEST_RESULT,
//            joinColumns = @JoinColumn(name = JpaConstants.SUD_ID))
//    @Column(name = JpaConstants.SUD_TEST_RESULTS, nullable = true)
//    private Equipment equipments;


    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(String baseNumber) {
        this.baseNumber = baseNumber;
    }

    public String getBflNumber() {
        return bflNumber;
    }

    public void setBflNumber(String bflNumber) {
        this.bflNumber = bflNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getBaseRelated() {
        return baseRelated;
    }

    public void setBaseRelated(String baseRelated) {
        this.baseRelated = baseRelated;
    }

    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }


    //--- Relationship methods ----------------------------------------------------//


    public void preUpdate() {
        modificationTime = new Date();
    }

    public void prePersist() {
        Date now = new Date();
        creationTime = now;
        modificationTime = now;
    }


    //--- Common methods ----------------------------------------------------//

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
