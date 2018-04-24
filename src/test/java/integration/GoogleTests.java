package integration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.webdriver.WebDriverFactory;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTests {

    // TODO реализовать отображение скриншотов в Allure
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
