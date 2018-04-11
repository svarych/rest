package api2.rpz;

import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50685 for RPZ-50641
 */

class RPZ_50685 {

    private Model model;

    @Test
    @DisplayName("TrackingDocument.getStatusDocument: Старый метод трекинга. Должен не работать")
    void t0() throws IOException {
        model = new ModelBuilder().apiKey(new Helper().getApiKeyLoyaltyLive())
                .modelName("TrackingDocument")
                .calledMethod("getStatusDocument")
                .build().run(Server.MY2)
        .printPrettyRequest();
        assertTrue(model.getResponse().get("errors").toString().contains("getStatusDocument not found"));
    }

    @Test
    @DisplayName("TrackingDocument.getStatusDocumentsOld: Старый метод трекинга. Должен не работать")
    void t1() throws IOException {
        model = new ModelBuilder().apiKey(new Helper().getApiKeyLoyaltyLive())
                .modelName("TrackingDocument")
                .calledMethod("getStatusDocumentsOld")
                .build().run(Server.MY2)
                .printPrettyRequest();;
        assertTrue(model.getResponse().get("errors").toString().contains("getStatusDocumentsOld not found"));
    }

    @Test
    @DisplayName("InternetDocument.documentsTracking: Старый метод трекинга. Должен не работать")
    void t2() throws IOException {
        model = new ModelBuilder().apiKey(new Helper().getApiKeyLoyaltyLive())
                .modelName("InternetDocument")
                .calledMethod("documentsTracking")
                .build().run(Server.MY2)
                .printPrettyRequest();;
        assertTrue(model.getResponse().get("errors").toString().contains("documentsTracking not found"));
    }


}
