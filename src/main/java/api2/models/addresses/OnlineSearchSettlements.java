/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Онлайн поиск в справочнике населенных пунктов
 * Метод «searchSettlements», работает в модели «Address», этот метод необходим для ОНЛАЙН ПОИСКА населенных пунктов.
 * С данным методом нет необходимости хранить на своей стороне справочники и заботиться о их обновлениях.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class OnlineSearchSettlements extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public OnlineSearchSettlements() {
        this
                .modelName("Address")
                .calledMethod("searchSettlements")

                .addProperty("CityName", "Київ")
                .addProperty("Limit", 100);
    }
}
