package api2.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static api2.service.Connector.server.live;

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

    public String getApiKeyLoyalty() throws IOException {
        Model model = new ModelBuilder()
                .modelName("LoyaltyUser").calledMethod("getLoyaltyUserByLogin")
                .addProperty("Login", "50cdd@hell.yeah")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    public String getApiKeyCorporate() throws IOException {
        Model model = new ModelBuilder()
                .modelName("CorporateUserGeneral").calledMethod("getCorporateByLogin")
                .addProperty("Login", "50cdd")
                .addProperty("Password", "1234")
                .build().run();
        return clear(model.getResponse().findValue("ApiKey").toString());
    }

    @Test
    void printApiKeyLoyaltyLive() throws IOException {
        System.out.println(getApiKeyLoyalty());
    }

    @Test
    void printApiKeyCorporateLive() throws IOException {
        System.out.println(getApiKeyCorporate());
    }

// END OF API KEY ------------------------------------------------------------------------------------------------------

}
