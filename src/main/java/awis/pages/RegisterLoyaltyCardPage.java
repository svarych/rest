package awis.pages;

import awis.pages.enums.CardType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegisterLoyaltyCardPage {

    public RegisterLoyaltyCardPage(String number) {
        $(byName("Code")).setValue(number).pressTab();
    }

    public RegisterLoyaltyCardPage type(CardType type) {
        $(byXpath("(//label[.='Тип карти лояльності:']/..//input)[2]")).click();

        if (type == CardType.Ccard) {
            $(byXpath("//ul/li[.='Ccard']")).click();
        }
        if (type == CardType.Indentification) {
            $(byXpath("//ul/li[.='Identification']")).click();
        }

        return this;
    }

    public RegisterLoyaltyCardPage newCounterParty(){
        SelenideElement buttonNew = $(byXpath("(//label[.='Контрагент:']/..//input/../div/div[1])"));
        buttonNew.click();
        buttonNew.shouldBe(Condition.disappear);

        SelenideElement activeTab = $(byXpath("//span[.='Пошук за номером телефона/кодом ЄДРПОУ']/../../../a"));
        activeTab.click();
        activeTab.should(Condition.disappear);

        $(byXpath("//span[.='Контрагенти']")).should(Condition.appear);
        $(byXpath("//span[.='Створити']")).shouldBe(Condition.visible).click();

        return this;
    }
}
