/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

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
                .addProperty("Limit", 5);
    }
}
