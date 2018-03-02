/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.ModelBuilder;

/**
 * Онлайн поиск в справочнике населенных пунктов
 * Метод «searchSettlements», работает в модели «Address», этот метод необходим для ОНЛАЙН ПОИСКА населенных пунктов.
 * С данным методом нет необходимости хранить на своей стороне справочники и заботиться о их обновлениях.
 */
public final class SearchSettlements extends ModelBuilder {

    /**
     * Доступность: Не требует использования API-ключа.
     */
    @SuppressWarnings("SpellCheckingInspection")
    public SearchSettlements() {
        this
                .modelName("Address")
                .calledMethod("searchSettlements")

                .addProperty("CityName", "Київ")
                .addProperty("Limit", 100);
    }
}
