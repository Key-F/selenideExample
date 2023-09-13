package com.example.selenideexample;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ResultsPage {

    @FindBy(css = "#sb_form_q")
    private SelenideElement searchField;

    @FindBy(css = ":not(.b_adUrl) > cite")
    private List<SelenideElement> results;

    public String getTextFromSearchField() {
        String val = searchField.attr("value");
        System.out.println("В строке поиска текста: " + val);
        return val;
    }

    public void goToUrl(int num, String urlText) {
        results.get(num)
                .shouldBe(interactable)
                .shouldHave(text(urlText))
                .click();
        System.out.println("Переход по ссылке результата " + num);
    }

    public String getCurrentUrlOfResult(String partOfUrl) {
        switchTo().window(1);
        webdriver().shouldHave(urlContaining(partOfUrl), Duration.ofSeconds(5));
        return webdriver().driver().getCurrentFrameUrl();
    }
}
