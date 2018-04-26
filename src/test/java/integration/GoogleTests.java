package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.junit.ScreenShooter;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTests {

    // TODO реализовать отображение скриншотов в Allure
    @BeforeEach
    void setUp() {
        System.setProperty("junit.jupiter.extensions.autodetection.enabled", "true");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

//    @BeforeAll
//    static void setUpBrowser() {
//        Configuration.browser = "phantomjs";
//        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
////        Configuration.reportsFolder = "./target/surefire-reports";
//    }
//
//    @Test
//    void googleTestShouldBeGreen() {
//        open("http://google.com");
//        $(byName("q")).val("selenide").pressEnter();
//        $$(".g").get(0).shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
//    }
//
//    @Test
//    void googleTestShouldBeRed() {
//        open("http://google.com");
//        $(byName("q")).val("selenide").pressEnter();
//        $$(".g").get(0).shouldHave(text("Какие преимущества даёт Selenide"));
//    }

    @Test
    @Step
    @Attachment(type = "image/png")
    void t0() {
        Configuration.browser = "phantomjs";
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
//        $$(".g").get(0).shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
        $$(".g").get(0).shouldHave(text("Что-то пошло не так"));
//        ScreenShooter.failedTests().to("./allure-report/screenshots");
    }
}
