package com.mysite.sbb.Util;

public class Util {
    public static boolean empty(Object object) {
        if(object == null) {
            return true;
        }
        if(object instanceof String == false) {
            return true;
        }

        String str = (String) object;

        return str.trim().length() == 0;
    }
}
