package com.robot.automation.bo;

import com.robot.automation.pages.WorkOrderLookupPage;

public class WorkOrderLookupPageBO {

    private WorkOrderLookupPage workOrderLookupPage;

    public WorkOrderLookupPageBO() {
        this.workOrderLookupPage = new WorkOrderLookupPage();
    }

    public void createWorkOrder(String workOrderID){
        workOrderLookupPage.setWorkOrderNumber(workOrderID);
        workOrderLookupPage.clickLookupButton();
    }
}
