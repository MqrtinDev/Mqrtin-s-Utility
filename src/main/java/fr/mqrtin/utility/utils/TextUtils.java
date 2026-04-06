package fr.mqrtin.utility.utils;

import fr.mqrtin.utility.enums.LabelValue;

public class TextUtils {

    public static String format(String text, Object... args){
        if (text == null || text.isEmpty()) {
            return text;
        }

        for (int i = 0; i < args.length; i++) {
            String placeholder = "{" + i + "}";
            text = text.replace(placeholder, String.valueOf(args[i]));
        }

        return text;
    }

    public static String format(String text){

        for (LabelValue value : LabelValue.values()) {
            text = text.replaceAll("\\{" + value.getName() + "}", value.getValue());
        }

        return text;
    }
}
