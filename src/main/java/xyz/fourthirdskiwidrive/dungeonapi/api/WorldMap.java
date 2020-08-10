package xyz.fourthirdskiwidrive.dungeonapi.api;

import net.minecraft.block.Block;
import xyz.fourthirdskiwidrive.dungeonapi.util.ChunkSliceManager;

public class WorldMap {
    /**
     *
     * @param x The X coordinate of the block
     * @param z The Z coordinate of the block
     * @return  The block at y=70 at the specified coordinates
     */
    public static Block getBlockAt(int x, int z) {
        assert ChunkSliceManager.getInstance().xNeeded * 16 >= x;
        assert ChunkSliceManager.getInstance().yNeeded * 16 >= z;

        return ChunkSliceManager.getInstance().getIndividualBlock(x, z);
    }
}
