package xyz.fourthirdskiwidrive.dungeonapi.updatethread;

import net.minecraft.client.Minecraft;
import xyz.fourthirdskiwidrive.dungeonapi.ChunkSliceManager;


public class UpdateMapThread {
    private ChunkSliceManager csm;
    public static int UPDATE_TIME = 500;

    public UpdateMapThread() {
        csm = ChunkSliceManager.getInstance();
    }

    public void update() {
        if(Minecraft.getMinecraft().theWorld != null) {
            csm.update();
            csm.updateSizes();
        }
    }


}
