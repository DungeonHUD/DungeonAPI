package xyz.fourthirdskiwidrive.dungeonapi.room;


import net.minecraft.util.BlockPos;
import xyz.fourthirdskiwidrive.dungeonapi.util.Secret;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public abstract class Room {

    public List<Secret> Secrets;

    public Room () {
        //Secrets = roomData.getSecrets();
    }

    public List<BlockPos> getSecretPositions(int rotation, int xstart, int zstart) throws InvalidParameterException {
        List<BlockPos> ret = new ArrayList<>();

        for(Secret secret : Secrets) {
            int tempx;
            int tempy;
            int tempz = 0;
            tempy = secret.getY();
            switch(rotation) {
                case 0:
                    tempx = xstart + secret.getX();
                    tempz = zstart + secret.getZ();
                    break;
                case 1:
                    tempx = xstart + (32 - secret.getZ());
                    tempy = zstart + secret.getX();
                    break;
                case 2:
                    tempx = xstart + (32 - secret.getX());
                    tempz = zstart + (32 - secret.getZ());
                    break;
                case 3:
                    tempx = xstart + secret.getZ();
                    tempz = xstart + (32 - secret.getX());
                    break;
                default:
                    throw new InvalidParameterException("Room rotation must be between 0 and 3");
            }

            ret.add(new BlockPos(tempx, tempy, tempz));
        }

        return ret;
    }
    public abstract RoomType getRoomType();
}
