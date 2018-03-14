/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Виды плательщиков
 * Метод «getTypesOfPayers», работает в модели «Common», этот метод необходим для загрузки списка видов плательщиков
 * услуги доставки на украинском и на русском языках: Отправитель, Получатель, Третье лицо.
 * Вид плательщика "Третье лицо" возможно заказать только после заключения договора с компанией "Новая Почта".
 * Для обновления данных, справочник необходимо загружать один раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class PayerTypes extends ModelBuilder {

    public PayerTypes() {
        this
                .modelName("Common")
                .calledMethod("getTypesOfPayers")
        ;
    }
}
