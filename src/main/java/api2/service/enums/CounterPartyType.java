package api2.service.enums;

public enum CounterPartyType {
    CORPORATE, LOYALTY;


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
