package xyz.fourthirdskiwidrive.dungeonapi;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ChunkSlice {
    public Block[][] blocks = new Block[16][16];

    public Boolean isEmpty() {
        for(Block[] i : blocks) {
            for(Block j : i) {
                if (j == null) continue;
                if (!(j == Blocks.air)) return false;
            }
        }
        return true;
    }
}
