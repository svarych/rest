package api2.service.enums;

public enum Format {
    PDF, HTML;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
