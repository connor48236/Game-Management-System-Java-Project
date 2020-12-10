package daos;

import pojo.Platform;

import java.util.ArrayList;

/**
 * @author Connor Cozad
 */

public interface PlatformDao {
    public ArrayList<Platform> getAllPlatforms();
    public Platform getPlatform(int id);
    public void createPlatform(Platform platform);
}
