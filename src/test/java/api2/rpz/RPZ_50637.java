package api2.rpz;

import api2.models.addresses.GetCitiesOfCompany;
import api2.models.addresses.GetStreetsOfCompany;
import api2.service.Helper;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RPZ-50637 - Зміна логіки заявки на виклик кур’єра
 */

class RPZ_50637 {

    Model model;
    private String intervalStart;
    private String intervalEnd;

    @Test
    @DisplayName("Common → getPickupTimeIntervals")
    void t0() throws IOException {
        model = new ModelBuilder()
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .modelName("Common")
                .calledMethod("getPickupTimeIntervals")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("SenderCityRef", getCityRefByName("Полтава"))

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();

        assertTrue(model.getResponse().get("errors").toString().contains("getStatusDocument not found"));
    }

    @Test
    @DisplayName("CommonGeneral → sendConsolidateOrder")
    void t1() throws IOException {

        bindTimeIntervals();

        model = new ModelBuilder()
                .apiKey(new Helper().getApiKeyCorporateTest())
                .system("MobileApp")
                .modelName("CommonGeneral")
                .calledMethod("sendConsolidateOrder")

                // Адреса
                .addProperty("SenderCityRef", getCityRefByName("Львів"))
                .addProperty("SenderStreetRef", getStreetRefByName("Львів", "Дудаєва"))
                .addProperty("SenderHouse", "1")
                .addProperty("SenderAddressDescription", "Генерала Джохара Дудаєва")
                .addProperty("AddressComment", ", героя Ічкерії")

                // Контактна інформація
                .addProperty("SenderCounterpartyRef", "b366266a-6548-11e6-af9a-005056886752")
                .addProperty("SenderPhone", "380675387254")
                .addProperty("SenderContactPersonDescription", "Хоменко Андрій")

                // Payment info
                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("Cost", "300")

                // Cargo info
                .addProperty("CargoType", "Cargo")
                .addProperty("Weight", "30")
                .addProperty("SeatsAmount", "1")
                .addProperty("Width", "")
                .addProperty("Height", "")
                .addProperty("Length", "")
                .addProperty("Note", "примітка")

                // Time intervals
                .addProperty("TimeIntervalStart", "")
                .addProperty("TimeIntervalEnd", "")

                // Type of request
                .addProperty("Type", "RequestFromMobile")

                .build().printPrettyRequest();
    }

    private String getCityRefByName(String name) throws IOException {
        model = new GetCitiesOfCompany().byName(name).build().run();
        return new Helper().clear(model.getResponse().findValue("Ref").toString());
    }

    private String getStreetRefByName(String city, String street) throws IOException {
        model = new GetStreetsOfCompany().byStreetName(street).replaceProperty("CityRef", getCityRefByName(city)).build().run();
        return new Helper().clear(model.getResponse().findValue("Ref").toString());
    }

    private void bindTimeIntervals() throws IOException {
        model = new ModelBuilder()
                .apiKey("b96bd5383d1c0b66d29d859030c0d7de")
                .modelName("Common")
                .calledMethod("getPickupTimeIntervals")
                .addProperty("DateTime", new Helper().getToday())
                .addProperty("SenderCityRef", getCityRefByName("Львів"))

                .build().printPrettyRequest()
                .run(Server.TEST).printPrettyResponse();

        intervalStart = model.getResponse().findValue("Start").asText();
        intervalEnd = model.getResponse().findValue("End").asText();
    }

    @Test
    void xt0() throws IOException {
        bindTimeIntervals();
        System.out.println(timeStart());
        System.out.println(timeEnd());
    }

    private String timeStart() {
        return date() + intervalStart;
    }

    private String timeEnd() {
        return date() + intervalEnd;
    }

    private String date(){
        String full = new Helper().getToday();
        String year, day, month;
        year = full.split(".", 0)[2];
        month = full.split(".", 0)[1];
        day = full.split(".", 0)[0];
        return year + "-" + month + "-" + day + "T";
    }
}
