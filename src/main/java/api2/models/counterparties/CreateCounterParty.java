/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.counterparties;

import api2.service.ModelBuilder;

/**
 * Создать Контрагента
 * Так же используется для создания контактного лица
 *
 * Метод «save», работает в модели «Counterparty», этот метод используется при создании Контрагента получателя.
 * Все данные вносятся только на Украинском языке.
 * Рекомендуеться проводить обновление справочников раз в месяц.
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
                .addProperty("FirstName", "Мамбу")
                .addProperty("MiddleName", "Рум")
                .addProperty("LastName", "Рамамбахара")
                .addProperty("Phone", "0997979789")
                .addProperty("Email", "hara@mamba.rum")
                .addProperty("CounterpartyType", "PrivatePerson")
                .addProperty("CounterpartyProperty", "Recipient")
        ;
    }

//----------------------------------------------------------------------------------------------------------------------
    public CreateCounterParty cityRef(String cityRef) {
        this.replaceProperty("CityRef", cityRef);
        return this;
    }

    public CreateCounterParty firstName(String firstName) {
        this.replaceProperty("FirstName", firstName);
        return this;
    }

    public CreateCounterParty middleName(String middleName) {
        this.replaceProperty("MiddleName", middleName);
        return this;
    }

    public CreateCounterParty lastName(String lastName) {
        this.replaceProperty("LastName", lastName);
        return this;
    }

    public CreateCounterParty email(String email) {
        this.replaceProperty("Email", email);
        return this;
    }

    public CreateCounterParty phone(String phone) {
        this.replaceProperty("Phone", phone);
        return this;
    }

//----------------------------------------------------------------------------------------------------------------------

    /**
     * PrivatePerson or Organization
     */
    enum form {
        PrivatePerson, Organisation
    }

    public CreateCounterParty form(Enum form) {
        this.replaceProperty("CounterpartyType", form);
        return this;
    }
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Sender or Recipient
     */
    enum type {
        Sender, Recipient
    }

    public CreateCounterParty type(Enum type) {
        this.replaceProperty("CounterpartyProperty", type);
        return this;
    }
//----------------------------------------------------------------------------------------------------------------------
}
