package api2.service;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ModelBuilder {

    private Helper helper = new Helper();

    private String apiKey;
    private String system;
    private String modelName;
    private String calledMethod;
    private LinkedHashMap<String, Object> methodProperties = new LinkedHashMap<>();

    public ModelBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ModelBuilder system(String system) {
        this.system = system;
        return this;
    }

    public ModelBuilder modelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public ModelBuilder calledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
        return this;
    }

    public ModelBuilder addProperty(String K, Object V) {
        methodProperties.put(K, V);
        return this;
    }

    public ModelBuilder removeProperty(String K) {
        methodProperties.remove(K);
        return this;
    }

    public ModelBuilder replaceProperty(String K, Object V) {
        methodProperties.replace(K, V);
        return this;
    }

    public ModelBuilder propertiesMap(LinkedHashMap<String, Object> map) {
        methodProperties = map;
        return this;
    }

    public Model build() throws IOException {
        return new Model(this);
    }

    public Helper getHelper() {
        return helper;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSystem() {
        return system;
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

    public String clear(String s) {
        return s
                .replace("\"", "")
                .replace("[", "")
                .replace("]", "")
                ;

    }
}
