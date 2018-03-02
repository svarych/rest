/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.ModelBuilder;

/**
 * Создать адрес контрагента (отправитель/получатель)
 * Метод «Save» , работает в модели «Address» , этот метод сохраняет адреса контрагента отправителя/получателя.
 */
public final class CreateCounterPartyAddress extends ModelBuilder {

    /**
     * Доступность: Требует использования API-ключа.
     */
    public CreateCounterPartyAddress() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("save")

                .addProperty("CounterpartyRef", "aa188fd1-75f1-11e7-8ba8-005056881c6b")
                .addProperty("StreetRef", "d4450bdb-0a58-11de-b6f5-001d92f78697")
                .addProperty("BuildingNumber", "10Ф")
                .addProperty("Flat", 2)
                .addProperty("Note", "Коментар");
    }
}
