package api2;

import org.junit.jupiter.api.Test;
import api2.models.CreateEW;

import java.io.IOException;

class Testing {

    @Test
    void t0() throws IOException {
        Model model = new ModelBuilder().apiKey("123").modelName("model").calledMethod("method")
                .addMethodProperty("Key0", "Value0")
                .addMethodProperty("Key1", "Value1")
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
                .addMethodProperty("k0", "v1")
                .addMethodProperty("k1", "v1")

                .build()
                .printRequest()
                .printPrettyRequest()

                .run()
                .printResponse()
                .printPrettyResponse()
                ;


        System.out.println(model.getRequestNode().get("apiKey"));
    }

    @Test
    void t2() throws IOException {
        new CreateEW().build().printRequest().run().printPrettyResponse();
    }
}
