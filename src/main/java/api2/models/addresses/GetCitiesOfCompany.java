/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Справочник городов компании
 * Получение справочника городов компании «Новая Почта» на украинском и русском языках.
 * <p>
 * Метод «getCities» работает в модели «Address», этот метод загружает справочник населенных пунктов Украины. Стоит
 * учитывать, справочник выгружается только с населенными пунктами где есть отделения "Нова Пошта" и можно оформить
 * доставку на отделение, а также на доставку по адресу.
 * <p>
 * Если в этот запрос добавить параметр «FindByString» (поиск по строкам) и в его свойствах прописать название населенного
 * пункта (Бровары), который нужно найти, то получим запрос с помощью которого в справочнике находится населенный пункт.
 * <p>
 * Необходимо сохранять копию справочников на стороне клиента и поддерживать ее в актуальном состоянии.
 * Рекомендуется проводить обновление справочников раз в сутки.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetCitiesOfCompany extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public GetCitiesOfCompany() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("getCities")

                .addProperty("Ref", "ebc0eda9-93ec-11e3-b441-0050568002cf");
    }

//    ==================================================================================================================

    public GetCitiesOfCompany getAll() {
        this.removeProperty("Ref");
        return this;
    }

    public GetCitiesOfCompany byRef(String ref) {
        this.replaceProperty("Ref", ref);
        return this;
    }

    public GetCitiesOfCompany byName(String name) {
        this
                .removeProperty("Ref")
                .addProperty("FindByString", name);
        return this;
    }
}
