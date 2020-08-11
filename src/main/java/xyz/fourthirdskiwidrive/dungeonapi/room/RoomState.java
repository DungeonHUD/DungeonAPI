package xyz.fourthirdskiwidrive.dungeonapi.room;

import java.util.ArrayList;

public class RoomState {
    final Room room;
    final int orientation;
    final ArrayList<Boolean> openDoors;

    public enum RoomStatus {
        UNDISCOVERED,           //If the room is not on the map in slot 9 (or is greyed with a question mark)
        INCOMPLETE,             //If the room has been discovered, but is incomplete
        MAIN_COMPLETE,          //If the room has a green check mark (main objective complete)
        FULL_COMPLETE,          //If the room has all objectives and secrets complete
    }


    public RoomState(Room room, int orientation, ArrayList<Boolean> doorStates) {
        this.room = room;
        this.orientation = orientation;
        this.openDoors = doorStates;
    }

    public Corridor.RoomShape getRoomShape() {
        if(room instanceof Corridor) {
            return ((Corridor) room).roomShape;
        }

        else {
            return Corridor.RoomShape.SQ_1_1;
        }
    }
}
