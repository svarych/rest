package api2.TOST.internetDocument;

import api2.models.internetDocument.CreateEW;
import api2.service.Helper;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api2.service.enums.Server.TEST;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    @Test
    @DisplayName("Create EW")
    void createEWTest() throws Throwable {
        model = new CreateEW()
                .sender("6e9acced-d072-11e3-95eb-0050568046cd")
                .citySender("8d5a980d-391c-11dd-90d9-001a92567626")
                .addressSender("2c2c0937-1234-11e8-ad11-005056886752")
                .contactSender("15adafe4-d5b5-11e3-95eb-0050568046cd")

                .recipient("0c38cdbe-84b9-11e6-af9a-005056886752")
                .cityRecipient("8d5a980d-391c-11dd-90d9-001a92567626")
                .addressRecipient("1ec5085c-1c8e-11e8-ad11-005056886752")
                .contactRecipient("20299c77-2b76-11e8-ad11-005056886752")
                .phoneRecipient("380777777777")

                .apiKey(new Helper().getApiKeyCorporateTest())
                .build()
                .printPrettyRequest().run(TEST).printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }
}
