/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Технологии доставки
 * Метод «getServiceTypes», работает в модели «Common», этот метод позволяет загрузить список типов технологий доставки:
 * «склад-склад», «двери-двери», «склад-двери», «двери-склад» на украинском и на русском языках. Необходимо сохранять
 * копию справочников на стороне клиента и поддерживать ее в актуальном состоянии.
 * Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class DeliveryTechnologies extends ModelBuilder {

    public DeliveryTechnologies() {
        this
                .modelName("Common")
                .calledMethod("getServiceTypes")
        ;
    }
}
