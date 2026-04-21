package fr.mqrtin.utility.utils;

import fr.mqrtin.utility.Main;
import fr.mqrtin.utility.enums.LabelValue;
import fr.mqrtin.utility.module.modules.other.DebugModule;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

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

    public static void log(String message){
        System.out.println(message);
        DebugModule instance = DebugModule.getInstance(DebugModule.class);
        if(instance.isEnabled()){
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(message));
        }
    }
}
