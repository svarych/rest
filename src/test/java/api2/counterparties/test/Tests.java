package api2.counterparties.test;

import api2.models.counterparties.CreateCounterPartyWithCP;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

public class Tests {

    private static Properties properties = new Properties();
    private Model model;

    @Test
    @DisplayName("Create counterparty")
    void createCounterPartyTest() throws IOException {
        model = new CreateCounterPartyWithCP()
                .cityRef("8d5a980d-391c-11dd-90d9-001a92567626")
                .build().printPrettyRequest().run().printPrettyResponse();
    }
}
