package xyz.fourthirdskiwidrive.dungeonapi.room;


import xyz.fourthirdskiwidrive.dungeonapi.util.ResourceID;

import java.io.InputStream;

public class Corridor extends Room {
    public enum RoomShape {
        SQ_1_1,     // Square, 1x1
        RC_1_2,     // Rectangular, 1x2
        RC_1_3,     // Rectangular, 1x3
        RC_1_4,     // Rectangular, 4x4
        SQ_2_2,     // Square, 2x2
        LS_2_2      // L-Shaped, 2x2
    }
    protected RoomShape roomShape;
    public Corridor (RoomShape roomShape, ResourceID Layout) {
        this.roomShape = roomShape;
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.CORRIDOR;
    }

    public static Corridor inferCorridorType(DungeonLayoutIdentifier.RoomSection[][] section) {
        return new Corridor(RoomShape.SQ_1_1, new ResourceID("no.csv")) {

        };
    }
}
