package xyz.fourthirdskiwidrive.dungeonapi.room;

import net.minecraft.init.Blocks;
import xyz.fourthirdskiwidrive.dungeonapi.util.ChatPrinter;
import xyz.fourthirdskiwidrive.dungeonapi.util.ChunkSliceManager;
import xyz.fourthirdskiwidrive.dungeonapi.util.CoordinatePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DungeonLayoutIdentifier {
    public enum RoomWallType {
        ROOM_WALL_TYPE_CONNECTED,
        ROOM_WALL_TYPE_SEALED,
        ROOM_WALL_TYPE_DOOR,
        ROOM_WALL_TYPE_BLOOD_DOOR,
    }

    public static class Direction {
        public static final int NORTH = 0;
        public static final int EAST  = 1;
        public static final int SOUTH = 2;
        public static final int WEST  = 3;
    }

    static class Wall {
        public int parentSectionX;
        public int parentSectionZ;
        public int direction;

        public Wall(int x, int z, int dir) {
            this.parentSectionX = x;
            this.parentSectionZ = z;
            this.direction = dir;
        }
    }

    private ChunkSliceManager csm;
    private Queue<Wall> doorQueue = new LinkedList<>();
    private Queue<Wall> connectionQueue = new LinkedList<>();

    private List<RoomCandidate> rooms = new ArrayList<>();
    private RoomCandidate currentRoomCandidate = new RoomCandidate();

    private List<CoordinatePair> processedSections = new ArrayList<>();

    private int step = 0;

    public DungeonLayoutIdentifier(ChunkSliceManager csm) {
        this.csm = csm;
        this.setStartingRoomSection(0, 0);
        while(this.step()) {
            assert true;
        }
        ChatPrinter.print("yeet");
        this.print();
    }

    public boolean step() {
        step++;
        if(step > 16) {
            connectionQueue.poll();
            return true;
        }
        if(connectionQueue.size() > 0) {
            Wall pos = connectionQueue.poll();
            int roomX = 0;
            int roomZ = 0;
            switch(pos.direction) {
                case Direction.NORTH:
                    roomX = pos.parentSectionX;
                    roomZ = pos.parentSectionZ - 1;
                    break;
                case Direction.SOUTH:
                    roomX = pos.parentSectionX;
                    roomZ = pos.parentSectionZ + 1;
                    break;
                case Direction.WEST:
                    roomX = pos.parentSectionX + 1;
                    roomZ = pos.parentSectionZ;
                    break;
                case Direction.EAST:
                    roomX = pos.parentSectionX - 1;
                    roomZ = pos.parentSectionZ;
                    break;
                default:
                    System.err.println("Invalid direction");
            }
            processRoomSection(roomX, roomZ, pos.direction);
            return true;
        } else if (doorQueue.size() > 0) {
            step = 0;
            rooms.add(currentRoomCandidate);
            currentRoomCandidate = new RoomCandidate();
            Wall pos = doorQueue.poll();
            int roomX = 0;
            int roomZ = 0;
            switch(pos.direction) {
                case Direction.NORTH:
                    roomX = pos.parentSectionX;
                    roomZ = pos.parentSectionZ - 1;
                    break;
                case Direction.SOUTH:
                    roomX = pos.parentSectionX;
                    roomZ = pos.parentSectionZ + 1;
                    break;
                case Direction.WEST:
                    roomX = pos.parentSectionX + 1;
                    roomZ = pos.parentSectionZ;
                    break;
                case Direction.EAST:
                    roomX = pos.parentSectionX - 1;
                    roomZ = pos.parentSectionZ;
                    break;
                default:
                    System.err.println("Invalid direction");
            }
            processRoomSection(roomX, roomZ, pos.direction);

            return true;
        }

        rooms.add(currentRoomCandidate);
        currentRoomCandidate = new RoomCandidate();
        return false;
    }

    public void setStartingRoomSection(int x, int z) {
        rooms.clear();
        currentRoomCandidate = new RoomCandidate();
        currentRoomCandidate.isStartingRoom = true;

        processRoomSection(x, z, -1);
    }

    /**
     *
     * @param x the x
     * @param z the z
     * @param oppositeExcludeDirection the direction opposite of where this will not process to avoid an infinite loop
     */
    public void processRoomSection(int x, int z, int oppositeExcludeDirection) {
        currentRoomCandidate.sections.add(new CoordinatePair(x, z));
        if(oppositeExcludeDirection != Direction.NORTH) {
            //Do south

            //Detect connected
            if((csm.getIndividualBlock(x * 32     , z * 32 + 31) != Blocks.air
            || csm.getIndividualBlock(x * 32 + 30, z * 32 + 31) != Blocks.air)
            && !currentRoomContainsSection(x, z + 1)) {
                connectionQueue.add(new Wall(x, z, Direction.SOUTH));
            }
            //Detect door
            else if(csm.getIndividualBlock(x * 32 + 13, z * 32 + 31) != Blocks.air
                    && !currentRoomContainsSection(x, z + 1)) {
                doorQueue.add(new Wall(x, z, Direction.SOUTH));
            }
        }
        if(oppositeExcludeDirection != Direction.SOUTH) {
            //Do north

            //Detect connected
            if((csm.getIndividualBlock(x * 32     , z * 32 - 1) != Blocks.air
            || csm.getIndividualBlock(x * 32 + 30, z * 32 - 1) != Blocks.air)
            && !currentRoomContainsSection(x, z - 1)) {
                connectionQueue.add(new Wall(x, z, Direction.NORTH));
            }
            //Detect door
            else if(csm.getIndividualBlock(x * 32 + 13, z * 32 - 1) != Blocks.air
                    && !currentRoomContainsSection(x, z - 1)) {
                doorQueue.add(new Wall(x, z, Direction.NORTH));
            }
        }

        if(oppositeExcludeDirection != Direction.WEST) {
            //Do east

            //Detect connected
            if((csm.getIndividualBlock(x * 32 - 1,z * 32) != Blocks.air
            || csm.getIndividualBlock(x * 32 - 1,z * 32 + 30) != Blocks.air)
            && !currentRoomContainsSection(x - 1, z)) {
                connectionQueue.add(new Wall(x, z, Direction.EAST));
            }
            //Detect door
            else if(csm.getIndividualBlock(x * 32 - 1, z * 32 + 13) != Blocks.air
                    && !currentRoomContainsSection(x - 1, z)) {
                doorQueue.add(new Wall(x, z, Direction.EAST));
            }
        }
        if(oppositeExcludeDirection != Direction.EAST) {
            //Do west

            //Detect connected
            if((csm.getIndividualBlock(x * 32 + 31,z * 32) != Blocks.air
            || csm.getIndividualBlock(x * 32 + 31,z * 32 + 30) != Blocks.air)
            && !currentRoomContainsSection(x + 1, z)) {
                connectionQueue.add(new Wall(x, z, Direction.WEST));
            }
            //Detect door
            else if(csm.getIndividualBlock(x * 32 + 31, z * 32 + 13) != Blocks.air
                    && !currentRoomContainsSection(x + 1, z)) {
                doorQueue.add(new Wall(x, z, Direction.WEST));
            }
        }
    }

    public void print() {
        for(RoomCandidate rc : rooms) {
            String fmt = "";
            for(CoordinatePair cp : rc.sections) {
                fmt += String.format("(%d, %d) ", cp.x, cp.z);
            }
            ChatPrinter.print(fmt);
        }
        char[][] b = new char[8][8];
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                b[i][j] = '#';
            }
        }
        char h = 'A';
        for(RoomCandidate rc : rooms) {
            for(CoordinatePair cp : rc.sections) {
                b[cp.z][cp.x] = h;
            }
            ++h;
        }

        for(int i = 0; i < 8; ++i) {
            ChatPrinter.print(new String(b[i]));
        }
    }

    public boolean currentRoomContainsSection(int x, int z) {
        for(RoomCandidate rc : rooms) {
            for(CoordinatePair cp : rc.sections) {
                if((cp.x == x) && (cp.z == z)) {
                    return true;
                }
            }
        }
        for(Wall w : connectionQueue) {
            int roomX = 0;
            int roomZ = 0;
            switch(w.direction) {
                case Direction.NORTH:
                    roomX = w.parentSectionX;
                    roomZ = w.parentSectionZ - 1;
                    break;
                case Direction.SOUTH:
                    roomX = w.parentSectionX;
                    roomZ = w.parentSectionZ + 1;
                    break;
                case Direction.WEST:
                    roomX = w.parentSectionX + 1;
                    roomZ = w.parentSectionZ;
                    break;
                case Direction.EAST:
                    roomX = w.parentSectionX - 1;
                    roomZ = w.parentSectionZ;
                    break;
                default:
                    System.err.println("Invalid direction");
            }

            if((roomX == x) && (roomZ == z)) {
                return true;
            }
        }

        for(Wall w : doorQueue) {
            int roomX = 0;
            int roomZ = 0;
            switch(w.direction) {
                case Direction.NORTH:
                    roomX = w.parentSectionX;
                    roomZ = w.parentSectionZ - 1;
                    break;
                case Direction.SOUTH:
                    roomX = w.parentSectionX;
                    roomZ = w.parentSectionZ + 1;
                    break;
                case Direction.WEST:
                    roomX = w.parentSectionX + 1;
                    roomZ = w.parentSectionZ;
                    break;
                case Direction.EAST:
                    roomX = w.parentSectionX - 1;
                    roomZ = w.parentSectionZ;
                    break;
                default:
                    System.err.println("Invalid direction");
            }

            if((roomX == x) && (roomZ == z)) {
                return true;
            }
        }
        return false;
    }
}
