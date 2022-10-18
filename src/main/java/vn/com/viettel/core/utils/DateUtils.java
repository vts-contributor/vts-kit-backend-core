package vn.com.viettel.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static String convertDateToString(Date date, String formatPattern) {
        if (date == null) {
            return null;
        } else {
            formatPattern = formatPattern == null ? "dd/MM/yyyy" : formatPattern;
            SimpleDateFormat dateFormat = getSimpleDateFormat(formatPattern);
            return dateFormat.format(date);
        }
    }

    /**
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date convertStringToDate(String date) throws Exception {
        if (date == null || date.trim().isEmpty()) {
            return null;
        } else {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            try {
                return dateFormat.parse(date);
            } catch (ParseException ex) {
                LOGGER.debug(ex.toString());
                return null;
            }
        }
    }

    /**
     * Chuyen doi tuong Date thanh doi tuong String
     *
     * @param date Doi tuong Date
     * @param formatPattern Kieu format ngay thang
     * @return Xau ngay voi kieu format truyen vao
     * @throws ParseException
     */
    public static Date convertStringToDate(String date, String formatPattern) throws ParseException {
        if (date == null || "".equals(date)) {
            return null;
        } else {
            String tg;
            tg = formatPattern == null ? "dd/MM/yyyy" : formatPattern;
            SimpleDateFormat dateFormat = new SimpleDateFormat(tg);
            return dateFormat.parse(date);
        }
    }

    /**
     *
     * @param formatPattern
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String formatPattern) {
        return new SimpleDateFormat(formatPattern);
    }
}
