package daos;

import pojo.DevInfo;

import java.util.ArrayList;

/**
 * @author Connor Cozad
 */

public interface DevInfoDao {
    public ArrayList<DevInfo> getAllDevInfo();
    public DevInfo getDevInfo(int id);
    public void createDevInfo(DevInfo devInfo);
}
