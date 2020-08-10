package xyz.fourthirdskiwidrive.dungeonapi.api;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;

public class Status {
    public static boolean isInWorld() {
        return Minecraft.getMinecraft().theWorld != null;
    }

    public static boolean isOnHypixel() {
        Minecraft mc = Minecraft.getMinecraft();
        if(mc.getCurrentServerData().serverIP.contains("hypixel.net"))
            return true;

        return false;
    }

    public static boolean isInDungeon() {
        return Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(8).getItem() == Items.filled_map;
    }
}
