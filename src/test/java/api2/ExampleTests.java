package api2;

import jdk.nashorn.internal.ir.ObjectNode;
import org.junit.jupiter.api.Test;
import api2.models.internetDocument.CreateEW;

import java.io.*;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleTests {

    private Model model;

    @Test
    void t0() throws IOException {
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
    void t1() throws IOException {
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
    void t2() throws IOException {
        model = new CreateEW().removeProperty("Cost").build().run();
        assertTrue(model.getResponse().get("errors").toString().contains("Cost is empty"));
    }

    @Test
    void t00() throws IOException {
        model = new CreateEW().build().run();
        assert model.getResponse().get("success").asBoolean();
    }

    @Test
    void t01() throws IOException {
        assertTrue(new CreateEW().removeProperty("Cost").build().run().getResponse().get("success").asBoolean());
    }

    @Test
    void t02() throws IOException {
        new CreateEW().build().run();
    }

    @Test
    void t04()throws IOException{
        model = new CreateEW()
                .build().printRequest().run().printResponse();
    }

    @Test
    void t4() {
        Helper h = new Helper();
        System.out.println(h.getToday());
        System.out.println(h.getDaysPlus(2));
        System.out.println(h.getDaysMinus(3));
    }

    @Test
    void t03() throws IOException {
        File text = new File("./src/main/resources/data/x.x");
        FileReader fileReader = new FileReader(text);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
    }
}
