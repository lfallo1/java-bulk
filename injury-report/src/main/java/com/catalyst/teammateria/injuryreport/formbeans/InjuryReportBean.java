package com.catalyst.teammateria.injuryreport.formbeans;

/**
 * Form bean for use on the injury report page
 * 
 * @author dGrimes
 */
public class InjuryReportBean {

    private Integer employeeId;

    private Integer reporterId;

    private Integer weatherId;

    private Integer bodyPartId;

    private Integer injuryTypeId;

    private String reportDate;

    private String reportManagementDate;

    private String timeOfInjury;

    private String description;

    
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getReporterId() {
        return reporterId;
    }

    public void setReporterId(Integer reporterId) {
        this.reporterId = reporterId;
    }

    public Integer getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public Integer getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(Integer bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public Integer getInjuryTypeId() {
        return injuryTypeId;
    }

    public void setInjuryTypeId(Integer injuryTypeId) {
        this.injuryTypeId = injuryTypeId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportManagementDate() {
        return reportManagementDate;
    }

    public void setReportManagementDate(String reportManagementDate) {
        this.reportManagementDate = reportManagementDate;
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
}
