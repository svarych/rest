package api2.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static api2.service.Connector.server.live;
import static api2.service.Connector.server.test;

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

    private String clear(String input) {
        return input.replace("\"", "").replace("[", "").replace("]", "");
    }

    public String getApiKeyLoyaltyLive() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "50cdd@hell.yeah")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKeyCorporateLive() throws IOException {
        Model model = new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "50cdd")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKeyLoyaltyTest() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "fender@i.ua")
                .addProperty("Password", "12345")

                .build()
                .printPrettyRequest()

                .run(test)
                .printPrettyResponse()
                ;
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKeyCorporateTest() throws IOException {
        Model model = new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "test_tech01")
                .addProperty("Password", "123456")

                .build()
                .printPrettyRequest()

                .run(test)
                .printPrettyResponse()
                ;
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyLoyaltyLive() throws IOException {
        System.out.println(getApiKeyLoyaltyLive());
    }

    @Test
    void printApiKeyCorporateLive() throws IOException {
        System.out.println(getApiKeyCorporateLive());
    }

    @Test // Fender
    void printApiKeyLoyaltyTest() throws IOException {
        System.out.println(getApiKeyLoyaltyTest());
    }

    @Test
    void printApiKeyCorporateTest() throws IOException {
        System.out.println(getApiKeyCorporateTest());
    }

// END OF API KEY ------------------------------------------------------------------------------------------------------

}
