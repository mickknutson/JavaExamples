package com.baselogic.tutorials.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Random;

public class StringUtilities {

    /**
     * Function: to boolean
     * Purpose: return true if T, True, Yes, Y, ON
     */
    public static boolean toBoolean(String value) {
        return ("True".equalsIgnoreCase(value) || "T".equalsIgnoreCase(value)
                || "Yes".equalsIgnoreCase(value) || "Y".equalsIgnoreCase(value))
                || "ON".equalsIgnoreCase(value);
    }

    public static boolean isNumber(String in) {
        try {
            Long.parseLong(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /**
     * This method should be used in place of:
     * Long.valueOf(callFlowProperty.getValue());
     * Long.parseLong(callFlowProperty.getValue());
     *
     * @param in
     * @return Long
     */
    public static Long parseNumber(String in) {
        Long result = -1L;
        try {
            result = Long.parseLong(in);
        } catch (NumberFormatException ex) {
            return result;
        }
        return result;
    }

    /**
     * Function: to yes
     * Purpose: return the Yes/No equivalent of the boolean value
     */
    public static String toYesNo(boolean value) {
        return (value ? "Yes" : "No");
    }

    /**
     * masks strings for payment info such as credit card, bank routing number, etc
     *
     * @param stringToBeMasked
     * @param trailingCharsToKeep
     * @return String
     */
    public static String mask(String stringToBeMasked, int trailingCharsToKeep) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(stringToBeMasked) && stringToBeMasked.length() >= trailingCharsToKeep) {
            for (int i = 0; i < stringToBeMasked.length() - trailingCharsToKeep; i++) {
                sb.append("*");
            }
            sb.append(stringToBeMasked.substring(stringToBeMasked.length() - trailingCharsToKeep));
        } else if (StringUtils.isNotBlank(stringToBeMasked)) {
            return mask(stringToBeMasked, stringToBeMasked.length());
        }

        return sb.toString();
    }

    /**
     * pad units (0-9) with a 0. e.g. 1 => 01
     *
     * @param units
     * @return String
     */

    public static String padUnitsWithZero(int units) {
        if (units < 10) {
            return "0" + units;
        } else {
            return Integer.toString(units);
        }
    }

    /**
     * Utility method to build a string out of given arguments.
     *
     * @param args
     * @return String
     */
    public static String buildArgInfoString(Object[] args) {
        StringBuilder sb = new StringBuilder();

        if (args != null && args.length > 0) {
            sb.append("[");
            for (Object arg : args) {
                if (arg != null && arg.getClass().isArray()) {
                    sb.append(arrayToString(arg));
                } else {
                    sb.append(arg);
                }
                sb.append(",");
            }

            sb.replace(sb.length() - 1, sb.length(), "]");
        } else {
            sb.append("[no args]");
        }

        return sb.toString();
    }


    /**
     * Converts an array to a string
     *
     * @param array
     * @return String
     */
    public static String arrayToString(Object array) {
        StringBuilder sb = new StringBuilder();

        if (array != null) {
            sb.append("[");
            for (int i = 0; i < Array.getLength(array); i++) {
                sb.append(Array.get(array, i));
                sb.append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), "]");
        }

        return sb.toString();
    }

    public static String getExceptionStackTraceString(Exception ex) {
        if (ex == null || ex.getStackTrace().length == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.getFileName()).append(":")
                    .append(element.getLineNumber()).append(">> ")
                    .append(element.getMethodName()).append("()")
                    .append("; ");
        }

        return sb.toString();
    }

    public static String getRandomString(int bindingLength){
        byte[] array = new byte[bindingLength]; // length is bounded by 7
        new Random().nextBytes(array);

        return new String(array, Charset.forName("UTF-8"));
    }
}
