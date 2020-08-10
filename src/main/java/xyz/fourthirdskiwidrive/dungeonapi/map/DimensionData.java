package xyz.fourthirdskiwidrive.dungeonapi.map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import xyz.fourthirdskiwidrive.dungeonapi.ChunkSlice;
import xyz.fourthirdskiwidrive.dungeonapi.ChunkSliceManager;

public class DimensionData {
    private static DimensionData instance;
    public static DimensionData getInstance() {
        if (instance == null) {
            instance = new DimensionData();
        }

        return instance;
    }

    public ChunkSlice getChunkSlice(int x, int z, Minecraft client) {
        World wc = client.theWorld;
        Chunk chunkZZ = wc.getChunkFromChunkCoords(x, z);
        if(chunkZZ == null) {
            ChunkSliceManager csm = ChunkSliceManager.getInstance();
            try {
                return csm.slices.get(x).get(z);
            } catch (IndexOutOfBoundsException e) {
                ChunkSlice emptyChunkSlice = new ChunkSlice();
                emptyChunkSlice.blocks = new Block[16][16];
                return emptyChunkSlice;
            }

        }
        Block[][] BlockSlice = new Block[16][16];

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                BlockPos blockPos = new BlockPos(i, 70, j);
                Block b = chunkZZ.getBlockState(blockPos).getBlock();
                BlockSlice[i][j] = b;
            }
        }

        ChunkSlice cs = new ChunkSlice();
        cs.blocks = BlockSlice;
        return cs;
    }
}
