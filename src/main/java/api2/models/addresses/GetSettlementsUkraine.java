/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.addresses;

import api2.service.ModelBuilder;

/**
 * Справочник населенных пунктов Украины
 * Метод «getSettlements» работает в модели «AddressGeneral», позволяет загрузить справочник городов Украины
 * (на Украинском или Русском), в которые осуществляется доставка груза компанией «Новая Почта».
 * Стоит учитывать, что метод «getSettlements» для каждого населенного пункта возвращает область, и район. Метод отдает
 * не более 150 записей на страницу. Для просмотра более 150 записей, необходимо использовать параметр "Page": "1",
 * <p>
 * Также в методе доступен поиск по строке, для него нужно указать параметр FindByString.
 * <p>
 * Важно! Поиск по строке возможен только на Украинском языке.
 * <p>
 * Необходимо сохранять копию справочников на стороне клиента и поддерживать ее в актуальном состоянии.
 * Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Параметр "Warehouse": "1 или 0" позволит отобразить только те населенные пункты в которых есть отделения "Нова пошта"
 * <p>
 * Доступность: Требует использования API-ключа.
 */
public final class GetSettlementsUkraine extends ModelBuilder {

    @SuppressWarnings("SpellCheckingInspection")
    public GetSettlementsUkraine() {
        this
                .apiKey("38d9f4c9c98686aca629634a245d7828")
                .modelName("AddressGeneral")
                .calledMethod("getSettlements")

                .addProperty("Ref", "e715719e-4b33-11e4-ab6d-005056801329")
                .addProperty("RegionRef", "e4aeec8a-4b33-11e4-ab6d-005056801329")
                .addProperty("AreaRef", "dcaadb64-4b33-11e4-ab6d-005056801329")
        ;
    }

//    ==================================================================================================================

    /**
     * Метод дозволяє отримати населений пункт за назвою
     */
    public GetSettlementsUkraine byName(String name) {
        this
                .removeProperty("AreaRef")
                .removeProperty("Ref")
                .removeProperty("RegionRef")

                .addProperty("FindByString", name);
        return this;
    }

    /**
     * Метод дозволяє отримати населений пункт за ознакою "з відділеннями" або "без відділень" -- withWarehouses(true/false)
     */
    public GetSettlementsUkraine withWarehouses(boolean condition) {
        if (condition) {
            this.addProperty("Warehouse", 1);
        } else this.addProperty("Warehouse", 0);
        return this;
    }

    /**
     * Метод дозволяє отримати частину населених пунктів, що видаються системою на відповідній сторінці
     * На одну сторінку виводиться 150 записів. Тому є можливість вказувати сторінку результатів -- page(2);
     */
    public GetSettlementsUkraine page(int page) {
        this.addProperty("Page", page);
        return this;
    }

    /**
     * Метод дозволяє отримати усі міста
     */
    public GetSettlementsUkraine getAll() {
        this
                .removeProperty("Ref")
                .removeProperty("RegionRef")
                .removeProperty("AreaRef");
        return this;
    }
}
