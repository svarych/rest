package api2.service.enums;

/**
 * есть обычные. к ним может добавлятся признак перманентности Permanent - когда они не заканчиваются. и к ним может добавлятся признак партнерской площадки MarketplacePartnerRef
 * в етот признак ложится реф
 * всего есть 40 типов рефов
 * по источникам ето
 * Мобільний додаток
 * SiteCard
 * ЛК 3.0
 * остальные 37 ето просто разные клиенты.
 * <p>
 * весь список выглядит так
 * <p>
 * 05.com.ua
 * 1C_Module
 * A-shop
 * all biz
 * Amway
 * Ants
 * AvtoPro
 * Comfy
 * Fotos
 * LaModa
 * liqpay
 * modnaKasta
 * Nails Mania
 * NPI
 * OLX
 * OLX_discount
 * OpenCart
 * Parfums.ua
 * Prom.ua
 * Prom.ua_SecurePayment
 * RIA
 * Rozetka
 * Shopping Mall
 * SiteCard
 * ucoz
 * Ukrop
 * Violity
 * Widget Map
 * Widget_Calc
 * Widget_tracking
 * Zakupka.ua
 * АвтоМода
 * Бест Тайм
 * Гарна мама
 * Клуб семейного досуга
 * Колесико
 * ЛК 3.0
 * Магазин контактных линз
 * Мобільний додаток
 * Сitrus
 */
public enum KeyType {
    EMPTY, ONE, TWO, THREE;

    @Override
    public String toString() {
        String s = super.toString();
        String s1[] = s.split("_");
        StringBuilder builder = new StringBuilder();
        for (String str: s1){
            builder.append(str.substring(0, 1)).append(str.substring(1).toLowerCase());
        }
        return builder.toString();
    }
}
