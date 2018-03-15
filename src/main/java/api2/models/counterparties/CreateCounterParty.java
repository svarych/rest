/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.counterparties;

import api2.service.Model;
import api2.service.ModelBuilder;

import java.io.IOException;

/**
 * Создать Контрагента с типом (юридическое лицо) или организацию
 * Метод «save», работает в модели «Counterparty», этот метод используется при создании Контрагента с типом
 * (юридическое лицо) или организация. Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * При создании организация поля: MiddleName, LastName, Phone, Email не обязательны!
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class CreateCounterParty extends ModelBuilder {

    public CreateCounterParty() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Counterparty")
                .calledMethod("save")

                .addProperty("CityRef", "db5c88d7-391c-11dd-90d9-001a92567626")
                .addProperty("FirstName", "Супер фірма")
                .addProperty("CounterpartyType", "Organization")
                .addProperty("CounterpartyProperty", "Recipient")
                .addProperty("OwnershipForm", "7f0f351d-2519-11df-be9a-000c291af1b3")
        ;
    }

//----------------------------------------------------------------------------------------------------------------------

    public CreateCounterParty cityRef(String cityRef) {
        this.replaceProperty("CityRef", cityRef);
        return this;
    }

    public CreateCounterParty name(String firstName) {
        this.replaceProperty("FirstName", firstName);
        return this;
    }

    public CreateCounterParty form(String ref) {
        this.replaceProperty("OwnershipForm", ref);
        return this;
    }

    public CreateCounterParty city(String ref) {
        this.replaceProperty("CityRef", ref);
        return this;
    }

    public CreateCounterParty printCityRefByName(String name) throws IOException {
        Model model = new ModelBuilder().apiKey(getApiKey()).modelName("Address").calledMethod("getCities").addProperty("FindByString", name)
                .build().run();
        System.out.println("Ref: " + clear(model.getResponse().findValues("Ref").toString()));
        return this;
    }

    public CreateCounterParty printCurrentCity() throws IOException {
        Model model = new ModelBuilder().apiKey(getApiKey()).modelName("Address").calledMethod("getCities").addProperty("Ref", getMethodProperties().get("CityRef"))
                .build().run();
        System.out.println("Поточне місто: " + clear(model.getResponse().findValue("Description").toString()));
        return this;
    }
}
