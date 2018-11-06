package com.robot.automation.model;

import com.opencsv.bean.CsvBindByName;

public class UATModel {

    @CsvBindByName(column = "ScenarioID")
    private String scenarioID;
    @CsvBindByName
    private String botSearchWorkOrder;
    @CsvBindByName
    private String scenarioTitle;
    @CsvBindByName
    private String firstName;
    @CsvBindByName
    private String lastName;
    @CsvBindByName(column = "DOB")
    private String dateOfBirth;
    @CsvBindByName
    private String botSearchOutcome;
    @CsvBindByName
    private String errorCode;
    @CsvBindByName(column = "#ofSummaryImages")
    private String NumberOfSummaryImages;
    @CsvBindByName(column = "#NumberOfCandidates")
    private String NumberOfCandidates;
    @CsvBindByName(column = "#NumberOfIncludedRecords")
    private String NumberOfIncludedRecords;
    @CsvBindByName(column = "#NumberOfOpenedRecordDetails")
    private String NumberOfOpenedRecordDetails;
    @CsvBindByName(column = "#NumberOfRecordDetailsImages")
    private String NumberOfRecordDetailsImages;
    @CsvBindByName
    private String kapowResult;

    public String getScenarioID() {
        return scenarioID;
    }

    public String getBotSearchWorkOrder() {
        return botSearchWorkOrder;
    }

    public String getScenarioTitle() {
        return scenarioTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBotSearchOutcome() {
        return botSearchOutcome;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getNumberOfSummaryImages() {
        return NumberOfSummaryImages;
    }

    public String getNumberOfCandidates() {
        return NumberOfCandidates;
    }

    public String getNumberOfIncludedRecords() {
        return NumberOfIncludedRecords;
    }

    public String getNumberOfOpenedRecordDetails() {
        return NumberOfOpenedRecordDetails;
    }

    public String getNumberOfRecordDetailsImages() {
        return NumberOfRecordDetailsImages;
    }

    public String getKapowResult() {
        return kapowResult;
    }

    @Override
    public String toString() {
        return "UATModel{" +
                "scenarioID='" + scenarioID + '\'' +
                ", botSearchWorkOrder='" + botSearchWorkOrder + '\'' +
                ", scenarioTitle='" + scenarioTitle + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", botSearchOutcome='" + botSearchOutcome + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", NumberOfSummaryImages='" + NumberOfSummaryImages + '\'' +
                ", NumberOfCandidates='" + NumberOfCandidates + '\'' +
                ", NumberOfIncludedRecords='" + NumberOfIncludedRecords + '\'' +
                ", NumberOfOpenedRecordDetails='" + NumberOfOpenedRecordDetails + '\'' +
                ", NumberOfRecordDetailsImages='" + NumberOfRecordDetailsImages + '\'' +
                ", kapowResult='" + kapowResult + '\'' +
                '}';
    }
}
