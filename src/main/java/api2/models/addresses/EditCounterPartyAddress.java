/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Редактировать адрес контрагента (отправитель/получатель)
 * Метод «update», работает в модели «Address», этот метод необходим для обновления адреса контрагента отправителя/получателя.
 * Редактировать данные контрагента можно только с момента его создания и до момента создания интернет документа с ним.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class EditCounterPartyAddress extends ModelBuilder {

    public EditCounterPartyAddress() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("update")

                .addProperty("CounterpartyRef", "aa188fd1-75f1-11e7-8ba8-005056881c6b")
                .addProperty("StreetRef", "d4450bdb-0a58-11de-b6f5-001d92f78697")
                .addProperty("Ref", "ec4899cf-fb7f-11e7-becf-005056881c6b") // Ref адреси, яку потрібно змінити
                .addProperty("BuildingNumber", "10Ф")
                .addProperty("Flat", 2)
                .addProperty("Note", "Відредагований коментар");
    }
}
