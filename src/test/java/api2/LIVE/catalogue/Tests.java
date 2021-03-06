package api2.LIVE.catalogue;

import api2.models.catalogue.*;
import api2.service.Helper;
import api2.service.Model;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    private Model model;

    @Test
    @DisplayName("Виды временных интервалов")
    void timeIntervalTypes() throws IOException {
        model = new TimeIntervalTypes().build().run();
        assertNotEquals(model.getResponse().findValue("Start"), model.getResponse().findValue("End"));
    }

    @Test
    @DisplayName("Виды груза")
    void cargoTypes() throws IOException {
        model = new CargoTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды обратной доставки груза")
    void cargoBackwardDeliveryTypes() throws IOException {
        model = new CargoBackwardDeliveryTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды паллет")
    void palletTypes() throws IOException {
        model = new PalletTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды плательщиков")
    void payerTypes() throws IOException {
        model = new PayerTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды плательщиков обратной доставки")
    void backwardDeliveryPayerTypes() throws IOException {
        model = new BackwardDeliveryPayerTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Виды упаковки")
    void packageTypes() throws IOException {
        model = new PackageTypes().length(460).build().run();
        assertTrue(Double.parseDouble(new Helper().clear(model.getResponse().findValue("Length").toString())) >= 460);
    }

    @Test
    @DisplayName("Виды шин и дисков")
    void tyreWheelTypes() throws IOException {
        model = new TyreWheelTypes().build().run();
        assertNotEquals(model.getResponse().findValues("Ref").get(0), model.getResponse().findValues("Ref").get(1));
    }

    @Test
    @DisplayName("Описания груза")
    void cargoDescriptions() throws IOException {
        model = new CargoDescriptions().name("фотопапір").build().run();
        assertTrue(model.getResponse().findValue("Description").toString().contains("фотопапір"));
    }

    @Test
    @DisplayName("Перечень ошибок")
    void errorList() throws IOException {
        model = new ErrorList().build().run();
        assertTrue(model.getResponse().findValues("MessageCode").size() > 1);
    }

    @Test
    @DisplayName("Технологии доставки")
    void deliveryTechnologies() throws IOException {
        model = new DeliveryTechnologies().build().run();
        assertTrue(model.getResponse().findValues("Description").size() > 1);
    }

    @Test
    @DisplayName("Типы контрагентов")
    void counterPartyTypes() throws IOException {
        model = new CounterPartyTypes().build().run();
        assertTrue(model.getResponse().findValues("Description").size() > 1);
    }

    @Test
    @DisplayName("Формы оплаты")
    void paymentForms() throws IOException {
        model = new PaymentForms().build().run();
        assertTrue(model.getResponse().findValues("Description").size() > 1);
    }

    @Test
    @DisplayName("Формы собственности")
    void ownershipTypes() throws IOException {
        model = new OwnershipTypes().build().run();
        assertTrue(model.getResponse().findValues("Ref").size() > 1);
    }
}
