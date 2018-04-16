package api2.LIVE.redirecting;

import api2.models.addresses.GetCitiesOfCompany;
import api2.models.addresses.OnlineSearchStreets;
import api2.models.redirecting.CheckPossibilityForRedirecting;
import api2.models.redirecting.CreateRedirectingRequest;
import api2.models.redirecting.CreateRedirectingRequest.Target;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    @Test
    @DisplayName("Проверка возможности создания заявки на переадресацию отправления")
    void checkPossibilityForRedirecting() throws IOException {
        model = new CheckPossibilityForRedirecting().docNumber("20450066644175").build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Создание заявки переадресации отправления (адрес)")
    void createRedirectingRequestAddress() throws IOException {
        model = new CreateRedirectingRequest(Target.ADDRESS).docNumber("20450066644175").build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Создание заявки переадресации отправления (отделение)")
    void createRedirectingRequestWarehouse() throws IOException {
        model = new CreateRedirectingRequest(Target.WAREHOUSE).docNumber("20450066644175").build().run();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

//    ------------------------------------------------------------------------------------------------------------------
//    DEBUGGING

//    @Test
    void printStreet() throws IOException {
//        new OnlineSearchStreets().settlementName("Львів").street("Дудаєва")
        new OnlineSearchStreets()
                .build().printPrettyRequest().run().printPrettyResponse();
    }

//    @Test
    void printCity() throws IOException {
        new GetCitiesOfCompany().byName("Львів")
                .build().printRequest().run().printPrettyResponse();
    }

//    END OF DEBUGGING
//    ------------------------------------------------------------------------------------------------------------------

}
