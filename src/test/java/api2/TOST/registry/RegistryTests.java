package api2.TOST.registry;

import api2.models.registry.GetRegistryList;
import api2.service.Model;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api2.service.enums.UserType.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistryTests {

    private Model model;

    @Test
    @DisplayName("Registry Ref for corporate (TOST)")
    void printRegistryRefCorporateTest() throws IOException {
        model = new GetRegistryList().user(CORPORATE_TEST).build().run(Server.TEST);
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Registry Ref for loyalty (TOST)")
    void printRegistryRefLoyaltyTest() throws IOException {
        model = new GetRegistryList().user(LOYALTY_TEST).build().run(Server.TEST);
        assertTrue(model.getResponse().get("success").asBoolean());
    }

}
