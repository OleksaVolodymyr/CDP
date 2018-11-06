package com.robot.automation.bo;

import com.robot.automation.model.UATModel;
import com.robot.automation.pages.WorkOrderInputPage;

public class WorkOrderInputPageBO {

    private WorkOrderInputPage workOrderInputPage;

    public WorkOrderInputPageBO() {
        this.workOrderInputPage = new WorkOrderInputPage();
    }

    public boolean isWorkOrderCreated(String workOrderID) {
        return workOrderInputPage.getWorkOrderNumber().equals(workOrderID);
    }

    public void enterSearchParameters(UATModel uat) {
        workOrderInputPage.enterLastName(uat.getLastName());
        workOrderInputPage.enterFirstName(uat.getFirstName());
        workOrderInputPage.enterMiddleName("");
        workOrderInputPage.enterDateOfBirth(uat.getDateOfBirth());
        workOrderInputPage.selectJurisdiction("IL");
        workOrderInputPage.selectCounty("Kane County");
        workOrderInputPage.clickSubmitJob();
    }


}
