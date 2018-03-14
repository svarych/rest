/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды плательщиков обратной доставки
 * Метод «getTypesOfPayersForRedelivery», работает в модели «Common», этот метод необходим для загрузки списка видов
 * плательщиков услуги обратной доставки на украинском и на русском языках: Отправитель, Получатель.
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class BackwardDeliveryPayerTypes extends ModelBuilder {

    public BackwardDeliveryPayerTypes() {
        this
                .modelName("Common")
                .calledMethod("getTypesOfPayersForRedelivery")
        ;
    }
}
