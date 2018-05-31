package api2.rpz;

import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import api2.service.enums.UserType;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * RPZ-50685 for RPZ-51634
 * Запрет создания переадресации/довоза по ЕН с контролем оплаты
 */

class RPZ_51935_51634 {

    private Model model;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.fastSetValue = true;
    }

    @Test
    void createEWByID() throws Exception {
        // Створюємо інтернет-документ з контролем оплати та запам'ятовуємо його номер
        createPaymentControlledEW();
        String number = clean(model.getResponse().findValue("IntDocNumber").toString());

        // Створюємо експрес-накладну на основі створеного інтернет-документа
        createAwisEWByIDNumber(number);

        // Перевіряємо можливість зробити переадреацію
        checkRedirectPossibility(number);
        Assertions.assertTrue(model.getResponse().get("success").asBoolean());

        // Намагаємося створити переадресацію до дверей (заборонено, при умові що є контроль оплати)
        createRedirectOrder(number);

        // Очікуємо необхідну помилку
        Assertions.assertEquals("Заборонено переадресування на адресу з замовленою послугою Контроль оплати",
                clean(model.getResponse().get("errors").get(0).toString()));
    }

    private void createAwisEWByIDNumber(String number) throws Exception {
        open("http://wis14.np.ua/ULKTest");
        $(byName("user")).setValue("svarych.a");
        $(byName("password")).setValue("123").pressEnter();

        $(byText("Документи")).click();
        $(byText("Інтернет-документи")).click();
        $(byName("NumberFilter")).setValue(number).pressEnter();

        int time = 0;

        for (int i = 0; i < 10; i++) {
            if ($(byText(number)).isDisplayed()) break;
            else $(byAttribute("data-qtip", "Освіжити")).click();
            try {
                $(byText(number)).waitUntil(Condition.visible, 5000);
            } catch (ElementNotFound notFound) {
                time += 5;
                $(byAttribute("data-qtip", "Освіжити")).click();
            }
        }

        if ($(byText(number)).isDisplayed()) {
            $(byText("Документи")).click();
            $(byText("Експрес-накладна")).click();
            $(byText("Створити")).click();
            $(byName("Number")).setValue(number).pressEnter();

            $(byText("Записати")).click();
            confirm();
            $(byText("OK")).click();
        } else throw new Exception("Не промігрували дані з веба в Авіс за " + time + " секунд");

    }

    private void createPaymentControlledEW() throws IOException {
        model = new ModelBuilder()
                .apiKey("88480f5e25e304a397605165c6f12d7c")
                .modelName("InternetDocument")
                .calledMethod("save")

                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("DateTime", new Helper().getToday())

                .addProperty("CargoType", "Documents")
                .addProperty("ServiceType", "DoorsWarehouse")
                .addProperty("Weight", "0.1")
                .addProperty("SeatsAmount", "1")
                .addProperty("Cost", "100")
                .addProperty("Description", "Документи")
                .addProperty("AfterpaymentOnGoodsCost", "100")

                .addProperty("CitySender", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Sender", "505b97d8-46cd-11e7-9a04-0025909b4e32")
                .addProperty("SenderAddress", "e3c29a1f-1c94-11e8-ad11-005056886752")
                .addProperty("ContactSender", "01bbc477-b8b0-11e7-af9a-005056886752")
                .addProperty("SendersPhone", "380674071624")


                .addProperty("CityRecipient", "8d5a980d-391c-11dd-90d9-001a92567626")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752")
                .addProperty("RecipientAddress", "1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                .addProperty("ContactRecipient", "18e8dda5-d5c5-11e3-95eb-0050568046cd")
                .addProperty("RecipientsPhone", "380504040404")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse()
        ;
    }

    private void checkRedirectPossibility(String docNumber) throws IOException {
        model = new ModelBuilder()
                .apiKey(new Helper().getApiKey(UserType.CORPORATE, "test_tech01", "123456", Server.TEST))
                .modelName("AdditionalServiceGeneral")
                .calledMethod("checkPossibilityForRedirecting")
                .addProperty("Number", docNumber)
                .build()
                .printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse()
        ;
    }

    private void createRedirectOrder(String docNumber) throws IOException {
        model = new ModelBuilder()
                .apiKey("88480f5e25e304a397605165c6f12d7c")
                .modelName("AdditionalServiceGeneral")
                .calledMethod("save")

                .addProperty("OrderType", "orderRedirecting")
                .addProperty("IntDocNumber", docNumber)
                .addProperty("Customer", "Recipient")
                .addProperty("ServiceType", "WarehouseDoors")

                .addProperty("RecipientSettlement", "e718a680-4b33-11e4-ab6d-005056801329")
                .addProperty("RecipientSettlementStreet", "0000a6ca-684e-11e6-8304-00505688561d")
                .addProperty("BuildingNumber", "4")
                .addProperty("NoteAddressRecipient", "")

                .addProperty("Recipient", "d558bfcb-7456-11df-ad52-000c29118aa7")
                .addProperty("RecipientContactName", "Флюгер Петро")
                .addProperty("RecipientPhone", "380504040404")

                .addProperty("PayerType", "Recipient")
                .addProperty("PaymentMethod", "Cash")
                .addProperty("Note", "Причина переадресации")
                .build().printPrettyRequest().run(Server.TEST).printPrettyResponse()
        ;
    }

    private String clean(String s) {
        return s.replace("\"", "");
    }
}
