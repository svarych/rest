package api2.rpz;

import api2.models.counterparties.CreateContactPerson;
import api2.models.internetDocument.CreateEW;
import api2.models.internetDocument.DeleteEW;
import api2.service.Model;
import api2.service.enums.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50685 for RPZ-51634
 */

class RPZ_52338_51920 {

    private static List<String> ewToDelete = new ArrayList<>();

    private Model model;

    /**
     * RPZ-52338
     */
    @ParameterizedTest
    @ValueSource(strings =
            {
                    // Positive # Поле може містити лише літери, апостроф, дефіс, пробіли;
                    // (Латиниця дозволена для певного переліку контрагентів і тільки при доставці на Відділення)
                    "Василь",
                    "Дем'ян",
                    "Марко-поло",
                    "Іван Чай",

                    // Negative (doc) # встановити заборону на можливість вказання слів «підпис», «агент»,
                    // «представник», «представитель», «неопр», «контакт», «керівник», «ФОП», «ТОВ», «канцелярія»,
                    // «канцелярия» «ПАТ», «АТ», «СПД», «доверенность», «довіреність»
                    "підпис",
                    "агент",
                    "представник",
                    "представитель",
                    "неопр",
                    "контакт",
                    "керівник",
                    "ФОП",
                    "ТОВ",
                    "канцелярія",
                    "канцелярия",
                    "ПАТ",
                    "АТ",
                    "СПД",
                    "доверенность",
                    "довіреність",

                    // Negative (doc) # слова не повинні починатися або закінчуватися на дефіс, апостроф
                    "-Дефіс",
                    "Дефіс-",
                    "'Апостроф",
                    "Апостроф'",

                    // Negative (doc) # Не повинно містити подвійні пробіли, апострофи, дефіси
                    "Подвійний  Пробіл",
                    "Подвійний''апостроф",
                    "Подвійний--дефіс",

                    // Negative (doc) # Не повинно містити потрійні літери
                    "Потрррійний",

                    // Negative (exploration)
                    "довереность", // 1 літера 'н'
                    "12345"
            })
    @DisplayName("Names should be correct")
    void namesTest(String name) throws IOException {
        model = new CreateContactPerson()
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .addProperty("CityRef", "db5c88f5-391c-11dd-90d9-001a92567626")
                .replaceProperty("CounterpartyRef", "481dbd47-6c60-11e7-af9a-005056886752")
                .replaceProperty("FirstName", name)
                .replaceProperty("MiddleName", "Тостович")
                .replaceProperty("LastName", "Тестер")
                .replaceProperty("Phone", "0771111111")
                .replaceProperty("Email", "1@2.com")
                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();

        String ref = model.getResponse().findValue("Ref").toString().replace("\"", "");

        LinkedHashMap<String, Object> backwardDeliveryDataMap = new LinkedHashMap<>();
        backwardDeliveryDataMap.put("PayerType", "Sender");
        backwardDeliveryDataMap.put("CargoType", "Money");
        backwardDeliveryDataMap.put("RedeliveryString", "333");

        List<Object> backwardDeliveryData = new ArrayList<>();
        backwardDeliveryData.add(backwardDeliveryDataMap);

        // Строка повинна містити мінімум 2 слова
        assertTrue(model.getResponse().findValue("Description").toString().split(" ").length >= 2);

        model = new CreateEW()
                .sender("47dd5d18-6c60-11e7-af9a-005056886752")
                .citySender("8d5a980d-391c-11dd-90d9-001a92567626")
                .addressSender("1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                .contactSender("480d1f06-6c60-11e7-af9a-005056886752")
                .phoneSender("0677777777")

                .recipient("481dbd47-6c60-11e7-af9a-005056886752")
                .cityRecipient("db5c88f5-391c-11dd-90d9-001a92567626")
                .addressRecipient("2ab02a87-4de1-11e8-ad11-005056886752")
                .contactRecipient(ref)
                .phoneRecipient("0688888888")

                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .addProperty("BackwardDeliveryData", backwardDeliveryData)
                .replaceProperty("PayerType", "Sender")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();

        assertTrue(model.getResponse().get("success").asBoolean());
        ewToDelete.add(clean(model.getResponse().findValue("Ref").toString()));
    }

    @ParameterizedTest
    @ValueSource(strings =
            {
                    // Телефон Відправника повинен бути мобільним
                    "050",
                    "063",
                    "066",
                    "067",
                    "068",
                    "073",
                    "089",
                    "091",
                    "092",
                    "093",
                    "094",
                    "095",
                    "096",
                    "097",
                    "098",
                    "099"
            })
    @DisplayName("Phone should be mobile")
    void phoneNumbersTest(String code) throws IOException {

        LinkedHashMap<String, Object> backwardDeliveryDataMap = new LinkedHashMap<>();
        backwardDeliveryDataMap.put("PayerType", "Sender");
        backwardDeliveryDataMap.put("CargoType", "Money");
        backwardDeliveryDataMap.put("RedeliveryString", "333");
        List<Object> backwardDeliveryData = new ArrayList<>();
        backwardDeliveryData.add(backwardDeliveryDataMap);

        model = new CreateEW()
                .sender("47dd5d18-6c60-11e7-af9a-005056886752")
                .citySender("8d5a980d-391c-11dd-90d9-001a92567626")
                .addressSender("1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                .contactSender("480d1f06-6c60-11e7-af9a-005056886752")
                .phoneSender("0688888888")

                .recipient("481dbd47-6c60-11e7-af9a-005056886752")
                .cityRecipient("db5c88f5-391c-11dd-90d9-001a92567626")
                .addressRecipient("2ab02a87-4de1-11e8-ad11-005056886752")
                .contactRecipient("f4fae6ac-583b-11e8-ad11-005056886752")
                .phoneRecipient(code + "7777777")

                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .addProperty("BackwardDeliveryData", backwardDeliveryData)
                .replaceProperty("PayerType", "Sender")

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();

        assertTrue(model.getResponse().get("success").asBoolean());
        ewToDelete.add(clean(model.getResponse().findValue("Ref").toString()));
    }

    @AfterAll
    static void deleteCreatedEWs() throws IOException {
        new DeleteEW().deleteEW(ewToDelete)
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .build().printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse()
        ;
    }

    String clean(String s) {
        return s.replace("\"", "");
    }
}
