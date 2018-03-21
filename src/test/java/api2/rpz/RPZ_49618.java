package api2.rpz;

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

class RPZ_49618 {

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
                .sender("505b97d8-46cd-11e7-9a04-0025909b4e32")
                .citySender("db5c88c6-391c-11dd-90d9-001a92567626")
                .addressSender("7b422fbf-e1b8-11e3-8c4a-0050568002cf")
                .contactSender("01bbc477-b8b0-11e7-af9a-005056886752")
                .phoneSender("380674071624")

                .recipient("0c38cdbe-84b9-11e6-af9a-005056886752")
                .cityRecipient("8d5a980d-391c-11dd-90d9-001a92567626")
                .addressRecipient("5b59eba5-10c6-11e8-ad11-005056886752")
                .contactRecipient("048751cf-0861-11e6-bbb6-005056886752")
                .phoneRecipient("380991234567")

                .addProperty("BackwardDeliveryData", list)

                .apiKey(new Helper().getApiKeyCorporateTest())
                .build()
                .printPrettyRequest()
                .run(TEST).printPrettyResponse()
        ;
    }
}
