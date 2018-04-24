package integration;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleTests {

    @BeforeAll
    static void setUpBrowser(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.reportsFolder="./target/surefire-reports";
    }

    @Test
    void googleTestShouldBeGreen(){
        open("http://google.com");
        $(".gsfi").setValue("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Selenide: лаконичные и стабильные UI тесты на Java"));
    }

    @Test
    void googleTestShouldBeRed(){
        open("http://google.com");
        $(".gsfi").setValue("selenide").pressEnter();
        $$(".g").get(0).shouldHave(text("Какие преимущества даёт Selenide"));
    }

}
