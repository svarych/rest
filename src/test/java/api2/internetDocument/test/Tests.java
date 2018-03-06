package api2.internetDocument.test;

import api2.models.internetDocument.CreateEW;
import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static api2.service.enums.Server.TEST;

class Tests {

    private Model model;

    @Test
    @DisplayName("Получение списка карт")
    void getCardListTest() throws IOException {
        model = new ModelBuilder()
                .apiKey(new Helper().getApiKeyLoyaltyTest2())
                .modelName("Payment")
                .calledMethod("getCards")
                .build()
                .printPrettyRequest()
                .run(TEST)
                .printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Создать экспресс-накладную с возвратом на карту")
    void createEWWithCashBackToCardTest() throws IOException {

        LinkedHashMap<String, Object> moneyInfo = new LinkedHashMap<>();
        moneyInfo.put("PayerType", "Recipient");
        moneyInfo.put("CargoType", "Money");
        moneyInfo.put("RedeliveryString", "45");
        moneyInfo.put("PaymentCard", "035d4aed-3644-11e7-af9a-005056886752");

        ArrayList<Object> list = new ArrayList<>();
        list.add(moneyInfo);

        model = new CreateEW()
                .sender("")
                .citySender("")
                .addressSender("")
                .contactSender("")
                .phoneSender("0999999999")

                .recipient("")
                .cityRecipient("")
                .addressRecipient("")
                .contactRecipient("")
                .phoneRecipient("0777777777")

                .addProperty("BackwardDeliveryData", list)

                .apiKey(new Helper().getApiKeyCorporateTest())
                .build()
                .printPrettyRequest()
                .run(TEST).printPrettyResponse()
        ;
    }
}
