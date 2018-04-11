package api2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;

public class FreeModel {

    private ObjectMapper mapper = new ObjectMapper();

    private String apiKey;
    private String modelName;
    private String calledMethod;
    private LinkedHashMap<String, Object> methodProperties;

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

    public FreeModel addNewField() throws JsonProcessingException {
        Object input = new Option<>(new BaseClass("inner"), "outer");
        System.out.println(new ObjectMapper().writeValueAsString(input));
        return this;
    }

    @Test
    void print() throws IOException {
//        mapper = new ObjectMapper();
//        @JsonAppend(attrs = {@JsonAppend.Attr(value = "")})
        System.out.println(mapper.writeValueAsString(this.addNewField()));
    }

    static class Option<T> {
        private T inner;
        private String extraField;

        Option(T inner, String field) {
            this.inner = inner;
            this.extraField = field;
        }

        public T getInner() {
            return inner;
        }

        public String getExtraField() {
            return extraField;
        }
    }

    static class BaseClass {
        private String baseField;

        public BaseClass(String baseField) {
            this.baseField = baseField;
        }

        public String getBaseField() {
            return baseField;
        }
    }

}
