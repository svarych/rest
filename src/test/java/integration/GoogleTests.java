package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTests {

    @BeforeEach
    void setUp() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browser = "phantomjs";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.reportsFolder = "./target/surefire-reports";
    }

    @Test
    void googleTestShouldBeGreen() {
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }

    @Test
    void googleTestShouldBeRed() {
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Какие преимущества даёт Selenide"));
    }

}
