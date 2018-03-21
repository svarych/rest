package api2.rpz;

import api2.models.addresses.*;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестування запитів в рамках RPZ_49952
 */

class RPZ_49952 {

    private Model model;

    @Test
    @DisplayName("Онлайн поиск в справочнике населенных пунктов")
    void searchSettlementsTest() throws Throwable {
        model = new OnlineSearchSettlements().replaceProperty("CityName", "Бровари").build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("MainDescription").size() > 0);
    }

    @Test
    @DisplayName("Онлайн поиск улиц в справочнике населенных пунктов")
    void searchStreetsOnSettlementTest() throws IOException {
        model = new OnlineSearchStreets().build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("TotalCount").size() > 0);
    }

    @Test
    @DisplayName("Создать адрес контрагента (отправитель/получатель)")
    void createCounterPartyAddressTest() throws IOException {
        model = new CreateCounterPartyAddress().build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Ref").size() == 1);
    }

    @Test
    @DisplayName("Редактировать адрес контрагента (отправитель/получатель)")
    void editCounterPartyAddressTest() throws IOException {
        model = new EditCounterPartyAddress().build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Ref").size() == 1);
    }

    @Test
    @DisplayName("Удалить адрес контрагента (отправитель/получатель)")
    void deleteCounterPartyAddressTest() throws IOException {
        // Creating new Address
        model = new CreateCounterPartyAddress().replaceProperty("BuildingNumber", "11К").build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Ref").size() == 1);

        // Getting Ref of Address to delete
        String ref = model.getResponse().findValue("Ref").toString().replace("\"", "");
        assert ref.length() > 0;

        // Deleting created Address
        model = new DeleteCounterPartyAddress().replaceProperty("Ref", ref).build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Ref").size() > 0);
    }

    /**
     * В даному тесті можна робити запити:
     * 1. На отримання міста по Ref -- byRef("7110b9c1-92f3-11e2-a57a-d4ae527baec3");
     * 2. На отримання міста по назві -- byName("Бровари");
     * 3. На отримання усіх міст -- getAll();
     */
    @Test
    @DisplayName("Справочник городов компании")
    void getCitiesOfCompanyTest() throws IOException {
        model = new GetCitiesOfCompany().byName("Ясіня").build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValue("Description").toString().equals("\"Ясіня\""));
    }

    /**
     * В даному тесті можна робити запити:
     * 1. На отримання усіх міст -- getAll();
     * 2. На отримання населеного пункту по назві -- byName("Бровари");
     * 3. На отримання населеного пункту за ознакою "з відділеннями" або "без відділень" -- withWarehouses(true/false);
     * 4. На одну сторінку виводиться 150 записів. Тому є можливість вказувати сторінку результатів -- page(2);
     */
    @Test
    @DisplayName("Справочник населенных пунктов Украины")
    void getSettlementsUkraineTest() throws IOException {
        model = new GetSettlementsUkraine().byName("Бровари").build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Ref").size() > 0);
    }

    @Test
    @DisplayName("Справочник географических областей Украины")
    void getGeographicalRegionsTest() throws IOException {
        model = new GetGeographicalRegions().build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Description").size() == 25);
    }

    @Test
    @DisplayName("Справочник отделений и типов отделений")
    void getWarehousesTest() throws IOException {
        model = new GetWarehouses()
                .addProperty("Limit", "1")
                .addProperty("Page", "1")
                .build().run();

        System.out.println(model
                        .printPrettyRequest()
                        .printPrettyResponse()
        )
        ;

        assertTrue(model.getResponse().findValues("SiteKey").size() > 0);
    }

    /**
     * В даномі тесті можна робити запит на отримання вулиць за назвою або її частиною -- byStreetName("Гру");
     */
    @Test
    @DisplayName("Справочник улиц компании")
    void getStreetsOfCompanyTest() throws IOException {
        model = new GetStreetsOfCompany().byStreetName("Гру").build().run();

        System.out.println(model.printPrettyRequest().printPrettyResponse());

        assertTrue(model.getResponse().findValues("Description").size() > 0);
    }

}
