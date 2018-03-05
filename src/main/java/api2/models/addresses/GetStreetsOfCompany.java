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
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("Address")
                .calledMethod("getStreet")

                .addProperty("CityRef", "ebc0eda9-93ec-11e3-b441-0050568002cf")
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
