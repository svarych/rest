/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Формы собственности
 * Метод «getOwnershipFormsList», работает в модели «Common», этот метод позволяет загрузить список форм собственности
 * на Украинском языке. Необходимо сохранять копию справочников на стороне клиента и поддерживать ее
 * в актуальном состоянии. Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class OwnershipTypes extends ModelBuilder {

    public OwnershipTypes() {
        this
                .modelName("Common")
                .calledMethod("getOwnershipFormsList")
        ;
    }
}
