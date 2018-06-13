package awis.pages;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;

public class NewLoyaltyCardPage {

    private String number;

    public String getNumber() {
        return number;
    }

    public NewLoyaltyCardPage() {
    }

    public NewLoyaltyCardPage number(String number) {
        this.number = number;
        $(byName("Code")).setValue(number).pressTab();
        return this;
    }

    public NewLoyaltyCardPage write() {
        Configuration.clickViaJs = true;
        $(byXpath("//em/button/span[.='Записати']/..")).shouldBe(visible).hover().click();
        confirm("Успішно створено карту за номером " + getNumber() + "!");
        return this;
    }


}
