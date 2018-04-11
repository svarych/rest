package api2.rpz;

import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * RPZ-50572
 */

class RPZ_50572 {

    private Model model;

    @Test
    @DisplayName("Расширения канала опроса удовлетворённости клиентов через мобильное приложения")
    void mobile0() throws IOException {
        model = new ModelBuilder()
                .modelName("Poll")
                .calledMethod("save")
                .addProperty("Ref", "259c0f00-3286-11e8-ad11-005056886752")

                .build()

                .printPrettyRequest()
                .run(Server.TEST)

                .printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Расширения канала опроса удовлетворённости клиентов через мобильное приложения")
    void mobile1() throws IOException {
        model = new ModelBuilder()
                .modelName("Poll")
                .calledMethod("save")
                .addProperty("Ref", "259c0f00-3286-11e8-ad11-005056886752")

                .build()

                .printPrettyRequest()
                .run(Server.LIVE)

                .printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Расширения канала опроса удовлетворённости клиентов через мобильное приложения")
    void mobile2() throws IOException {
        model = new ModelBuilder()
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .modelName("Common")
                .calledMethod("sendPollAnswer")
                .addProperty("Ref", "259c0f00-3286-11e8-ad11-005056886752")

                .build()

                .printPrettyRequest()
                .run(Server.TEST)

                .printPrettyResponse()
        ;
    }

    @Test
    @DisplayName("Расширения канала опроса удовлетворённости клиентов через мобильное приложения")
    void mobile3() throws IOException {
        model = new ModelBuilder()
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Common")
                .calledMethod("sendPollAnswer")
                .addProperty("Ref", "259c0f00-3286-11e8-ad11-005056886752")

                .build()

                .printPrettyRequest()
                .run(Server.LIVE)

                .printPrettyResponse()
        ;
    }


}
