package api2.rpz;

import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * RPZ-53537 - Доработка метода getContactPersonsCounterparty, поиск данных
 */

class RPZ_53537 {

    private Model model;

    @Test
    void t0() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .system("PA 3.0")
                .modelName("ContactPersonGeneral")
                .calledMethod("getContactPersonsCounterparty")

                .methodProperty("Page", "1")
                .methodProperty("Limit", "20")
                .methodProperty("ContactProperty", "Recipient")
                .methodProperty("FindByString", "066777")

                .build().printPrettyRequest()
//                .run(Server.TEST).printPrettyResponse()
        ;
    }
}
