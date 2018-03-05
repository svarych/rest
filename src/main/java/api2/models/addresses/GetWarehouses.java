/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Справочник отделений и типов отделений
 * Метод «getWarehouses», работает в модели «Address», этот метод загружает справочник отделений «Новая Почта» в рамках населенных пунктов Украины.
 * Есть возможность отфильтровать нужные отделения по следующим признакам:
 * 1) TypeOfWarehouseRef 2) PostFinance 3) BicycleParking 4) POSTerminal 5) CityName 6) CityRef
 * <p>
 * Предусмотрена возможность быстрого получения отделений в конкретном городе/нас. пункте или поселке, при помощи метода getSettlements
 * Пример:
 * "methodProperties": {"SettlementRef": "e71629ab-4b33-11e4-ab6d-005056801329"} //РЕФ города из справочника населенных пунктов Украины
 * <p>
 * Если заменить «getWarehouses» на «getWarehouseTypes», можно получить справочник типов отделений «Новая Почта».
 * Копию справочника необходимо сохранять и поддерживать в актуальном состоянии путем обновления раз в сутки.
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetWarehouses extends ModelBuilder {

    public GetWarehouses() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("AddressGeneral")
                .calledMethod("getWarehouses")
        ;
    }
}
