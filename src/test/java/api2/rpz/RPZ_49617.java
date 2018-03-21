package api2.rpz;

import api2.models.internetDocument.GetListEW;
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

class RPZ_49617 {

    private Model model;
    private Properties properties = new Properties();

    RPZ_49617() throws IOException {
        InputStream configFile = new FileInputStream("./src/main/resources/properties/keys.properties");
        properties.load(configFile);
    }

    /**
     * Тестування друкованих форм - реєстри
     */

    @Test
    @DisplayName("Друк запиту на отримання Ref`ки реєстру")
    void printRegistry() throws IOException {
        System.out.println(requestForRegistry(CORPORATE_TEST, PDF));
        System.out.println(requestForRegistry(LOYALTY_TEST, PDF));
        System.out.println(requestForRegistry(CORPORATE_TEST, HTML));
        System.out.println(requestForRegistry(LOYALTY_TEST, HTML));
    }

    @Test
    @DisplayName("Друк запиту на отримання Ref`ки реєстру")
    void printEW() throws IOException {
        System.out.println(requestForEW(CORPORATE_TEST, PDF));
        System.out.println(requestForEW(LOYALTY_TEST, PDF));
        System.out.println(requestForEW(CORPORATE_TEST, HTML));
        System.out.println(requestForEW(LOYALTY_TEST, HTML));
    }

    private String clean(String input) {
        return input.replace("\"", "");
    }

    private String requestForRegistry(UserType userType, Format format) throws IOException {
        if (userType == CORPORATE_TEST) {
            model = new GetRegistryList().user(CORPORATE_TEST).build().run(TEST);

            return "https://webclient.sb.np.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.test");
        }
        if (userType == UserType.LOYALTY_TEST) {
            model = new GetRegistryList().user(UserType.LOYALTY_TEST).build().run(TEST);
            return "https://webclient.sb.np.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.test");
        }
        if (userType == UserType.CORPORATE_LIVE) {
            model = new GetRegistryList().user(UserType.CORPORATE_LIVE).build().run(LIVE);
            return "https://my.novaposhta.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.live");
        }
        if (userType == UserType.LOYALTY_LIVE) {
            model = new GetRegistryList().user(UserType.LOYALTY_LIVE).build().run(LIVE);
            return "https://my.novaposhta.ua/scanSheet/printScanSheet/refs[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.live");
        } else return null;
    }

    private String requestForEW(UserType userType, Format format) throws IOException {
        if (userType == CORPORATE_TEST) {
            System.out.print("Corporate test");
            if (format == PDF) {
                System.out.println(" PDF");
            }
            if (format == HTML) {
                System.out.println(" HTML");
            }
            model = new GetListEW().user(CORPORATE_TEST).build().run(TEST);
            return "https://webclient.sb.np.ua/orders/printDocument/orders[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.test");
        }
        if (userType == UserType.LOYALTY_TEST) {
            System.out.print("Loyalty test");
            if (format == PDF) {
                System.out.println(" PDF");
            }
            if (format == HTML) {
                System.out.println(" HTML");
            }
            model = new GetListEW().user(UserType.LOYALTY_TEST).build().run(TEST);
            return "https://webclient.sb.np.ua/orders/printDocument/orders[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.test");
        }
        if (userType == UserType.CORPORATE_LIVE) {
            model = new GetListEW().user(UserType.CORPORATE_LIVE).build().run(LIVE);
            System.out.print("Corporate live");
            if (format == PDF) {
                System.out.println(" PDF");
            }
            if (format == HTML) {
                System.out.println(" HTML");
            }
            return "https://my.novaposhta.ua/orders/printDocument/orders[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("corporate.live");
        }
        if (userType == UserType.LOYALTY_LIVE) {
            System.out.print("Loyalty live");
            if (format == PDF) {
                System.out.println(" PDF");
            }
            if (format == HTML) {
                System.out.println(" HTML");
            }
            model = new GetListEW().user(UserType.LOYALTY_LIVE).build().run(LIVE);
            return "https://my.novaposhta.ua/orders/printDocument/orders[]/"
                    + clean(model.getResponse().findValue("Ref").toString())
                    + "/type/" + format + "/apiKey/"
                    + properties.getProperty("loyalty.live");
        } else return null;
    }

    @Test
    @DisplayName("EW loyalty")
    void ew_loyalty() throws IOException {
        model = new GetListEW().user(LOYALTY_TEST).build().printRequest().run(TEST);
        System.out.println(model.getResponse());
    }

    @Test
    @DisplayName("EW Corporate")
    void ew_corporate() throws IOException {
        model = new GetListEW().user(CORPORATE_TEST).build().run(TEST);
        System.out.println(model.getResponse());
    }
}
