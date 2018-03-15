package api2.service;

import api2.service.enums.Server;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import static api2.service.enums.Server.LIVE;
import static api2.service.enums.Server.TEST;

public class Connector {

    private URL url;
    private BufferedReader in;
    private BufferedWriter out;

    private ObjectNode response;
    private String prettyResponse;

    private Properties properties = new Properties();

    public Connector() throws IOException {
        InputStream configFile = new FileInputStream("./src/main/resources/properties/connection.properties");
        properties.load(configFile);
    }

    public void send(String request, Server... server) throws IOException {
        if (server.length > 0) {
            for (Server s : server) {
                if (s == LIVE) {
                    runOnLive(request);
                }
                if (s == TEST) {
                    runOnTest(request);
                }
            }
        } else {
            runOnLive(request);
        }

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        ObjectMapper mapper = new ObjectMapper();
        this.response = mapper.readValue(response.toString(), ObjectNode.class);
        this.prettyResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.response);
    }

    private void runOnLive(String request) throws IOException {
        url = new URL(properties.getProperty("url.live"));
        HttpsURLConnection https = (HttpsURLConnection) url.openConnection();

        https.setDoOutput(true);
        https.setRequestMethod(properties.getProperty("requestMethod"));

        String USER_AGENT = properties.getProperty("userAgent");
        https.setRequestProperty("User-Agent", USER_AGENT);

        out = new BufferedWriter(new OutputStreamWriter(https.getOutputStream(), "UTF-8"));
        out.write(request);
        out.flush();
        out.close();

        in = new BufferedReader(new InputStreamReader(https.getInputStream()));
    }

    private void runOnTest(String request) throws IOException {
        url = new URL(properties.getProperty("url.test"));
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setDoOutput(true);
        http.setRequestMethod(properties.getProperty("requestMethod"));

        String USER_AGENT = properties.getProperty("userAgent");
        http.setRequestProperty("User-Agent", USER_AGENT);

        out = new BufferedWriter(new OutputStreamWriter(http.getOutputStream(), "UTF-8"));
        out.write(request);
        out.flush();
        out.close();

        in = new BufferedReader(new InputStreamReader(http.getInputStream()));
    }

    public String getPrettyResponse() {
        return prettyResponse;
    }

    public ObjectNode getResponse() {
        return response;
    }
}
