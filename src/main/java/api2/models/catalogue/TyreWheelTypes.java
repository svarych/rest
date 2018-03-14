/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды шин и дисков
 * Метод «getTiresWheelsList», работает в модели «Common», этот метод позволяет загрузить список шин и дисков
 * (используется, если вид груза «шины-диски») на Украинском и Русском языках. Необходимо сохранять копию справочников
 * на стороне клиента и поддерживать ее в актуальном состоянии. Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class TyreWheelTypes extends ModelBuilder {

    public TyreWheelTypes() {
        this
                .modelName("Common")
                .calledMethod("getTiresWheelsList")
        ;
    }
}
