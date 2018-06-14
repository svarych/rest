package awis.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CreateContactPersonPage {

    public CreateContactPersonPage() {
    }

    public CreateContactPersonPage lastName(String lastName) {
        $(byXpath("(//label[.='Прізвище:']/..//input)")).setValue(lastName);
        return this;
    }

    public CreateContactPersonPage firstName(String firstName) {
        $(byXpath("(//div[contains(@id,'FirstName')][contains(@id,'trigger')]/div[1])")).click();
        $(byXpath("(//div[@role='listbox'])[last()]")).should(Condition.appears);

        // Ввести перші букви, дочекатись фрейм, вибрати ім'я з фрейму
        $(byXpath("(//div[contains(@id,'FirstName')][contains(@id,'trigger')]/../input)")).setValue(firstName.substring(0, 3));
        $(byXpath("//div[contains(@id, 'boundlist')]//span[.='" + firstName + "']")).shouldBe(Condition.appear).click();

        return this;
    }

    public CreateContactPersonPage middleName(String middleName) {
        $(byXpath("(//div[contains(@id,'MiddleName')][contains(@id,'trigger')]/div[1])")).click();
        $(byXpath("(//div[@role='listbox'])[last()]")).should(Condition.appears);

        // Ввести перші букви, дочекатись фрейм, вибрати ім'я з фрейму
        $(byXpath("(//div[contains(@id,'MiddleName')][contains(@id,'trigger')]/../input)")).setValue(middleName.substring(0, 3));
        $(byXpath("//div[contains(@id, 'boundlist')]//span[.='" + middleName + "']")).shouldBe(Condition.appear).click();
        return this;
    }

    public CreateContactPersonPage phone(String lastName) {
        $(byXpath("(//label[.='Телефон +:']/..//input)")).setValue(lastName);
        return this;
    }

    public CreateContactPersonPage submit() {
        $(byXpath("(//button[contains(@id,'buttonWriteAndClose')])[last()]")).shouldBe(Condition.visible).click();
        return this;
    }
}
