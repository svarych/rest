package api2.rpz;

import api2.models.catalogue.*;
import api2.service.Helper;
import api2.service.Model;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Дані тести не відпрацюють на живому сервері, по тій причині, що адміністративно заборонено створювати ЕН, а для
 * коректної роботи тестів, необхідно створити ЕН на підставі ІД
 * <p>
 * Тому, дані тести (до появи перед-production сервера) будуть виконуватись на тестовому сервері.
 */

class RPZ_49953 {

    private Model model;

    @Test
    @DisplayName("Виды временных интервалов")
    void timeIntervalTypes() throws IOException {
        model = new TimeIntervalTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        JsonNode node = model.getResponse().findValue("data").get(0);
        assert !node.findValue("Start").equals(node.findValue("End"));
    }

    @Test
    @DisplayName("Виды груза")
    void cargoTypes() throws IOException {
        model = new CargoTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды обратной доставки груза")
    void cargoBackwardDeliveryTypes() throws IOException {
        model = new CargoBackwardDeliveryTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды паллет")
    void palletTypes() throws IOException {
        model = new PalletTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды плательщиков")
    void payerTypes() throws IOException {
        model = new PayerTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды плательщиков обратной доставки")
    void backwardDeliveryPayerTypes() throws IOException {
        model = new BackwardDeliveryPayerTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды упаковки")
    void packageTypes() throws IOException {
        model = new PackageTypes().length(460).build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert Double.parseDouble(new Helper().clear(model.getResponse().findValue("Length").toString())) >= 460;
    }

    @Test
    @DisplayName("Виды шин и дисков")
    void tyreWheelTypes() throws IOException {
        model = new TyreWheelTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert !model.getResponse().findValues("Ref").get(0).equals(model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Описания груза")
    void cargoDescriptions() throws IOException {
        model = new CargoDescriptions().name("фотопапір").build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValue("Description").toString().contains("фотопапір");
    }

    @Test
    @DisplayName("Перечень ошибок")
    void errorList() throws IOException {
        model = new ErrorList().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValues("MessageCode").size() > 1;
    }

    @Test
    @DisplayName("Технологии доставки")
    void deliveryTechnologies() throws IOException {
        model = new DeliveryTechnologies().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValues("Description").size() > 1;
    }

    @Test
    @DisplayName("Типы контрагентов")
    void counterPartyTypes() throws IOException {
        model = new CounterPartyTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValues("Description").size() > 1;
    }

    @Test
    @DisplayName("Формы оплаты")
    void paymentForms() throws IOException {
        model = new PaymentForms().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValues("Description").size() > 1;
    }

    @Test
    @DisplayName("Формы собственности")
    void ownershipTypes() throws IOException {
        model = new OwnershipTypes().build().run();
        System.out.println(model
                .printPrettyRequest()
                .printPrettyResponse()
        );

        assert model.getResponse().findValues("Ref").size() > 1;
    }

}
