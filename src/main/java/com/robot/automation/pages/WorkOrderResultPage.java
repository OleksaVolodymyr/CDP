package com.robot.automation.pages;

import com.robot.automation.control.Link;
import com.robot.automation.control.Text;
import com.robot.automation.model.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkOrderResultPage extends PageObject {

    private static final String RESULT_STATUS_XPATH = "//span[@class='search-label' and contains(text()," +
            "'Status')]/following-sibling::span[1]";
    private static final String RESULT_OUTCOME_XPATH = "//span[@class='search-label' and contains(text(),'Outcome')]" +
            "/following-sibling::span[1]";
    private static final String SUMMARY_IMAGE_LINK_XPATH = "//div[@class='container' and h4[text()='Summary " +
            "Images']]//a[contains(text(),' png')]";
    private static final String CANDIDATE_ROW_XPATH = "//table[@class=' table table-hover']//tr[@class='column-data']";


    @FindBy(xpath = RESULT_STATUS_XPATH)
    private Text resultStatus;

    @FindBy(xpath = RESULT_OUTCOME_XPATH)
    private Text outcomeResults;

    @FindBy(xpath = SUMMARY_IMAGE_LINK_XPATH)
    private List<Link> summaryImages;

    @FindBy(xpath = CANDIDATE_ROW_XPATH)
    private List<WebElement> candidates;


    public boolean isWorkOrderStatusCompleteOrError() {
        return new FluentWait<>(driver).withTimeout(Duration.ofMinutes(10))
                .pollingEvery(Duration.ofSeconds(30))
                .ignoring(NoSuchElementException.class)
                .until(webDriver -> {
                    driver.navigate().refresh();
                    String status = driver.findElement(By.xpath(RESULT_STATUS_XPATH)).getText();
                    return status.equalsIgnoreCase("complete")
                            || status.equalsIgnoreCase("Error");
                });
    }

    public String getWorkOrderStatus() {
        return resultStatus.getText();
    }

    public String getOutcomeStatus() {
        return outcomeResults.getText();
    }

    public void getSummaryImages() {
        summaryImages.forEach(s -> System.out.println(s.getUrl()));
    }

    public int getSummaryImageCount() {
        return summaryImages.size();
    }

    public int getFoundCandidatesCount() {
        return candidates.size();
    }

    public void getAllCandidateData() {
        candidates.forEach(s -> System.out.println(s.getAttribute("innerHTML")));
        //  candidates.forEach(s->s.findElements(By.tagName("td")).stream().filter()
        //forEach(r->System.out.println(r.getText())));
    }

    public int getCountOfCandidateStatus(Result result) {
        return 1;/*(int) candidates.stream().map(s -> s.findElements(By.tagName("td"))).filter(r->!r.getText()
        .equalsIgnoreCase(result.getValue())
                .stream().filter(r -> {
                    System.out.println(r.getText());
                    return !r.getText().equalsIgnoreCase(result.getValue());
                }))
                .collect(Collectors.toList()).size();
    */
    }


}
