package api2.LIVE.registry;

import api2.models.registry.GetRegistryList;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api2.service.enums.UserType.*;
import static org.junit.jupiter.api.Assertions.*;

class Tests {

    private Model model;

    @Test
    @DisplayName("Registry Ref for corporate (LIVE)")
    void printRegistryRefCorporateLive() throws IOException {
        model = new GetRegistryList().user(CORPORATE_LIVE).build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Registry Ref for loyalty (LIVE)")
    void printRegistryRefLoyaltyLive() throws IOException {
        model = new GetRegistryList().user(LOYALTY_LIVE).build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

}
