package com.example.selenideexample;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;


public class MainPage {
    @FindBy(css = "#sb_form_q")
    private SelenideElement searchField;

    public void sendText(String text) {
        searchField.sendKeys(text);
        System.out.println("Введен текст: " + text);
        searchField.submit();
    }

}
