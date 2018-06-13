package webclient;

public enum Ownership {

    PRODUCTION_COOPERAVIVE("Виробничий кооператив"),
    STATE_ENTERPRISE("Державне підприємство"),
    MUNICIPAL_ENTERPRISE("Комунальне підприємство"),
    LIMITED_LIABILITY("Командитне товариство"),
    PUBLIC_COMPANY("Публічне акціонерне товариство"),
    FOREIGN_INVESTMENTS("Підприємство з іноземними інвестиціями"),
    PRIVATE_ENTERPRISE("Приватне підприємство (не приватний підприємець)"),
    JOINT_STOCK("Акціонерне товариство (ПАТ, ПрАТ, ВАТ, ЗАТ)"),
    FULL_COMPANY("Повне товариство"),
    JOINT_VENTURE("Спільне підприємство"),
    ADDITIONAL_LIABILITY("Товариство з додатковою відповідальністю"),
    LIMITED_LIABILITY_COMPANY("Товариство з обмеженою відповідальністю"),
    FARM("Фермерське господарство"),
    INDIVIDUAL_ENTREPRENEUR("Фіз. особа-підприємець (лише приватний підприємець)")
 ;

    private final String name;

    Ownership(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
