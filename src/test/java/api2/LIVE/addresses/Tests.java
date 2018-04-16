package api2.LIVE.addresses;

import api2.models.addresses.*;
import api2.service.Model;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Tests {

    private Model model;

    @Test
    @DisplayName("Search Settlement")
    void searchSettlementsTest() throws Throwable {
        model = new OnlineSearchSettlements().replaceProperty("CityName", "Бровари").build().run();
        assertTrue(model.getResponse().findValues("MainDescription").size() > 0);
    }

    @Test
    @DisplayName("Search Streets on Settlement")
    void searchStreetsOnSettlementTest() throws IOException {
        model = new OnlineSearchStreets().build().run();
        assertTrue(model.getResponse().findValues("TotalCount").size() > 0);
    }

    @Test
    @DisplayName("Create CounterParty Address")
    void createCounterPartyAddressTest() throws IOException {
        model = new CreateCounterPartyAddress().build().run();
        assertEquals(1, model.getResponse().findValues("Ref").size());
    }

    @Test
    @DisplayName("Edit CounterParty Address")
    void editCounterPartyAddressTest() throws IOException {
        model = new EditCounterPartyAddress().build().run();
        assertEquals(1, model.getResponse().findValues("Ref").size());
    }

    @Test
    @DisplayName("Delete CounterParty Address")
    void deleteCounterPartyAddressTest() throws IOException {
        // Creating new Address
        model = new CreateCounterPartyAddress().replaceProperty("BuildingNumber", "11К").build().run();
        assertEquals(1, model.getResponse().findValues("Ref").size());

        // Getting Ref of Address to delete
        String ref = model.getResponse().findValue("Ref").toString().replace("\"", "");
        assertTrue(ref.length() > 0);

        // Deleting created Address
        model = new DeleteCounterPartyAddress().replaceProperty("Ref", ref).build().run();
        assertTrue(model.getResponse().findValues("Ref").size() > 0);
    }

    /**
     * В даному тесті можна робити запити:
     * 1. На отримання міста по Ref -- byRef("7110b9c1-92f3-11e2-a57a-d4ae527baec3");
     * 2. На отримання міста по назві -- byName("Бровари");
     * 3. На отримання усіх міст -- getAll();
     */
    @Test
    @DisplayName("Get Cities of company")
    void getCitiesOfCompanyTest() throws IOException {
        model = new GetCitiesOfCompany().byName("Ясіня").build().run();
        assertEquals("\"Ясіня\"", model.getResponse().findValue("Description").toString());
    }

    /**
     * В даному тесті можна робити запити:
     * 1. На отримання усіх міст -- getAll();
     * 2. На отримання населеного пункту по назві -- byName("Бровари");
     * 3. На отримання населеного пункту за ознакою "з відділеннями" або "без відділень" -- withWarehouses(true/false);
     * 4. На одну сторінку виводиться 150 записів. Тому є можливість вказувати сторінку результатів -- page(2);
     */
    @Test
    @DisplayName("Get Settlements of Ukraine")
    void getSettlementsUkraineTest() throws IOException {
        model = new GetSettlementsUkraine().byName("Бровари").build().run();
        assertTrue(model.getResponse().findValues("Ref").size() > 0);
    }

    @Test
    @DisplayName("Get geographical Regions of Ukraine")
    void getGeographicalRegionsTest() throws IOException {
        model = new GetGeographicalRegions().build().run();
        assertEquals(25, model.getResponse().findValues("Description").size());
    }

    /**
     * В даномі тесті можна робити запит на отримання вулиць за назвою або її частиною -- byStreetName("Гру");
     */
    @Test
    @DisplayName("Get streets of company")
    void getStreetsOfCompanyTest() throws IOException {
        model = new GetStreetsOfCompany().byStreetName("Гру").build().run();
        assertTrue(model.getResponse().findValues("Description").size() > 0);
    }

    //todo: написати JavaDoc та додаткові методи в класі
    @Test
    @DisplayName("Get warehouses and types of warehouses")
    void getWarehousesTest() throws IOException {
        model = new GetWarehouses().build().run();
        assertTrue(model.getResponse().findValues("SiteKey").size() > 0);
    }
}
