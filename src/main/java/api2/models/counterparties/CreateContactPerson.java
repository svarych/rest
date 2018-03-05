/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.counterparties;

import api2.service.ModelBuilder;

/**
 * Создать Контактное лицо Контрагента
 * Метод «save», работает в модели «ContactPerson», этот метод необходим для создания контактного лица
 * контрагента отправителя/получателя.
 * <p>
 * Редактировать данные контактного лица контрагента могут только юридические лица.
 * Частные лица могут редактировать только телефон контактного лица контрагента.
 * Редактировать данные контрагента можно только с момента его создания до момента создания ИД с ним.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class CreateContactPerson extends ModelBuilder {

    public CreateContactPerson() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Counterparty")
                .calledMethod("save")

                .addProperty("CounterpartyRef", "e2adaba0-1dc3-11e7-8ba8-005056881c6b")
                .addProperty("FirstName", "Мамбу")
                .addProperty("MiddleName", "Рум")
                .addProperty("LastName", "Пуп")
                .addProperty("Phone", "0997979789")
                .addProperty("Email", "hara@mamba.rum")
        ;
    }

    //----------------------------------------------------------------------------------------------------------------------
    public CreateContactPerson counterpartyRef(String cityRef) {
        this.replaceProperty("CounterpartyRef", cityRef);
        return this;
    }

    public CreateContactPerson firstName(String firstName) {
        this.replaceProperty("FirstName", firstName);
        return this;
    }

    public CreateContactPerson middleName(String middleName) {
        this.replaceProperty("MiddleName", middleName);
        return this;
    }

    public CreateContactPerson lastName(String lastName) {
        this.replaceProperty("LastName", lastName);
        return this;
    }

    public CreateContactPerson email(String email) {
        this.replaceProperty("Email", email);
        return this;
    }

    public CreateContactPerson phone(String phone) {
        this.replaceProperty("Phone", phone);
        return this;
    }
}
