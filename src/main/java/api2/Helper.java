package api2;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

    public Helper() {
        setDate();
    }

    @Getter
    private String currentDate;

    // Current Date
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = format.format(calendar.getTime());
    }
}
