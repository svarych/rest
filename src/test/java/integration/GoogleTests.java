package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

class GoogleTests {

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browser = "phantomjs";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUp() {
        System.setProperty("junit.jupiter.extensions.autodetection.enabled", "true");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void googleTestShouldBeGreen() {
        open("http://google.com");
        $(byName("q")).val("Нова пошта").pressEnter();
        $$(".g").get(0).shouldHave(text("Весь спектр логістичних послуг"));
    }

    @Test
    void googleTestShouldBeRed() {
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Через цей текст, ПОВИНЕН впати тест ;)"));
    }
}
