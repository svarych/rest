package api2;

import api2.models.internetDocument.*;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleTests {

    private Model model;

    @Test
    void t000() throws IOException {
        Model model = new ModelBuilder().apiKey("123").modelName("model").calledMethod("method")
                .addProperty("Key0", "Value0")
                .addProperty("Key1", "Value1")
                .build();
        System.out.println(model.getRequest());
        System.out.println(model.getRequestNode().get("apiKey"));

        Connector connector = new Connector();
        connector.prepare().send(model.getRequest());

        System.out.println(connector.getResponse());
        System.out.println(connector.getPrettyResponse());
    }

    @Test
    void t001() throws IOException {
        Model model = new ModelBuilder()
                .apiKey("12345")
                .modelName("superModel")
                .calledMethod("superMethod")
                .addProperty("k0", "v1")
                .addProperty("k1", "v1")

                .build()
                .printRequest()
                .printPrettyRequest()

                .run()
                .printResponse()
                .printPrettyResponse();


        System.out.println(model.getRequestNode().get("apiKey"));
    }

    @Test
    void t002() throws IOException {
        model = new CreateEW().removeProperty("Cost").build().run();
        assertTrue(model.getResponse().get("errors").toString().contains("Cost is empty"));
    }

    @Test
    void t003() throws IOException {
        model = new CreateEW().build().run();
        assert model.getResponse().get("success").asBoolean();
    }

    @Test
    void t004() throws IOException {
        assertTrue(new CreateEW().removeProperty("Cost").build().run().getResponse().get("success").asBoolean());
    }

    @Test
    void t005() throws IOException {
        new CreateEW().build().run();
    }

    @Test
    void t006() throws IOException {
        model = new CreateEW()
                .build().printRequest().run().printResponse();
    }

    @Test
    void t007() {
        Helper h = new Helper();
        System.out.println(h.getToday());
        System.out.println(h.getDaysPlus(2));
        System.out.println(h.getDaysMinus(3));
    }

    @Test
    void t008() throws IOException {
        File text = new File("./src/main/resources/data/x.x");
        FileReader fileReader = new FileReader(text);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    void t009() throws IOException {
        model = new CreateEWBackwardDelivery().build().printPrettyRequest().run().printPrettyResponse();
    }

    @Test
    void t010() throws IOException {
        model = new CreateEWToAddress().apiKey("123").replaceProperty("DateTime", "27.02.2018").build().printPrettyRequest();
    }

    @RepeatedTest(2)
    void t011() throws IOException {
        model = new CreateEW().build().run().printPrettyResponse();
        assertTrue(model.getResponse().findValue("Ref").isTextual());
    }

    @Test
    void t012() throws IOException {
        model = new GetListEW().getFullList().getPage(2).build().printPrettyRequest().run().printResponse();
        System.out.println(model.getResponse().findValues("IntDocNumber").size());
    }

    @Test
    void t013() throws IOException {
        model = new GetListEW().getFullList().build().run();
        System.out.println(model.getResponse().findValues("IntDocNumber").size());
    }

    @Test
    void t14() throws IOException {
        model = new DeleteEW().deleteAllToday().build().printPrettyRequest();

    }
}
