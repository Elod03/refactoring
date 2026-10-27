package fr.efrei.transfret;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Dates {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formater(Date d) {
        if (d == null) {
            return "";
        }
        return FORMAT_DATE.format(d.toInstant().atZone(ZoneId.systemDefault()));
    }
}
