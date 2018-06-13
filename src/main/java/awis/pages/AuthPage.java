package awis.pages;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    public AuthPage() {
    }

    public AuthPage login(String login) {
        $(byName("user")).setValue(login);
        return this;
    }

    public AuthPage password(String password) {
        $(byName("password")).setValue(password);
        return this;
    }

    public AuthPage submit() {
        $(byXpath("//span[.='Вхід']")).click();
        return null;
    }

}
