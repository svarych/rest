/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Справочник улиц компании
 * Метод «getStreet» работает в модели «Address», этот метод загружает справочник улиц в рамках населенных пунктов Украины
 * куда осуществляет доставку компания «Новая Почта». Справочник используется при создании отправлений с типом
 * доставки от/до адреса клиента.
 * Если в этот запрос добавить параметр «FindByString» (поиск по строкам) и в его свойствах прописать название улицы
 * (Броварський), который нужно найти, то получим запрос с помощью которого в справочнике находится проспект или улица.
 * <p>
 * Копию справочника необходимо сохранять и поддерживать в актуальном состоянии путем обновления раз в сутки.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetStreetsOfCompany extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public GetStreetsOfCompany() {
        this
                .apiKey("88480f5e25e304a397605165c6f12d7c")
                .modelName("Address")
                .calledMethod("getStreet")

                .addProperty("CityRef", "e718a680-4b33-11e4-ab6d-005056801329")
        ;
    }

//    ==================================================================================================================

    /**
     * Метод дозволяє отримати список вулиць по назві, або частині назви
     */
    public GetStreetsOfCompany byStreetName(String name) {
        this.addProperty("FindByString", name);
        return this;
    }
}
