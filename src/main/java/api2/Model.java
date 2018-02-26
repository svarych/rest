package api2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Model {
    private Connector connector = new Connector();
    private ObjectMapper mapper = new ObjectMapper();

    @JsonIgnore
    private String request;

    @JsonIgnore
    private ObjectNode requestNode;

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private LinkedHashMap<String, Object> methodProperties;

    Model(final ModelBuilder modelBuilder) throws IOException {
        this.apiKey = modelBuilder.getApiKey();
        this.modelName = modelBuilder.getModelName();
        this.calledMethod = modelBuilder.getCalledMethod();
        this.methodProperties = modelBuilder.getMethodProperties();

        this.request = mapper.writeValueAsString(this);
        this.requestNode = mapper.readValue(this.getRequest(), ObjectNode.class);
    }

    @JsonIgnore
    public ObjectNode getResponse(){
        return connector.getResponse();
    }

    public String getRequest() {
        return request;
    }

    public ObjectNode getRequestNode() {
        return requestNode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public LinkedHashMap<String, Object> getMethodProperties() {
        return methodProperties;
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

    public Model assertTrue(Boolean condition){
        Assertions.assertTrue(condition);
        return this;
    }

    //==================================================================================================================
}
