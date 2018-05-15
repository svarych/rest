package api2.rpz;

import api2.models.redirecting.CreateRedirectingRequest;
import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50685 for RPZ-51634
 */

class RPZ_51634 {

    private Model model;

    @Test
    @DisplayName("AdditionalServiceGeneral для orderRedirecting добавить проверку...")
    void t0() throws IOException {
        model = new CreateRedirectingRequest(CreateRedirectingRequest.Target.ADDRESS).build().printPrettyRequest();
        model = new CreateRedirectingRequest(CreateRedirectingRequest.Target.WAREHOUSE).build().printPrettyRequest();
    }
}
