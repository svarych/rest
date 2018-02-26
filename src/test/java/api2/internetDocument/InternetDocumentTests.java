package api2.internetDocument;

import api2.Connector;
import api2.Model;
import api2.models.internetDocument.CreateEW;
import api2.models.internetDocument.DeleteEW;
import api2.models.internetDocument.GetListEW;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InternetDocumentTests {

    private Model model;

    @RepeatedTest(2)
    void t00() throws IOException {
        model = new CreateEW().build().run().printPrettyResponse();
        assertTrue(model.getResponse().findValue("Ref").isTextual());

    }

    @Test
    void t01() throws IOException {
        model = new GetListEW().getFullList().getPage(2).build().printPrettyRequest().run().printResponse();
        System.out.println(model.getResponse().findValues("IntDocNumber").size());
    }

    @Test
    void t02() throws IOException {
        model = new GetListEW().getFullList().build().run();
        System.out.println(model.getResponse().findValues("IntDocNumber").size());
    }

    @Test
    void t0() throws IOException {
        model = new DeleteEW().deleteAllToday().build().printPrettyRequest();

    }
}
