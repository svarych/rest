package awis.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class FindCounterPartyPage {

    public FindCounterPartyPage() {
        SelenideElement buttonNew = $(byXpath("(//label[.='Контрагент:']/..//input/../div/div[1])"));
        buttonNew.click();
        buttonNew.shouldBe(Condition.disappear);

        SelenideElement activeTab = $(byXpath("//span[.='Пошук за номером телефона/кодом ЄДРПОУ']/../../../a"));
        activeTab.shouldBe(Condition.appears);
    }

    public FindCounterPartyPage edrpou(String edrpou) {
        $(byXpath("(//label[.='ЄДРПОУ:']/..//input)")).setValue(edrpou).pressTab();
        return this;
    }

    public FindCounterPartyPage phone(String phone) {
        $(byXpath("(//label[.='Номер:']/..//input)")).setValue(phone).pressTab();
        return this;
    }

    public FindCounterPartyPage person(String val) {
        $(byXpath("(//label[.='Конт. особа:']/..//input)")).setValue(val).pressTab();
        return this;
    }

    public FindCounterPartyPage name(String name) {
        $(byXpath("(//label[.='Контрагент:']/..//input)")).setValue(name).pressTab();
        return this;
    }

    public FindCounterPartyPage submit() {
        $(byXpath("(//span[.='Обрати']")).shouldBe(Condition.visible).click();
        return this;
    }
}
