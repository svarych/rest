/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды обратной доставки груза
 * Метод «getBackwardDeliveryCargoTypes», работает в модели «Common», этот метод необходим для отображения списка видов
 * обратной доставки груза на украинском и на русском языках . Для обновления данных,
 * справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class CargoBackwardDeliveryTypes extends ModelBuilder {

    public CargoBackwardDeliveryTypes() {
        this
                .modelName("Common")
                .calledMethod("getBackwardDeliveryCargoTypes")
        ;
    }
}
