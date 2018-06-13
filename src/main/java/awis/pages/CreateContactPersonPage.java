package awis.pages;

import api2.models.counterparties.CreateContactPerson;
import awis.pages.enums.CardType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CreateContactPersonPage {

    public CreateContactPersonPage() {
    }

    public CreateContactPersonPage lastName(String lastName){
        $(byXpath("(//label[.='Прізвище:']/..//input)")).setValue(lastName);
        return this;
    }

    public CreateContactPersonPage firstName(String firstName){
        $(byXpath("(//div[contains(@id,'FirstName')][contains(@id,'trigger')]/div[1])")).click();
        $(byXpath("(//div[@role='listbox'])")).should(Condition.appears);
        $(byXpath("(//div[contains(@id,'FirstName')][contains(@id,'trigger')]/../input)")).sendKeys(firstName);
        return this;
    }

    public CreateContactPersonPage middleName(String middleName){
        $(byXpath("(//div[contains(@id,'MiddleName')][contains(@id,'trigger')]/div[1])")).click();
        return this;
    }

    public CreateContactPersonPage phone(String lastName){
        $(byXpath("(//label[.='Телефон +:']/..//input)")).setValue(lastName);
        return this;
    }





















    public CreateContactPersonPage newCounterParty(){
        SelenideElement buttonNew = $(byXpath("(//label[.='Контрагент:']/..//input/../div/div[1])"));
        buttonNew.click();
        buttonNew.shouldBe(Condition.disappear);

        SelenideElement activeTab = $(byXpath("(//a[@class='x-tab-close-btn'])[last()]"));
        activeTab.click();
        activeTab.should(Condition.disappear);

        $(byXpath("//span[.='Контрагенти']")).should(Condition.appear);
        $(byXpath("//span[.='Створити']")).shouldBe(Condition.visible).click();

        return this;
    }
}
