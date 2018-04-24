package integration;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTests {

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browser = "phantomjs";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.reportsFolder = "./target/surefire-reports";
    }

    @Attachment
    @Test
    void googleTestShouldBeGreen() {
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }

    @Attachment
    @Test
    void googleTestShouldBeRed() {
        open("http://google.com");
        $(byName("q")).val("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Какие преимущества даёт Selenide"));
    }

}
