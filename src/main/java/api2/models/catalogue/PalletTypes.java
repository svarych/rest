/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды паллет
 * Метод «getPalletsList», работает в модели «Common», этот метод необходим для получения списка видов паллет.
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class PalletTypes extends ModelBuilder {

    public PalletTypes() {
        this
                .modelName("Common")
                .calledMethod("getPalletsList")
        ;
    }
}
