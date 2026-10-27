package fr.efrei.transfret;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

    public static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");

    public static String formater(Date d) {
        if (d == null) {
            return "";
        }
        return FORMAT_DATE.format(d);
    }
}
