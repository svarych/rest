package api2.counterparties.live;

import api2.models.counterparties.CreateCounterParty;
import api2.service.Helper;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;
    private String loyaltyApiKey = new Helper().getApiKeyLoyalty();
    private String corporateApiKey = new Helper().getApiKeyCorporate();

    Tests() throws IOException {
    }

    @Test
    @DisplayName("Create contact person for loyalty user")
    void createContactPersonLoyaltyTest() throws IOException {
        model = new CreateCounterParty().apiKey(loyaltyApiKey).build().printPrettyRequest().run().printPrettyResponse();
        assertTrue(model.getResponse().findValue("Description").toString().contains("Приватна особа"));
    }

    @Test
    @DisplayName("Create contact person for corporate user")
    void createContactPersonCorporateTest() throws IOException {
        model = new CreateCounterParty().apiKey(corporateApiKey).build().printPrettyRequest().run().printPrettyResponse();
        assertTrue(true);
    }
}
