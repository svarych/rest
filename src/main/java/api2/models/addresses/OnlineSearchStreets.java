/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;
import api2.service.enums.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Онлайн поиск улиц в справочнике населенных пунктов
 * Метод «searchSettlementStreets», работает в модели «Address», этот метод необходим для ОНЛАЙН ПОИСКА улиц в выбранном
 * населенном пункте. С данным методом нет необходимости хранить на своей стороне справочники и заботиться об их обновлениях.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class OnlineSearchStreets extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public OnlineSearchStreets() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("searchSettlementStreets")

                .addProperty("StreetName", "Незалежност")
                .addProperty("SettlementRef", "e715719e-4b33-11e4-ab6d-005056801329")
//                .addProperty("SettlementRef","0db2df4b-4b3a-11e4-ab6d-005056801329")
                .addProperty("Limit", 5);
    }

//    ------------------------------------------------------------------------------------------------------------------

    public OnlineSearchStreets street(String street) {
        this
                .replaceProperty("StreetName", street);
        return this;
    }

    public OnlineSearchStreets settlementRef(String settlementRef) {
        this
                .replaceProperty("SettlementRef", settlementRef);
        return this;
    }

    public OnlineSearchStreets settlementName(String name) throws IOException {
        String ref = clear(new GetSettlementsUkraine().byName(name)
                .build().printRequest().run().getResponse().findValue("Ref").toString());
        this
                .replaceProperty("SettlementRef", ref);
        return this;
    }

    public OnlineSearchStreets limit(int limit) {
        this
                .replaceProperty("Limit", limit);
        return this;
    }

//    ------------------------------------------------------------------------------------------------------------------

    @Test
    void printSettlementsList() throws IOException {
        new GetSettlementsUkraine().byName("Київ")
                .apiKey("1a85cdffb52d67959d040ce9d517ab4c")
                .build().printRequest().run(Server.TEST).printPrettyResponse();
    }

    @Test
    void printCityRefByCityNameByCityName() throws IOException {
        String name = "Київ";
        System.out.println(clear(new GetCitiesOfCompany().byName(name)
                .build().printRequest().run().getResponse().findValue("Ref").toString()));
    }

    @Test
    void printStreet() throws IOException {
//        new OnlineSearchStreets().settlementName("Київ").street("Миколи Закревського")
        new OnlineSearchStreets().settlementRef("e718a680-4b33-11e4-ab6d-005056801329").street("Миколи Закревського")
                .build().printPrettyRequest().run().printPrettyResponse();
    }

    @Test
    void printCityAsCity() throws IOException {
        new GetCitiesOfCompany().byName("Львів")
                .build().printRequest().run().printPrettyResponse();
    }

    @Test
    void printCityAsSettlement() throws IOException {
        new GetSettlementsUkraine().byName("Львів")
                .build().printRequest().run().printPrettyResponse();
    }
}
