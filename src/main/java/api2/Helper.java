package api2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {

    public Helper() {
        setDateTime();
    }

// DATE TIME -----------------------------------------------------------------------------------------------------------
    private String today;
    private LocalDateTime currentDate;

    private void setDateTime() {
        currentDate = LocalDateTime.now();
        today = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getToday() {
        return today;
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }
// END OF DATE TIME ----------------------------------------------------------------------------------------------------
}
