package com.robot.automation.pages;

import com.robot.automation.control.Button;
import com.robot.automation.control.TextInput;
import com.robot.automation.utils.Property;
import org.openqa.selenium.support.FindBy;

public class WorkOrderLookupPage extends PageObject {

    private static final String WORK_ORDER_NUMBER_INPUT_XPATH = "//input[@id='workOrderNumber']";
    private static final String LOOKUP_BUTTON_XPATH = "//button[@id='btnSubmit']";

    @FindBy(xpath = WORK_ORDER_NUMBER_INPUT_XPATH)
    private TextInput workOrderNumber;

    @FindBy(xpath = LOOKUP_BUTTON_XPATH)
    private Button lookup;

    public WorkOrderLookupPage() {
        goTo(Property.getInstance().getPropertyByKey("Url"));
    }


    public void setWorkOrderNumber(String number) {
        if (number != null) {
            workOrderNumber.sendKeys(number);
        }
    }

    public void clickLookupButton() {
       lookup.click();
    }
}
