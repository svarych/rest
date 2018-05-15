package api2.LIVE.counterparties;

import api2.models.counterparties.CreateContactPerson;
import api2.models.counterparties.CreateCounterParty;
import api2.models.counterparties.CreateCounterPartyWithCP;
import api2.models.internetDocument.CreateEW;
import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
    @DisplayName("Create counterparty Organization")
    void createCounterpartyOrganization() throws IOException {
        model = new CreateCounterParty().build().run();
        assertEquals("\"Супер фірма\"", model.getResponse().findValue("Description").toString());
    }

    @Test
    @DisplayName("Загрузить список контактных лиц Контрагента")
    void getContactPersonList() throws IOException {
        model = new ModelBuilder()
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .modelName("Counterparty")
                .calledMethod("getCounterpartyContactPersons")
                .addProperty("Ref", "481dbd47-6c60-11e7-af9a-005056886752")
                .build()
                .printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse();
    }

}
