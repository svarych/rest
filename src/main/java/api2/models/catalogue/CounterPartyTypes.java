/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Типы контрагентов
 * Метод «getTypesOfCounterparties», работает в модели «Common», этот метод позволяет загрузить список типов контрагентов
 * на украинском и на русском языках. Необходимо сохранять копию справочников на стороне клиента и поддерживать ее
 * в актуальном состоянии. Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class CounterPartyTypes extends ModelBuilder {

    public CounterPartyTypes() {
        this
                .modelName("Common")
                .calledMethod("getTypesOfCounterparties")
        ;
    }
}
