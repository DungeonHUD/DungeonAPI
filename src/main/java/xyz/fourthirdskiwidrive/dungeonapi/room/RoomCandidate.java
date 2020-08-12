package xyz.fourthirdskiwidrive.dungeonapi.room;

import xyz.fourthirdskiwidrive.dungeonapi.util.CoordinatePair;

import java.util.ArrayList;
import java.util.List;

public class RoomCandidate {
    public List<CoordinatePair> sections = new ArrayList<>();
    public boolean isStartingRoom;
}
