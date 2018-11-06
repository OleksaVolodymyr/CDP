package com.robot.automation.pages;

import com.robot.automation.control.Button;
import com.robot.automation.control.Text;
import com.robot.automation.control.TextInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WorkOrderInputPage extends PageObject {

    private static final String FIRST_NAME_INPUT_XPATH = "//input[@id = 'firstName']";
    private static final String LAST_NAME_INPUT_XPATH = "//input[@id='lastName']";
    private static final String MIDDLE_NAME_INPUT_XPATH = "//input[@id='MiddleName']";
    private static final String DOB_INPUT_XPATH = "//input[@id='dob']";
    private static final String JURISDICTION_SELECT_XPATH = "//select[@id='ddlState']";
    private static final String COUNTY_SELECT_XPATH = "//select[@id='ddlCounty']";
    private static final String SUBMIT_BUTTON_XPATH = "//button[@id='btnSubmit']";
    private static final String WORK_ORDER_ID_XPATH = "//div/h3[contains(text(),'Work Order:')]";

    @FindBy(xpath = LAST_NAME_INPUT_XPATH)
    private TextInput lastNameInput;
    @FindBy(xpath = FIRST_NAME_INPUT_XPATH)
    private TextInput firstNameInput;
    @FindBy(xpath = MIDDLE_NAME_INPUT_XPATH)
    private TextInput middleNameInput;
    @FindBy(xpath = DOB_INPUT_XPATH)
    private TextInput dateOfBirthInput;
    @FindBy(xpath = JURISDICTION_SELECT_XPATH)
    private Select jurisdiction;
    @FindBy(xpath = COUNTY_SELECT_XPATH)
    private Select county;
    @FindBy(xpath = SUBMIT_BUTTON_XPATH)
    private Button submit;
    @FindBy(xpath = WORK_ORDER_ID_XPATH)
    private Text workOrder;


    public void enterLastName(String lastName) {
        if (lastName != null) {
            lastNameInput.sendKeys(lastName);
        }
    }

    public void enterFirstName(String firstName) {
        if (firstName != null) {
            firstNameInput.sendKeys(firstName);
        }
    }

    public void enterMiddleName(String middleName) {
        if (middleName != null) {
            middleNameInput.sendKeys(middleName);
        }
    }

    public void enterDateOfBirth(String dateOfBirth) {
        if (dateOfBirth != null) {
            dateOfBirthInput.cleanAndType(dateOfBirth);
        }
    }

    public void selectJurisdiction(String jurid) {
        if (jurid != null) {
            jurisdiction.selectByValue(jurid);
        }
    }

    public void selectCounty(String county) {
        this.county.getFirstSelectedOption().click();
        if (county != null) {
            this.county.selectByVisibleText(county);
        }

    }

    public void clickSubmitJob() {
        submit.click();
    }

    public String getWorkOrderNumber() {
        return workOrder.getText().replaceAll("(Work Order:\\s+)", "");
    }
}
