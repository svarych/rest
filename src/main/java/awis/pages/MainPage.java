package awis.pages;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public MainPage() {
    }

    public MainPage click(String element) {
        $(byText(element)).click();
        return this;
    }

    public MainPage hover(String element) {
        $(byText(element)).hover();
        return this;
    }
}
