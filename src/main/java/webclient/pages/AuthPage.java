package webclient.pages;

import webclient.Server;
import webclient.UserType;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthPage {

    public AuthPage(Server server) {

        if (server == Server.TEST) {
            open("http://webclient.sb.np.ua/auth");
        }
        if (server == Server.LIVE) {
            open("https://novaposhta.ua/auth");
        }
        if (server == Server.MY2) {
            open("https://my2.novaposhta.ua/auth");
        }

        $(".logo_in").click();
    }

    public AuthPage() {
    }

    public AuthPage(Server server, UserType userType) {
        open("http://webclient.sb.np.ua/auth");
        $(".logo_in").click();
        userType(userType);
    }

    public AuthPage(Server server, UserType user, String login, String password) {
        open("http://webclient.sb.np.ua/auth");
        $(".logo_in").click();
        userType(user).login(login).password(password);
    }

    public AuthPage login(String login) {
        $("#LoginForm_username").setValue(login);
        return this;
    }

    public AuthPage password(String password) {
        $("#LoginForm_password").setValue(password);
        return this;
    }

    public AuthPage submit() {
        $(byName("yt0")).click();
        return this;
    }

    public AuthPage userType(UserType userType) {
        if (userType == UserType.CORPORATE) {
            $(byAttribute("data-type", "business")).click();
        }
        if (userType == UserType.LOYALTY) {
            $(byAttribute("data-type", "person")).click();
        }
        return this;
    }
}
