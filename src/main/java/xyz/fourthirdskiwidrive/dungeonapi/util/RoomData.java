package xyz.fourthirdskiwidrive.dungeonapi.util;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomData {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("shape")
    @Expose
    private List<List<String>> shape = null;
    @SerializedName("possible_door_locations")
    @Expose
    private List<Boolean> possibleDoorLocations = null;
    @SerializedName("secrets")
    @Expose
    private List<Secret> secrets = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<String>> getShape() {
        return shape;
    }

    public void setShape(List<List<String>> shape) {
        this.shape = shape;
    }

    public List<Boolean> getPossibleDoorLocations() {
        return possibleDoorLocations;
    }

    public void setPossibleDoorLocations(List<Boolean> possibleDoorLocations) {
        this.possibleDoorLocations = possibleDoorLocations;
    }

    public List<Secret> getSecrets() {
        return secrets;
    }

    public void setSecrets(List<Secret> secrets) {
        this.secrets = secrets;
    }

}