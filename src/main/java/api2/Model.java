package api2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

public class Model {
    private Connector connector = new Connector();
    private ObjectMapper mapper = new ObjectMapper();

    @Getter
    @JsonIgnore
    private String request;

    @Getter
    @JsonIgnore
    private ObjectNode requestNode;

    @Getter
    private String apiKey;
    @Getter
    private String modelName;
    @Getter
    private String calledMethod;
    @Getter
    private LinkedHashMap<Object, Object> methodProperties;

    Model(final ModelBuilder modelBuilder) throws IOException {
        this.apiKey = modelBuilder.getApiKey();
        this.modelName = modelBuilder.getModelName();
        this.calledMethod = modelBuilder.getCalledMethod();
        this.methodProperties = modelBuilder.getMethodProperties();

        this.request = mapper.writeValueAsString(this);
        this.requestNode = mapper.readValue(this.getRequest(), ObjectNode.class);
    }

    protected Model() throws FileNotFoundException {
    }

    //==================================================================================================================
    // Send request
    public Model run() throws IOException {
        connector.prepare().send(getRequest());
        return this;
    }
    //------------------------------------------------------------------------------------------------------------------

    // Print request
    public Model printRequest() {
        System.out.println(getRequest());
        return this;
    }

    // Print formatted request
    public Model printPrettyRequest() throws IOException {
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(getRequestNode()));
        return this;
    }

    // Print response
    public Model printResponse() {
        System.out.println(connector.getResponse());
        return this;
    }

    // Print formatted response
    public Model printPrettyResponse() {
        System.out.println(connector.getPrettyResponse());
        return this;
    }

    //==================================================================================================================
}
