package xyz.fourthirdskiwidrive.dungeonapi.room;

import java.util.ArrayList;

public class BloodRoom extends Room {
    public BloodRoom() {
        Secrets = new ArrayList<>(
        );
    }
    @Override
    public RoomType getRoomType() {
        return RoomType.BOSS;
    }
}
