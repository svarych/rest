package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class RulesTests implements AfterEachCallback, BeforeEachCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        ScreenShooter.failedTests().to("./allure-report/screenshots");
        System.out.println("AFTER IS CALLED");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.setProperty("junit.jupiter.extensions.autodetection.enabled", "true");
    }

    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        System.out.println("BEFORE IS CALLED");
    }

    @AfterEach
    void setResult(){
        ScreenShooter.failedTests().to("./allure-report/screenshots");
        System.out.println("AFTER IS CALLED");
    }

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
