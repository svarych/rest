package api2.rpz;

import api2.models.counterparties.CreateContactPerson;
import api2.models.internetDocument.CreateEW;
import api2.models.internetDocument.DeleteEW;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50685 for RPZ-51634
 */

class RPZ_52268_48695 {

    private static List<String> ewToDelete = new ArrayList<>();

    private Model model;

    /**
     * RPZ-52268
     */
    @ParameterizedTest
    @ValueSource(strings =
            {
                    //
                    "12345"
            })
    @DisplayName("Phones should be unique")
    void namesTest(String name) throws IOException {
        model = new ModelBuilder().build();
        System.out.println(name);
    }

    @AfterAll
    static void deleteCreatedEWs() throws IOException {
        new DeleteEW().deleteEW(ewToDelete)
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .build().printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse()
        ;
    }

    String clean(String s) {
        return s.replace("\"", "");
    }
}
