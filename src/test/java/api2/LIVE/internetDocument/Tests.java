package api2.LIVE.internetDocument;

import api2.models.internetDocument.CreateEW;
import api2.models.internetDocument.DeleteEW;
import api2.models.internetDocument.GetListEW;
import api2.service.Model;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    /**
     * Готуємо тестові дані: створюємо ЕН'ки
     */
    @BeforeAll
    /*Create EWs to tests*/
    static void createEWsForTests() throws IOException {
        for (int i = 0; i < 5; i++) {
            new CreateEW().build().run();
        }
    }

    @Test
    void createEWs() throws IOException {
        new CreateEW().build().printPrettyRequest().run().printPrettyResponse();
    }

    /**
     * При першому проході, переконуємось, що коректно отримали список ЕН на видалення)
     * При другому проході, видаляємо список ЕН
     */
    @RepeatedTest(2)
    @DisplayName("Get EW Ref`s to delete and then delete it")
    void deleteEW(RepetitionInfo info) throws IOException {
        // Get list of Ref`s to delete
        if (info.getCurrentRepetition() == 1) {
            model = new DeleteEW().deleteAllToday().build();
            assertTrue(model.getRequestNode().findValues("DocumentRefs").get(0).size() > 0);
        }
        // Deleting EW`s by Ref`s we get on repetition 1
        if (info.getCurrentRepetition() == 2) {
            model = new DeleteEW().deleteAllToday().build().run();
            assertTrue(model.getResponse().findValues("Ref").size() > 0);
        }
    }

    @Test
    @DisplayName("Create EW")
    void createEWTest() throws Throwable {
        model = new CreateEW().build().printPrettyRequest().run().printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Get list of EW`s")
    void getEWListTest() throws IOException {
        model = new GetListEW().getTodayList().build().run().printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @AfterAll
    /*Delete all today created EW*/
    static void deleteAllTodayEWsTest() throws IOException {
        Model m = new DeleteEW().deleteAllToday().build().run();
        assertTrue(m.getResponse().get("success").asBoolean());
    }
}
