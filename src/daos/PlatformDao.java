package daos;

import pojo.Platform;

import java.util.ArrayList;

public interface PlatformDao {
    public ArrayList<Platform> getAllPlatforms();
    public Platform getPlatform(int id);
}
