/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.redirecting;

import api2.service.ModelBuilder;

/**
 * Создание заявки переадресация отправления (отделение/адрес)
 * Метод «save», работает в модели «AdditionalServiceGeneral», этот метод позволяет создать заявку переадресации
 * отправления на отделение или на адрес.
 * <p>
 * Метод доступен только для клиентов отправителей.
 * <p>
 * Доступность, Требует использования API-ключа.
 */
public final class CreateRedirectingRequest extends ModelBuilder {

    public CreateRedirectingRequest(Target target) {
        this
                .apiKey("8f7e235f0afe315e2e81e10ac9d5c914")
                .modelName("AdditionalServiceGeneral")
                .calledMethod("save")
                .addProperty("OrderType", "orderRedirecting")
                .addProperty("Recipient", "0c38cdbe-84b9-11e6-af9a-005056886752") //Фізична особа, тест. корпорат
        ;

        if (target == Target.WAREHOUSE) {
            this
                    .addProperty("IntDocNumber", "")
                    .addProperty("Customer", "Sender")
                    .addProperty("ServiceType", "DoorsWarehouse")
                    .addProperty("RecipientWarehouse", "1ec09d88-e1c2-11e3-8c4a-0050568002cf")
                    .addProperty("RecipientContactName", "Лололо Степан Степанович")
                    .addProperty("RecipientPhone", "380671234567")
                    .addProperty("PayerType", "Recipient")
                    .addProperty("PaymentMethod", "Cash")
                    .addProperty("Note", "какая-то причина переадресации на отделение");
        }

        if (target == Target.ADDRESS) {
            this
                    .addProperty("IntDocNumber", "")
                    .addProperty("Customer", "Recipient")
                    .addProperty("ServiceType", "DoorsDoors")
                    .addProperty("RecipientSettlement", "e718a680-4b33-11e4-ab6d-005056801329")
                    .addProperty("RecipientSettlementStreet", "2190bb8f-6856-11e6-8304-00505688561d")
                    .addProperty("BuildingNumber", "4")
                    .addProperty("NoteAddressRecipient", "у дворі")
                    .addProperty("RecipientContactName", "Вова Зі Львова")
                    .addProperty("RecipientPhone", "380999999999")
                    .addProperty("PayerType", "Recipient")
                    .addProperty("PaymentMethod", "Cash")
                    .addProperty("Note", "какая-то причина переадресации на адрес");
        }
    }

    public enum Target {
        WAREHOUSE, ADDRESS
    }

    public CreateRedirectingRequest docNumber(String number) {
        this.replaceProperty("IntDocNumber", number);
        return this;
    }
}
