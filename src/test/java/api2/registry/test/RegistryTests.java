package api2.registry.test;

import api2.models.registry.GetRegistryList;
import api2.service.Model;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static api2.service.enums.UserType.*;

class RegistryTests {

    private Model model;

    @Test
    @DisplayName("Registry Ref for corporate (live)")
    void printRegistryRefCorporateLive() throws IOException {
        model = new GetRegistryList().user(CORPORATE_LIVE).build().printPrettyRequest();
    }

    @Test
    @DisplayName("Registry Ref for loyalty (live)")
    void printRegistryRefLoyaltyLive() throws IOException {
        model = new GetRegistryList().user(LOYALTY_LIVE).build().printPrettyRequest();
    }

    @Test
    @DisplayName("Registry Ref for corporate (test)")
    void printRegistryRefCorporateTest() throws IOException {
        model = new GetRegistryList().user(CORPORATE_TEST).build().printPrettyRequest();
        model.run(Server.TEST).printPrettyResponse();
        System.out.println(model.getResponse().findValue("Ref"));
    }

    @Test
    @DisplayName("Registry Ref for loyalty (test)")
    void printRegistryRefLoyaltyTest() throws IOException {
        model = new GetRegistryList().user(LOYALTY_TEST).build().printPrettyRequest();

    }

}
