package api2;

import lombok.Getter;

import java.io.IOException;
import java.util.LinkedHashMap;


public class ModelBuilder {
    @Getter
    private Helper helper = new Helper();

    @Getter
    private String apiKey;
    @Getter
    private String modelName;
    @Getter
    private String calledMethod;
    @Getter
    private LinkedHashMap<Object, Object> methodProperties = new LinkedHashMap<Object, Object>();

    public ModelBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public ModelBuilder addMethodProperty(Object K, Object V) {
        methodProperties.put(K, V);
        return this;
    }

    public ModelBuilder properties(LinkedHashMap<Object, Object> map) {
        methodProperties = map;
        return this;
    }

    public Model build() throws IOException {
        return new Model(this);
    }

}
