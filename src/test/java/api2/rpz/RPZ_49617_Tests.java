package api2.rpz;

import api2.models.registry.GetRegistryList;
import api2.service.Model;
import api2.service.enums.Format;
import api2.service.enums.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static api2.service.enums.Format.HTML;
import static api2.service.enums.Format.PDF;
import static api2.service.enums.Server.LIVE;
import static api2.service.enums.Server.TEST;
import static api2.service.enums.UserType.CORPORATE_TEST;
import static api2.service.enums.UserType.LOYALTY_TEST;

class RPZ_49617_Tests {

    private Model model;
    private Properties properties = new Properties();

    RPZ_49617_Tests() throws IOException {
        InputStream configFile = new FileInputStream("./src/main/resources/properties/keys.properties");
        properties.load(configFile);
    }

    /**
     * Тестування друкованих форм - реєстри
     */

    @Test
    @DisplayName("Друк запиту на отримання Ref`ки реєстру")
    void printRegister() throws IOException {
        System.out.println(requestFor(CORPORATE_TEST, PDF));
        System.out.println(requestFor(LOYALTY_TEST, PDF));
        System.out.println(requestFor(CORPORATE_TEST, HTML));
        System.out.println(requestFor(LOYALTY_TEST, HTML));
    }

    private String clean(String input) {
        return input.replace("\"", "");
    }

    private String requestFor(UserType userType, Format format) throws IOException {
        if (userType.equals(CORPORATE_TEST)) {
            model = new GetRegistryList().user(CORPORATE_TEST).build().run(TEST);

            return "https://webclient.sb.np.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.test");
        }
        if (userType.equals(UserType.LOYALTY_TEST)) {
            model = new GetRegistryList().user(UserType.LOYALTY_TEST).build().run(TEST);
            return "https://webclient.sb.np.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.test");
        }
        if (userType.equals(UserType.CORPORATE_LIVE)) {
            model = new GetRegistryList().user(UserType.CORPORATE_LIVE).build().run(LIVE);
            return "https://my.novaposhta.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.live");
        }
        if (userType.equals(UserType.LOYALTY_LIVE)) {
            model = new GetRegistryList().user(UserType.LOYALTY_LIVE).build().run(LIVE);
            return "https://my.novaposhta.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.live");
        } else return null;
    }
}
