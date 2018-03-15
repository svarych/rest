/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.counterparties;

import api2.service.ModelBuilder;

/**
 * Создать Контрагента
 * Так же используется для создания контактного лица
 * <p>
 * Метод «save», работает в модели «Counterparty», этот метод используется при создании Контрагента получателя.
 * Все данные вносятся только на Украинском языке.
 * Рекомендуеться проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Требует использования API-ключа.
 * <p>
 * При створенні контрагента - фізичної особи, необхідно одночасно створити і контактну особбу, тому при стандартному
 * запиті отримуємо 2 об'єкти: крнтрагента (в даному випадку - "Фізична особа") та контактної особи -
 * (Рамамбахара Мамбу Рум)
 *
 */
public final class CreateCounterPartyWithCP extends ModelBuilder {

    public CreateCounterPartyWithCP() {
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
    public CreateCounterPartyWithCP cityRef(String cityRef) {
        this.replaceProperty("CityRef", cityRef);
        return this;
    }

    public CreateCounterPartyWithCP firstName(String firstName) {
        this.replaceProperty("FirstName", firstName);
        return this;
    }

    public CreateCounterPartyWithCP middleName(String middleName) {
        this.replaceProperty("MiddleName", middleName);
        return this;
    }

    public CreateCounterPartyWithCP lastName(String lastName) {
        this.replaceProperty("LastName", lastName);
        return this;
    }

    public CreateCounterPartyWithCP email(String email) {
        this.replaceProperty("Email", email);
        return this;
    }

    public CreateCounterPartyWithCP phone(String phone) {
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

    public CreateCounterPartyWithCP form(Enum form) {
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

    public CreateCounterPartyWithCP type(Enum type) {
        this.replaceProperty("CounterpartyProperty", type);
        return this;
    }
//----------------------------------------------------------------------------------------------------------------------
}
