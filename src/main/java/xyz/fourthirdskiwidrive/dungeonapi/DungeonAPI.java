package xyz.fourthirdskiwidrive.dungeonapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import xyz.fourthirdskiwidrive.dungeonapi.updatethread.UpdateMapThread;

import javax.swing.*;
import java.awt.event.ActionListener;

@Mod(modid = DungeonAPI.MODID, version = DungeonAPI.VERSION)
public class DungeonAPI
{
    public static final String MODID = "dungeonapi";
    public static final String VERSION = "0.0.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        UpdateMapThread mapThread = new UpdateMapThread();
        ActionListener mapThreadUpdateTrigger = e -> mapThread.update();

        Timer timer = new Timer(500, mapThreadUpdateTrigger);
        timer.setRepeats(true);
        timer.start();
    }
}
