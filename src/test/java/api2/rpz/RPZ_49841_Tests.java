package api2.rpz;

import api2.models.redirecting.CheckPossibilityForRedirecting;
import api2.models.redirecting.CreateRedirectingRequest;
import api2.models.redirecting.CreateRedirectingRequest.Target;
import api2.service.Model;
import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Дані тести не відпрацюють на живому сервері, по тій причині, що адміністративно заборонено створювати ЕН, а для
 * коректної роботи тестів, необхідно створити ЕН на підставі ІД
 * <p>
 * Тому, дані тести (до появи перед-production сервера) будуть виконуватись на тестовому сервері.
 */

class RPZ_49841_Tests {

    private Model model;


    @Test
    @DisplayName("Проверка возможности создания заявки на переадресацию отправления")
    void checkPossibilityForRedirecting() throws IOException {
        model = new CheckPossibilityForRedirecting().docNumber("20600000068796")
                .apiKey("88480f5e25e304a397605165c6f12d7c").build().printPrettyRequest().run(Server.TEST).printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Создание заявки переадресации отправления (адрес)")
    void createRedirectingRequestAddress() throws IOException {
        model = new CreateRedirectingRequest(Target.ADDRESS).docNumber("20600000068796")
                .apiKey("88480f5e25e304a397605165c6f12d7c").build().printPrettyRequest().run(Server.TEST).printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

    @Test
    @DisplayName("Создание заявки переадресации отправления (отделение)")
    void createRedirectingRequestWarehouse() throws IOException {
        model = new CreateRedirectingRequest(Target.WAREHOUSE).docNumber("20600000068796")
                .apiKey("88480f5e25e304a397605165c6f12d7c").build().printPrettyRequest().run(Server.TEST).printPrettyResponse();
        assertTrue(model.getResponse().get("success").asBoolean());
    }

//    @Test
    @DisplayName("small test")
    void smallTest(){}


    @AfterEach
//    @Test
    @DisplayName("Видаляємо замовлення на переадресування")
    void deleteRedirectOrder() throws IOException {

        if (model.getResponse().get("success").asBoolean()) {
            System.out.println("THIS STEP IS CORRECT");
            String ref = clear(model.getResponse().findValue("Ref").toString());
            System.out.println("REF IS: " + ref);

            model = new ModelBuilder().apiKey("88480f5e25e304a397605165c6f12d7c")
                    .modelName("AdditionalServiceGeneral").calledMethod("delete")
                    .addProperty("Ref", ref)
                    .build().printPrettyRequest()
                    .run(Server.TEST).printPrettyResponse()
            ;
        }


//            model = new ModelBuilder().apiKey("88480f5e25e304a397605165c6f12d7c")
//                    .modelName("AdditionalServiceGeneral").calledMethod("delete")
//                    .addProperty("Ref", "a9ec9b48-2927-11e8-a857-005056b28cf4")
//                    .build().printPrettyRequest()
//                    .run(Server.TEST).printPrettyResponse()
//            ;
//        System.out.println("IS RESPONSE HERE? :" + model.getResponse().get("success"));
    }

    private String clear(String s) {
        return s
                .replace("/", "")
                .replace("\"", "")
                .replace("[", "")
                .replace("]", "");
    }

}
