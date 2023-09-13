package com.example.selenideexample;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ResultsPage2 {

    private static final By SEARCH_FIELD_CSS = By.cssSelector("#sb_form_q");
    private static final By RESULTS_CSS = By.cssSelector(":not(.b_adurl) > cite");

    public String getTextFromSearchField() {
        String val = $(SEARCH_FIELD_CSS).attr("value");
        System.out.println("В строке поиска текста: " + val);
        return val;
    }

    public void goToUrl(int num, String urlText) {
        $$(RESULTS_CSS).get(num)
                .shouldBe(interactable)
                .shouldHave(partialText(urlText))
                .click();
        System.out.println("Переход по ссылке результата " + num);
    }

    public String getCurrentUrlOfResult(String partOfUrl) {
        switchTo().window(1);
        webdriver().shouldHave(urlContaining(partOfUrl.toLowerCase()), Duration.ofSeconds(3));
        return webdriver().driver().getCurrentFrameUrl();
    }
}
