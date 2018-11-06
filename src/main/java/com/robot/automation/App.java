package com.robot.automation;

import com.robot.automation.model.Result;
import com.robot.automation.pages.WorkOrderResultPage;
import com.robot.automation.utils.Property;

public class App {
    public static void main(String[] args) {
        //      CsvToBean<UATModel> csvToBean;
//        List<UATModel> uat= null;
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\WorkSpace\\botsearch\\src\\main\\resources" +
//                "\\TN_Shelby_3" +
//                ".2_Shelby_58478_uat.csv"))) {
//            csvToBean =
//                    new CsvToBeanBuilder<UATModel>(reader).withType(UATModel.class).withIgnoreLeadingWhiteSpace(true).build();
//            uat = csvToBean.parse();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        uat.forEach(System.out::println);

//        WebDriver driver = WebDriverPoolFactory.getDriver("CHROME");
//        driver.get(Property.getInstance().getPropertyByKey("Url"));
//        boolean test = new FluentWait<>(driver).withTimeout(Duration.ofMinutes(10))
//                .pollingEvery(Duration.ofSeconds(30))
//                .ignoring(NoSuchElementException.class)
//                .until(webDriver -> {
//                    driver.navigate().refresh();
//                    System.out.println("+");
//                    return driver.findElement(By.xpath("//span[@class=\"search-label\"and contains(text(),\"Status\")" +
//                            "]/following-sibling::span[1]")).getText().equalsIgnoreCase("complete1");
//                });
//
//        System.out.println(test);

        WorkOrderResultPage resultPage = new WorkOrderResultPage();
        resultPage.goTo(Property.getInstance().getPropertyByKey("Url"));
        if (resultPage.isWorkOrderStatusCompleteOrError()) {
            System.out.println(resultPage.getWorkOrderStatus());
        } else System.out.println("Failed");
        System.out.println(resultPage.getOutcomeStatus());
        resultPage.getSummaryImages();
        System.out.println(resultPage.getSummaryImageCount());
        System.out.println(resultPage.getFoundCandidatesCount());
        // resultPage.getAllCandidateData();
        System.out.println(resultPage.getCountOfCandidateStatus(Result.HIT));

    }

}
