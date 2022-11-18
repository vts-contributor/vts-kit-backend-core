package vn.com.viettel.core.utils;

import vn.com.viettel.core.config.I18n;
import vn.com.viettel.core.exception.ValidateException;

import java.util.regex.Pattern;

public class ValidatorUtils {
    private static final String VIETTEL_PREFIX = "^\\w+([\\.-]?\\w+)*@viettel.com.vn+$";

    public static void isNullOrEmpty(String value, String msgCode) {
        String message = msgCode == null ? "Trường không được trống" : I18n.getMessage(msgCode);
        if(StringUtils.isNullOrEmpty(value)) throw new ValidateException(message);
    }

    public static void isEmail(String value, String msgCode) {
        String message = msgCode == null ? "Email không hợp lệ" : I18n.getMessage(msgCode);
        String regex = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        if(!Pattern.matches(regex, value)) throw new ValidateException(message);
    }

    public static void isNumber(String value, String msgCode) {
        String message = msgCode == null ? "Trường chỉ được chứa số" : I18n.getMessage(msgCode);
        String regex = "\\d+";
        if(!Pattern.matches(regex, value)) throw new ValidateException(message);
    }

    public static void isSpecialCharacter(String value, String msgCode) {
        String message = msgCode == null ? "Trường không được chứa ký tự đặc biệt" : I18n.getMessage(msgCode);
        String regex = "^[_A-z0-9]*((-|\\s)*[_A-z0-9])*$";
        if(!Pattern.matches(regex, value)) throw new ValidateException(message);
    }

    public static void isTrim(String value, String msgCode) {
        String message = msgCode == null ? "Trường không được có ký tự trắng" : I18n.getMessage(msgCode);
        if(value.indexOf(" ") != -1) throw new ValidateException(message);
    }


    /**
     * Checks if an Email is Viettel Email.
     *
     * @param str the String to check, may be null
     *
     * @return {@code true} if the String is Viettel email
     *
     * @since Commons Lang v2.1, svn 240418
     */
    static boolean isVTEmail(final String str)
    {
        return str != null && !str.isEmpty() && str.matches(VIETTEL_PREFIX);
    }

}
