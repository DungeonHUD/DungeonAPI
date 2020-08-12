package xyz.fourthirdskiwidrive.dungeonapi.updatethread;

import net.minecraft.client.Minecraft;
import xyz.fourthirdskiwidrive.dungeonapi.room.DungeonLayoutIdentifier;
import xyz.fourthirdskiwidrive.dungeonapi.util.ChunkSliceManager;



public class UpdateMapThread implements Runnable {
    private ChunkSliceManager csm;
    public static int UPDATE_TIME = 500;

    public UpdateMapThread() {
        csm = ChunkSliceManager.getInstance();
    }

    public void run() {
        try {
            if(Minecraft.getMinecraft().theWorld != null) {
                csm.update();
                csm.updateSizes();
                DungeonLayoutIdentifier dli = new DungeonLayoutIdentifier(csm);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}
