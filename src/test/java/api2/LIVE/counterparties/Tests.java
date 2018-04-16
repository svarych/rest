package api2.LIVE.counterparties;

import api2.models.counterparties.CreateContactPerson;
import api2.models.counterparties.CreateCounterParty;
import api2.models.counterparties.CreateCounterPartyWithCP;
import api2.service.Helper;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    /**
     * Створення контрагента-приватної особи та отримувача (для корпората)
     */
    @Test
    @DisplayName("Create counterparty with contact person for corporate user")
    void createContactPersonCorporateTest() throws IOException {
        model = new CreateCounterPartyWithCP().apiKey(new Helper().getApiKeyCorporateLive()).build().run();
        assertTrue(model.getResponse().findValue("Description").toString().contains("Приватна особа"));
    }

    /**
     * Створення контрагента-приватної особи та отримувача (для лояльника)
     */
    @Test
    @DisplayName("Create counterparty with contact person for loyalty user")
    void createContactPersonLoyaltyTest() throws IOException {
        model = new CreateCounterPartyWithCP().apiKey(new Helper().getApiKeyLoyaltyLive()).build().run();
        assertTrue(model.getResponse().findValue("Description").toString().contains("Приватна особа"));
    }

    @Test
    @DisplayName("Create contact person for private person counterparty LIVE")
    void createContactPersonTest() throws IOException {
        model = new CreateContactPerson().apiKey(new Helper().getApiKeyLoyaltyLive()).build().run();
    }

    @Test
    @DisplayName("Create counterparty Organization")
    void createCounterpartyOrganization() throws IOException {
        model = new CreateCounterParty().build().run();
        assertEquals("\"Супер фірма\"", model.getResponse().findValue("Description").toString());
    }
}
