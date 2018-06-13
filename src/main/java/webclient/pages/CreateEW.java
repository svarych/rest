package webclient.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateEW {

    public CreateEW() {
    }

    public CreateEW startCreation() {
        open("http://webclient.sb.np.ua/newOrder/index");
        return this;
    }

    public CreateEW changeSender() {
        $("#SenderSelectButton").click();
        return this;
    }

    public CreateEW setSenderCity(String city) {
        SelenideElement citiesFrame = $("#cities_ul");
        citiesFrame.shouldBe(Condition.appears);
        $(byXpath("//div[@id='filter_journal_cities_div']//i[@title='Очистити фільтр']")).click();
        citiesFrame.shouldBe(Condition.disappear);
        citiesFrame.shouldBe(Condition.appears);

        $(byXpath("(.//*/text()[normalize-space(translate(string(.), '\t ', '')) = '" + city + "']/parent::a)[2]")).click();
        return this;
    }

//    public CreateEW setCounterParty(){}

}
