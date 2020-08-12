package xyz.fourthirdskiwidrive.dungeonapi.util;


import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatPrinter {
    public static void print(String str) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(str));
        //System.err.println(str);
    }
}
