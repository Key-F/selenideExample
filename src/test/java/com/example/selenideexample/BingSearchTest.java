package com.example.selenideexample;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {
    MainPage mainPage;
    ResultsPage2 resultsPage;

    private final String input = "Selenium";

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        mainPage = page();
        resultsPage = page();
        open("https://www.bing.com");
    }

    @Test
    public void searchResultTest() {
        mainPage.sendText(input);
        assertEquals(input, resultsPage.getTextFromSearchField(), "текст не совпал");
    }

    @Test
    public void testResultsField() {
        String expected = "https://www.selenium.dev/";

        mainPage.sendText(input);
        resultsPage.goToUrl(0, input);
        assertEquals(expected, resultsPage.getCurrentUrlOfResult(input), "url не тот");
    }
}
