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

                .build().run(Server.TEST);

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
                .addProperty("SenderCounterpartyRef", "8f59d90c-a1cc-11e7-80c5-0025909b4e32") // ТОВ Розетка Киев доставка
                .addProperty("SenderPhone", "380672203916")
                .addProperty("SenderContactPersonDescription", "Пупкін Дмитро")

                // Payment info
                .addProperty("PayerType", "Sender")
                .addProperty("PaymentMethod", "NonCash")
                .addProperty("Cost", "300")

                // Cargo info
                .addProperty("CargoType", "Cargo")
                .addProperty("Weight", "30")
                .addProperty("SeatsAmount", "1")
                .addProperty("Width", "10")
                .addProperty("Height", "10")
                .addProperty("Length", "10")
                .addProperty("Note", "примітка")

                // Time intervals
                .addProperty("TimeIntervalStart", timeStart())
                .addProperty("TimeIntervalEnd", timeEnd())

                // Type of request
                .addProperty("Type", "RequestFromMobile")

                .build().printPrettyRequest()
                .run(Server.TEST)
                .printPrettyResponse()
        ;
    }

//== HELPER ============================================================================================================

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

                .build()
                .run(Server.TEST)
        ;
        intervalStart = model.getResponse().findValue("Start").asText();
        intervalEnd = model.getResponse().findValue("End").asText();
    }

    private String timeStart() {
        return date() + intervalStart;
    }

    private String timeEnd() {
        return date() + intervalEnd;
    }

    @Test
    void xt0() throws IOException {
        bindTimeIntervals();
        System.out.println(timeStart());
        System.out.println(timeEnd());
    }

    private String date() {
        String full = new Helper().getToday();
        String year, day, month;
        year = reformat(full,2);
        month = reformat(full,1);
        day = reformat(full,0);
        return year + "-" + month + "-" + day + "T";
    }

    private String reformat(String s, int i){
        return s.replace(".","-").split("-")[i];
    }
}
