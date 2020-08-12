package xyz.fourthirdskiwidrive.dungeonapi;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import xyz.fourthirdskiwidrive.dungeonapi.updatethread.UpdateMapThread;

import java.util.concurrent.*;

@Mod(modid = DungeonAPI.MODID, version = DungeonAPI.VERSION)
public class DungeonAPI
{
    public static final String MODID = "dungeonapi";
    public static final String VERSION = "0.0.0";

    public static ScheduledExecutorService exe;
    public static UpdateMapThread mapThread;

    public static ScheduledFuture<?> future;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {


        //System.out.printf("%s", RoomParser.ParseRoom(new ResourceID("/layouts/ExampleRoom.json")).getType());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        mapThread = new UpdateMapThread();
        exe = Executors.newScheduledThreadPool(1);
        future = exe.scheduleAtFixedRate(mapThread, 1000, 500, TimeUnit.MILLISECONDS);
    }
}
