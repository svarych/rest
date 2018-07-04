package api2.rpz;

import api2.service.DataBase;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * RPZ-52859 - Изменение процесса создания бонусных карт
 */

class RPZ_52859 {

    private Model model;

    private static String phone;
    private static String login;
    private static String password;

    private static String getLogin() {
        return login;
    }

    private static void setLogin(String login) {
        RPZ_52859.login = login;
    }

    private static String getPassword() {
        return password;
    }

    private static void setPassword(String password) {
        RPZ_52859.password = password;
    }

    private static String getPhone() {
        return phone;
    }

    private static void setPhone(String phone) {
        RPZ_52859.phone = phone;
    }

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.fastSetValue = true;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        setPhone("380270000119");
        setLogin("0001" + lastChar(getPhone()) + "@novaposhta.ua");
        setPassword("password");
    }

    @Test
    @DisplayName("Register corporate user without EDRPOU")
    void registerWithoutEDRPOU() throws IOException, SQLException {
        model = new ModelBuilder()
                .system("MobileApp")
                .modelName("LoyaltyUserGeneral")
                .calledMethod("registration")

                .addProperty("City", "Киев")
                .addProperty("FirstName", "Едуард")
                .addProperty("LastName", "Едуардович")
                .addProperty("Patronymic", "Едуардов")
                .addProperty("Phone", getPhone())
                .addProperty("Email", getLogin())
                .addProperty("Password", getPassword())
                .addProperty("CounterpartyType", "Organization")
                .addProperty("OwnerShipForm", "7f0f351d-2519-11df-be9a-000c291af1b3")
                .addProperty("CompanyName", "СП ДП СУПЕР ФІРМА ЕДУАРДА")
//                .addProperty("EDRPOU", "723456713")
                .addProperty("MarketplacePartnerToken", "005056887b8d-8478-11e5-c9ad-fa7dc024")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
        Assertions.assertTrue(model.getResponse().get("success").booleanValue());
        activate();
        Assertions.assertTrue(model.getResponse().get("success").booleanValue());
        open(getLink());
        logIn(getLogin(), getPassword());
        Assertions.assertTrue($("#exit").is(Condition.visible));
    }

    private static String lastChar(String s) {
        return s.substring(s.length() - 1);
    }

    private void activate() throws SQLException, IOException {
        model = new ModelBuilder()
                .system("MobileApp")
                .modelName("LoyaltyUserGeneral")
                .calledMethod("activationLoyalty")

                .addProperty("Code", activationCodeForPhone(getPhone()))
                .addProperty("Phone", getPhone())

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    private void logIn(String login, String password) {
        $(byClassName("logo_in")).click();
        $(byXpath("//div[contains(@class,'tabs-registration')]")).should(Condition.appear);
        $("#LoginForm_username").setValue(login);
        $("#LoginForm_password").setValue(password);
        $(byName("yt0")).click();
    }

    private String getLink() throws SQLException {
        String url = "http://webclient.sb.np.ua/auth/activate/code/";
        String[] linkData = getEmailActivationLink().split("/");
        String link = linkData[linkData.length - 1];

        return url + link;

    }

    private String activationCodeForPhone(String phone) throws SQLException {
        String[] list;
        list = new DataBase()
                .getResponse("SELECT MessageText FROM wfakesms WHERE PhoneNumber = '" + phone + "' ORDER BY DateTime DESC LIMIT 1").get(0).split(" ");

        return list[list.length - 1].trim();
    }

    private String getEmailActivationLink() throws SQLException {
        String page;
        String link;
        page = new DataBase()
                .getResponseX("SELECT MessageContent FROM wemailmessages ORDER BY MessageDate DESC LIMIT 1").toString();

        link = page.split("href=\"")[1].split("</a>")[0].split("\"")[0];

        return link;
    }
}
