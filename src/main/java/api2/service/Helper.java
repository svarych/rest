package api2.service;

import api2.service.enums.KeyType;
import api2.service.enums.Server;
import api2.service.enums.UserType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static api2.service.enums.KeyType.EMPTY;
import static api2.service.enums.Server.LIVE;
import static api2.service.enums.Server.TEST;

public class Helper {

    public Helper() {
        setDateTime();
    }

    // DATE TIME -------------------------------------------------------------------------------------------------------
    private String today;
    private LocalDateTime currentDate;

    private void setDateTime() {
        currentDate = LocalDateTime.now();
        today = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getToday() {
        return today;
    }

    public String getDaysPlus(int days) {
        currentDate = LocalDateTime.now().plusDays(days);
        return currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getDaysMinus(int days) {
        currentDate = LocalDateTime.now().minusDays(days);
        return currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

// END OF DATE TIME ----------------------------------------------------------------------------------------------------

    // API KEY ---------------------------------------------------------------------------------------------------------

    public String clear(String input) {
        return input
                .replace("\"", "")
                .replace("[", "")
                .replace("]", "");
    }

    public String getApiKeyLoyaltyLive() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "50cdd@hell.yeah")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyLoyaltyLive() throws IOException {
        System.out.println(getApiKeyLoyaltyLive());
    }

    public String getApiKeyCorporateLive() throws IOException {
        Model model = new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "50cdd")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyCorporateLive() throws IOException {
        System.out.println(getApiKeyCorporateLive());
    }

    public String getApiKeyLoyaltyTest() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "fender@i.ua")
                .addProperty("Password", "12345")
                .build()
                .run(TEST);
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKeyLoyaltyTest2() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "alexxxvunet@mail.ru")
//                .addProperty("Password", "Hahl0Doo")
                .addProperty("Password", "123456")
                .build()
                .printPrettyRequest()
                .run(TEST);
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKey(String login, String password, Server server) throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", login)
                .addProperty("Password", password)
                .build()
                .printPrettyRequest()
                .run(server)
                .printPrettyResponse();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyLoyaltyTest() throws IOException {
        System.out.println(getApiKeyLoyaltyTest());
    }

    public String getApiKeyCorporateTest() throws IOException {
        Model model = new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "test_tech01")
                .addProperty("Password", "123456")
                .build()
                .run(TEST);
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyCorporateTest() throws IOException {
        System.out.println(getApiKeyCorporateTest());
    }

    private String getAllApiKeysForUser(String apiKey, KeyType keyType, Server server) throws IOException {
        String key = "";
        Model model = new ModelBuilder()
                .apiKey(apiKey)
                .modelName("CommonGeneral")
                .calledMethod("getApiKeysList")
                .build()
                .run(server)
                .printPrettyResponse();

        ObjectNode response = model.getResponse();
        for (JsonNode o : response.get("data")) {
            if (keyType.toString().equals("empty")) {
                if (clear(o.findValue("MarketplacePartnerDescription").toString()).length() == 0) {
                    key = clear(o.findValue("ApiKey").toString());
                }
            } else {
                if (clear(o.findValue("MarketplacePartnerDescription").toString()).equals(keyType.toString())) {
                    key = clear(o.findValue("ApiKey").toString());
                }
            }
        }
        return key;
    }

    @Test
    @DisplayName("Get all api keys for user by one any key")
    void getAllKeys() throws IOException {
        System.out.println(getAllApiKeysForUser("8f7e235f0afe315e2e81e10ac9d5c914", EMPTY, LIVE));
    }

// END OF API KEY ------------------------------------------------------------------------------------------------------

}
