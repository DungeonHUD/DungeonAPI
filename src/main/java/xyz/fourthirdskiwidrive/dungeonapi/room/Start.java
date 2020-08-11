package xyz.fourthirdskiwidrive.dungeonapi.room;

import java.util.ArrayList;

public class Start extends Room {
    public Start () {

        Secrets = new ArrayList<>(
        );
    }
    @Override
    public RoomType getRoomType() {
        return RoomType.START;
    }
}
