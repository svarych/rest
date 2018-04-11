package api2.counterparties.live;

import api2.models.counterparties.CreateContactPerson;
import api2.models.counterparties.CreateCounterParty;
import api2.models.counterparties.CreateCounterPartyWithCP;
import api2.service.Helper;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    //    @Test
    void printApiKeys() throws IOException {
        System.out.println("Loyalty live api key: " + new Helper().getApiKeyLoyaltyLive());
        System.out.println("Corporate live api key: " + new Helper().getApiKeyCorporateLive());
        System.out.println("Loyalty test api key: " + new Helper().getApiKeyLoyaltyTest());
        System.out.println("Corporate test api key: " + new Helper().getApiKeyCorporateTest());
    }

    /**
     * Створення контрагента-приватної особи та отримувача (для корпората)
     */
    @Test
    @DisplayName("Create counterparty with contact person for corporate user")
    void createContactPersonCorporateTest() throws IOException {
        model = new CreateCounterPartyWithCP().apiKey(new Helper().getApiKeyCorporateLive()).build().printPrettyRequest().run().printPrettyResponse();
        assertTrue(model.getResponse().findValue("Description").toString().contains("Приватна особа"));
    }

    /**
     * Створення контрагента-приватної особи та отримувача (для лояльника)
     */
    @Test
    @DisplayName("Create counterparty with contact person for loyalty user")
    void createContactPersonLoyaltyTest() throws IOException {
        model = new CreateCounterPartyWithCP().apiKey(new Helper().getApiKeyLoyaltyLive()).build().printPrettyRequest().run().printPrettyResponse();
        assertTrue(model.getResponse().findValue("Description").toString().contains("Приватна особа"));
    }

    @Test
    @DisplayName("Create contact person for private person counterparty live")
    void createContactPersonTest() throws IOException {
        model = new CreateContactPerson().apiKey(new Helper().getApiKeyLoyaltyLive()).build().printPrettyRequest().run().printPrettyResponse();
    }

    @Test
    @DisplayName("Create counterparty Organization")
    void createCounterpartyOrganization() throws IOException {
        model = new CreateCounterParty()
//                .printCurrentCity()
//                .city("db5c88f5-391c-11dd-90d9-001a92567626")
//                .printCurrentCity()
                .build()
                .printPrettyRequest()
                .run()
                .printPrettyResponse()

        ;
    }



}
