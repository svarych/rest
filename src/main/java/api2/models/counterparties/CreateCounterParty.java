/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.counterparties;

import api2.service.ModelBuilder;

/**
 * Создать Контрагента
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

    public CreateCounterParty firstName() {
        return this;
    }

    public CreateCounterParty middleName() {
        return this;
    }

    public CreateCounterParty lastName() {
        return this;
    }

    public CreateCounterParty email() {
        return this;
    }

    public CreateCounterParty phone() {
        return this;
    }

    /**
     * PrivatePerson or Organization
     */
    public CreateCounterParty type() {
        return this;
    }

    /**
     * Sender or Recipient
     */
    public CreateCounterParty senderOrRecipient() {
        return this;
    }
}
