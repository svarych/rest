package api2.rpz;

import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50572
 */

class RPZ_50572 {

    private Model model;

    @Test
    @DisplayName("D  - RPZ-46205 + 50572")
    void commonSendPollAnswer() throws IOException {
        model = new ModelBuilder()
                .apiKey("e810e6e4283d33db4d2f016a7d406e66")
                .modelName("Common")
                .calledMethod("sendPollAnswer")
                .addProperty("Number", "20600000067700")
                .addProperty("Status", "1")
                .addProperty("ClientType", "Sender") // - Не обов'язкове
                .addProperty("Mark", "5") // - Не обов'язкове
                .addProperty("Comment", "Коко Шанель Тестер") // - Не обов'язкове
                .addProperty("Ref", "259c0f00-3286-11e8-ad11-005056886752") //todo RPZ-50572
                .build().printPrettyRequest()
//                .run(Server.TEST).printPrettyRequest().printPrettyResponse()
        ;

        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("InternetDocument->getDocumentForPoll")
    void documentGetDocumentForPoll() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("InternetDocument")
                .calledMethod("getDocumentForPoll")
                .addProperty("Number", "20600000067700")
                .addProperty("ClientType", "Sender")
//                .addProperty("ClientType", "Recipient")
//                .addProperty("Status", "1")
//                .addProperty("Mark", "10")
//                .addProperty("Comment", "Николайченко")

                .build().printPrettyRequest()

                .run(Server.TEST).printPrettyResponse();

        assertTrue(model.getResponse().get("success").asBoolean());
    }

    /**
     * Тут отримаємо REF`ки відгуків клієнтів, які будемо використовувати в тесті Common->sendPollAnswer
     */
    @Test
    @DisplayName("Get Ref`s of user responses (RPZ-50025)")
    void getPollCode() throws IOException {
        model = new ModelBuilder()
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .modelName("Common")
                .calledMethod("getReviewTypes")
                .build()
                .printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }
}
