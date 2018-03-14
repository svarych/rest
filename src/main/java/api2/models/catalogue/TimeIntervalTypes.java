/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.Helper;
import api2.service.ModelBuilder;

/**
 * Виды временных интервалов
 * Метод «getTimeIntervals», работает в модели «Common», этот метод необходим для получения списка временных интервалов
 * (для заказа услуги "Временные интервалы"). Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class TimeIntervalTypes extends ModelBuilder {

    public TimeIntervalTypes() {
        this
                .modelName("Common")
                .calledMethod("getTimeIntervals")

                .addProperty("RecipientCityRef", "db5c8892-391c-11dd-90d9-001a92567626")
                .addProperty("DateTime", new Helper().getToday())
        ;
    }

//    ------------------------------------------------------------------------------------------------------------------

    public TimeIntervalTypes recipientCityRef(String ref) {
        this.replaceProperty("RecipientCityRef", ref);
        return this;
    }

}
