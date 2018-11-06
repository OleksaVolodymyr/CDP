package com.robot.automation;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.robot.automation.bo.WorkOrderInputPageBO;
import com.robot.automation.bo.WorkOrderLookupPageBO;
import com.robot.automation.model.UATModel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class WorkOrdersTest {

    @BeforeClass
    public void setupClassName(ITestContext context) {
        context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);
        context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
    }

    @Test(dataProvider = "uat")
    public void test(UATModel uatModel) {
        WorkOrderLookupPageBO workOrderLookupPageBO = new WorkOrderLookupPageBO();
        workOrderLookupPageBO.createWorkOrder(uatModel.getBotSearchWorkOrder());
        WorkOrderInputPageBO workOrderInputPageBO = new WorkOrderInputPageBO();
        Assert.assertTrue(workOrderInputPageBO.isWorkOrderCreated(uatModel.getBotSearchWorkOrder()));
        workOrderInputPageBO.enterSearchParameters(uatModel);

    }

    @DataProvider(name = "uat", parallel = true)
    public Iterator<UATModel> getDataFromXML() {
        CsvToBean<UATModel> csvToBean;
        List<UATModel> uat= null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\csv\\TN_Shelby_3.2_Shelby_58478_uat.csv"))) {
            csvToBean =
                    new CsvToBeanBuilder<UATModel>(reader).withType(UATModel.class).withIgnoreLeadingWhiteSpace(true).build();
            uat = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uat.iterator();
    }

}
