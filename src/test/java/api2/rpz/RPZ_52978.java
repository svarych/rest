package api2.rpz;

import api2.service.DataBase;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import awis.pages.CreateContactPersonPage;
import awis.pages.NewLoyaltyCardPage;
import awis.pages.MainPage;
import awis.pages.RegisterLoyaltyCardPage;
import awis.pages.enums.CardType;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webclient.UserType;
import webclient.pages.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

/**
 * RPZ-50685 for RPZ-52978 - "Вход в МП клиентов с картой клиента выданой через Авис"
 */

class RPZ_52978 {

    private Model model;

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browser = "chrome";
        Configuration.fastSetValue = true;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    @DisplayName("Easy Register")
    void easyRegisterRequest() throws IOException, SQLException, ParseException {

        String phone = "0270000005";

//        registerUserOnSite(phone, phone + "@novaposhta.ua");

        model = new ModelBuilder()
                .system("MobileApp")
                .modelName("LoyaltyUser")
                .calledMethod("registrationLoyaltyUserByPhone")

                .addProperty("Login", "38" + phone)
                .addProperty("Password", "password")

                .build()
                .printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse();
        // TODO: Ищем карту лояльности


        // TODO: Если карта есть, но по рефу нет ЛК - сздаем випа+вип-юзера по упрощенной схеме, запросом:
        model = new ModelBuilder()
                .system("MobileApp")
                .modelName("LoyaltyUser")
                .calledMethod("activationLoyaltyUserByPhone")

                .addProperty("VerificationCode", activationCodeForPhone("38" + phone))
                .addProperty("Phone", "38" + phone)

                .build().printPrettyRequest().run(Server.TEST).printPrettyResponse();
    }

//======================================================================================================================

    private void registerUserOnSite(String phone, String email) throws ParseException, SQLException {
        new RegisterPage(webclient.Server.TEST, UserType.LOYALTY)
                .city("Київ")
                .lastName("Юзер")
                .firstName("Номер")
                .middleName("Два")
                .phone(phone)
                .email(email)
                .birthDay("30.09.1989")
                .confirmPersonalDataUsage().acceptRules().submit();

        new RegisterConfirmPage().enterCode(activationCodeForPhone("38" + phone)).submit();
        new ActivationPage();
        new SetPasswordPage("user" + lastChar(phone)).submit();
        new AuthPage().userType(UserType.LOYALTY).login(email).password("user" + lastChar(phone)).submit();
    }

//======================================================================================================================

    @Test
    void registerCardOnAwis() throws ParseException, SQLException {
        String cardNumber = "10000009";
        open("http://wis14.np.ua/ULKTest");
        new awis.pages.AuthPage().login("homenko.a").password("123").submit();

        // Видача
//        new MainPage()
//                .click("Обробки")
//                .hover("Лояльність")
//                .click("Видача карти лояльності");
//        new NewLoyaltyCardPage()
//                .number(cardNumber)
//                .write();

        // Реєстрація
        new MainPage()
                .click("Обробки")
                .hover("Лояльність")
                .click("Реєстрація карти");

        new RegisterLoyaltyCardPage(cardNumber).newCounterParty();

        new CreateContactPersonPage().lastName("Пупкін").phone("380210000001").firstName("п");
    }

//    ------------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("Get all codes for phone")
    void t3() throws SQLException {
        System.out.println(getCodesForPhone("380260000005"));
    }

    private String activationCodeForPhone(String phone) throws SQLException {
        String[] list;
        list = new DataBase()
                .getResponse("SELECT MessageText FROM wfakesms WHERE PhoneNumber = '" + phone + "' ORDER BY DateTime DESC LIMIT 1").get(0).split(" ");

        return list[list.length - 1].trim();
    }

    private List<String> getCodesForPhone(String phone) throws SQLException {
        List<String> codes;
        codes = new DataBase()
                .getResponse("SELECT * FROM wfakesms WHERE PhoneNumber = '" + phone + "' ORDER BY DateTime DESC");
        return codes;
    }

    private String getEmailActivationLink() throws SQLException {
        String page;
        String link;
        page = new DataBase()
                .getResponseX("SELECT MessageContent FROM wemailmessages ORDER BY MessageDate DESC LIMIT 1").toString();

        link = page.split("href=\"")[1].split("</a>")[0].split("\"")[0];

        return link;
    }

    char lastChar(String s) {
        return s.charAt(s.length() - 1);
    }
}
