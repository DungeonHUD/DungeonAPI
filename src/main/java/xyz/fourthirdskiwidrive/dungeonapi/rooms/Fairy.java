package xyz.fourthirdskiwidrive.dungeonapi.rooms;

import java.util.ArrayList;

public class Fairy extends Room {
    public Fairy (int x, int z, int r) {
        super(x, z, r);

        Secrets = new ArrayList<> (
        );
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.FAIRY;
    }
}
