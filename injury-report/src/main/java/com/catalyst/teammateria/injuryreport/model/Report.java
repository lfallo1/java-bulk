package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Report model to represent injury report records in the database
 * 
 * @author nPoloway
 */
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    private int reportId;

    private Employee employee;

    private User reporter;

    private Weather weather;

    private BodyPart bodyPart;

    private InjuryType injuryType;

    private User approver;

    private Date dateOfReport;

    private Date dateReportedToManagement;

    private Time timeOfInjury;

    private String description;

    private String approverComments;

    private Date dateUpdated;

    private Boolean enabled;

    public Report() {
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(Date dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public InjuryType getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(InjuryType injuryType) {
        this.injuryType = injuryType;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public Date getDateReportedToManagement() {
        return dateReportedToManagement;
    }

    public void setDateReportedToManagement(Date dateReportedToManagement) {
        this.dateReportedToManagement = dateReportedToManagement;
    }

    public Time getTimeOfInjury() {
        return timeOfInjury;
    }

    public void setTimeOfInjury(Time timeOfInjury) {
        this.timeOfInjury = timeOfInjury;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
	@Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.reportId);
        return hashBuilder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Report rhs = (Report)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.reportId, rhs.getReportId());
        return equalsBuilder.isEquals();
    }
}
