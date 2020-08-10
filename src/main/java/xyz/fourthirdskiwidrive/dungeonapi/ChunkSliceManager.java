package xyz.fourthirdskiwidrive.dungeonapi;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import xyz.fourthirdskiwidrive.dungeonapi.map.DimensionData;
import java.util.ArrayList;

public class ChunkSliceManager {
    public ArrayList<ArrayList<ChunkSlice>> slices = new ArrayList<>();
    private static ChunkSliceManager instance = null;

    public int xNeeded = 16;
    public int yNeeded = 16;

    public boolean updated = false;

    public static ChunkSliceManager getInstance() {
        if(instance == null) instance = new ChunkSliceManager();
        return instance;
    }

    public boolean hasChanged() {
        boolean uRecord = updated;
        updated = false;
        return uRecord;
    }

    public void update() {
        ArrayList<ArrayList<ChunkSlice>> chunkSlices = new ArrayList<>();
        DimensionData dimensionData = DimensionData.getInstance();
        Minecraft c = Minecraft.getMinecraft();
        for(int i = 0; i < 16; i++) {
            ArrayList<ChunkSlice> currentChunkRow = new ArrayList<>();
            for (int j = 0; j < 16; ++j) {
                ChunkSlice cs = dimensionData.getChunkSlice(i, j, c);
                currentChunkRow.add(cs);
            }
            chunkSlices.add(currentChunkRow);
        }

        slices = chunkSlices;
        updated = true;
    }

    public void updateSizes() {
        for(int i = this.slices.size() - 1; i >= 0; --i) {
            if (this.slices.get(i).get(0).isEmpty())
                xNeeded = i;
            if (this.slices.get(0).get(i).isEmpty())
                yNeeded = i;
        }
    }

    public Block getIndividualBlock(int x, int y) {
        Block ret;
        if(x >= 0 && y >= 0) {
            ret = slices.get(x >> 4).get(y >> 4).blocks[x & 15][y & 15];
        }
        else {
            ret = Blocks.air;
        }


        return ret;
    }
}
