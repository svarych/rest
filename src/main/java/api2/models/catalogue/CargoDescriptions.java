/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Описания груза
 * Метод «getCargoDescriptionList», работает в модели «Common», этот метод позволяет отдать описание груза на украинском и на русском языках.
 * "FindBy String": "абажур", - также доступен поиск по строкам, не обязательный параметр
 * Необходимо сохранять копию справочников на стороне клиента и поддерживать ее в актуальном состоянии.
 * Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class CargoDescriptions extends ModelBuilder {

    public CargoDescriptions() {
        this
                .modelName("Common")
                .calledMethod("getCargoDescriptionList")
        ;
    }

//    ------------------------------------------------------------------------------------------------------------------

    public CargoDescriptions name(String name) {
        this.addProperty("FindByString", name);
        return this;
    }

}
