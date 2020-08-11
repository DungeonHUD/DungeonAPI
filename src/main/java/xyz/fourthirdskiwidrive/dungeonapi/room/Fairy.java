package xyz.fourthirdskiwidrive.dungeonapi.room;

import java.util.ArrayList;

public class Fairy extends Room {
    public Fairy () {

        Secrets = new ArrayList<> (
        );
    }

    @Override
    public RoomType getRoomType() {
        return RoomType.FAIRY;
    }
}
