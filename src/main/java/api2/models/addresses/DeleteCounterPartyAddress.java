/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Удалить адрес контрагента (отправитель/получатель)
 * Метод «delete», работает в модели «Address», этот метод необходим для удаления адреса контрагента отправителя/получателя.
 * Редактировать данные контрагента можно только с момента его создания до момента создания ИД с ним.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class DeleteCounterPartyAddress extends ModelBuilder {

    public DeleteCounterPartyAddress() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("delete")

                .addProperty("Ref", "ec4899cf-fb7f-11e7-becf-005056881c6b");
    }
}
