/*
 * Copyright (c) Artem Svarych (Tober), 2018.
 */

package api2.models.catalogue;

import api2.service.ModelBuilder;

/**
 * Формы оплаты
 * Метод «getPaymentForms», работает в модели «Common», этот метод позволяет загрузить список форм оплат на украинском
 * и на русском языках. Оплачивать услуги доставки с помощью безналичного расчета могут Клиенты, которые заключили
 * договор с компанией "Новая Почта". Необходимо сохранять копию справочников на стороне клиента и поддерживать
 * ее в актуальном состоянии. Рекомендуется проводить обновление справочников раз в месяц.
 * <p>
 * Доступность: Не требует использования API-ключа.
 */
public final class PaymentForms extends ModelBuilder {

    public PaymentForms() {
        this
                .modelName("Common")
                .calledMethod("getPaymentForms")
        ;
    }
}
