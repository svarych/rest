package api2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Properties;

public class Connector {
    private HttpsURLConnection connection;
    @Getter
    private String prettyResponse;
    @Getter
    private ObjectNode response;

    private Properties properties = new Properties();
    private InputStream configFile = new FileInputStream("./src/main/resources/connection.properties");

    Connector() throws FileNotFoundException {
    }

    public void send(String request) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        out.write(request);
        out.flush();
        out.close();
        bindRequestAndResponse();
    }

    private void bindRequestAndResponse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        ObjectMapper mapper = new ObjectMapper();
        this.response = mapper.readValue(response.toString(), ObjectNode.class);
        this.prettyResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.response);
    }

    public Connector prepare() throws IOException {
        properties.load(configFile);
        URL url = new URL(properties.getProperty("url"));
        connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod(properties.getProperty("requestMethod"));
        String USER_AGENT = properties.getProperty("userAgent");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setDoOutput(true);
        return this;
    }
}
