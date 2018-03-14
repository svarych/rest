package api2.rpz;

import api2.service.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RPZ {

    protected Model model;
    protected Properties properties = new Properties();

    void load_config(String file) throws IOException {
        String path = "./src/main/resources/properties/" + file;
        InputStream configFile = new FileInputStream(path);
        properties.load(configFile);
    }

    String clear(String input){
        return input
                .replace("\"","")
                .replace("[","")
                .replace("]","")
                ;
    }
}
