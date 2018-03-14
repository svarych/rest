package api2.models.internetDocument;

import api2.service.ModelBuilder;
import api2.service.enums.UserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class GetListEW extends ModelBuilder {

    private Properties properties;

    public GetListEW() throws IOException {
        properties = new Properties();
        InputStream configFile = new FileInputStream("./src/main/resources/properties/keys.properties");
        properties.load(configFile);

        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("InternetDocument")
                .calledMethod("getDocumentList")
                .addProperty("GetFullList", "1");
    }

    public GetListEW getPage(int page) {
        this.replaceProperty("GetFullList", "0");
        this.addProperty("Page", String.valueOf(page));
        return this;
    }

    public GetListEW getTodayList() {
        this.addProperty("DateTime", getHelper().getToday());
        return this;
    }

    public GetListEW getListForDate(String date) {
        this.addProperty("DateTime", date);
        return this;
    }

    public GetListEW getListForPeriod(String from, String to) {
        this.dateFrom(from).dateTo(to);
        return this;
    }

    public GetListEW getFullList() {
        this.dateFrom("01.01.1991").dateTo(getHelper().getToday());
        return this;
    }

    public GetListEW dateFrom(String date) {
        this.addProperty("DateTimeFrom", date);
        return this;
    }

    public GetListEW dateTo(String date) {
        this.addProperty("DateTimeTo", date);
        return this;
    }

    public GetListEW user(UserType userType) {
        if (userType.equals(UserType.CORPORATE_LIVE)) {
            this.apiKey(properties.getProperty("corporate.live"));
        }
        if (userType.equals(UserType.CORPORATE_TEST)) {
            this.apiKey(properties.getProperty("corporate.test"));
        }
        if (userType.equals(UserType.LOYALTY_LIVE)) {
            this.apiKey(properties.getProperty("loyalty.live"));
        }
        if (userType.equals(UserType.LOYALTY_TEST)) {
            this.apiKey(properties.getProperty("loyalty.test"));
        }
        return this;
    }
}
