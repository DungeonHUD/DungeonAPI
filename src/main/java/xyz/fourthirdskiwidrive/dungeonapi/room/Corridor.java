package xyz.fourthirdskiwidrive.dungeonapi.room;


public class Corridor extends Room {

    @Override
    public RoomType getRoomType() {
        return null;
    }

    public enum RoomShape {
        SQ_1_1,     // Square, 1x1
        RC_1_2,     // Rectangular, 1x2
        RC_1_3,     // Rectangular, 1x3
        RC_1_4,     // Rectangular, 4x4
        SQ_2_2,     // Square, 2x2
        LS_2_2      // L-Shaped, 2x2
    }
    protected RoomShape roomShape;
}
