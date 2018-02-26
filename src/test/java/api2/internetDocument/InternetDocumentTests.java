package api2.internetDocument;

import api2.Model;
import api2.models.internetDocument.CreateEW;
import api2.models.internetDocument.DeleteEW;
import api2.models.internetDocument.GetListEW;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class InternetDocumentTests {

    private Model model;

    public InternetDocumentTests() {
    }

    @RepeatedTest(2)
    void createEWTest() throws Throwable {
        model = new CreateEW().build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    void getEWListTest() throws IOException {
        model = new GetListEW().getTodayList().build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    void deleteAllTodayEWsTest() throws IOException {
        model = new DeleteEW().deleteAllToday().build().printPrettyRequest();
        assertTrue(model.getRequestNode().findValues("DocumentRefs").get(0).size() > 0);
    }
}
