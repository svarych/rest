/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды груза
 * Метод «getCargoTypes», работает в модели «Common», этот метод необходим для скачивания списка типов груза на Украинском языке.
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class CargoTypes extends ModelBuilder {

    public CargoTypes() {
        this
                .modelName("Common")
                .calledMethod("getCargoTypes")
        ;
    }
}
