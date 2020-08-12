package xyz.fourthirdskiwidrive.dungeonapi.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import xyz.fourthirdskiwidrive.dungeonapi.DungeonAPI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class RoomParser {
    static Type listOfBooleanObject = new TypeToken<ArrayList<Boolean>>() {}.getType();
    static class RDIntermediate {
        public String type;
        public List<List<String>> shape;
        public List<Boolean> doorPossible;
    }
    public static RoomData ParseRoom(ResourceID id) {
        byte[] b = new byte[0];
        String json = "";
        File f = new File(DungeonAPI.class.getResource(id.location).getFile());
        try {
            json = String.join("\n", Files.readAllLines(f.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RoomData ret;

        Gson gson = new Gson();
        //System.out.println("json: " + json);
        ret = gson.fromJson(json, RoomData.class);



        return ret;
    }
}
