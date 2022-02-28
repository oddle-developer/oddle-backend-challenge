package com.oddle.app.weather.util;

import java.util.List;

public class ObjectUtil {

    public static boolean isExist(Object object) {
        if (object != null) {
            if (object instanceof String) {
                String stringData = (String) object;
                if (stringData.equalsIgnoreCase("null")) {
                    return false;
                } else {
                    return !((String) object).isEmpty();
                }
            } else if (object instanceof List) {
                return ((List) object).size() > 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
