package xyz.fourthirdskiwidrive.dungeonapi.util;

import java.io.File;
import java.io.InputStream;

public class ResourceID {
    public final String location;
    public ResourceID(String loc) {
        this.location = loc;
    }

    public InputStream open() {
        return getClass().getResourceAsStream(location);
    }
}
