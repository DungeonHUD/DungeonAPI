package xyz.fourthirdskiwidrive.dungeonapi.room;

import java.util.ArrayList;

public class Miniboss extends Room {
    public Miniboss () {
        super();
        Secrets = new ArrayList<>(
        );
    }
    @Override
    public RoomType getRoomType() {
        return RoomType.MINIBOSS;
    }
}