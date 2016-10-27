package com.catalyst.teammateria.injuryreport.formbeans;

/**
 * Form bean to assist in the collection of data from the admin proofing page
 * 
 * @author dGrimes
 */
public class AdminProofingBean {

    private String dateReportedToManagement;

    private String dateOfReport;

    private Integer weatherId;

    private Integer typeId;

    private Integer bodyPartId;

    private String timeOfInjury;

    private String description;

    private String approverComments;

    public String getDateReportedToManagement() {
        return dateReportedToManagement;
    }

    public void setDateReportedToManagement(String dateReportedToManagement) {
        this.dateReportedToManagement = dateReportedToManagement;
    }

    public String getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(String dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(Integer bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getTimeOfInjury() {
        return timeOfInjury;
    }

    public void setTimeOfInjury(String timeOfInjury) {
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
}
