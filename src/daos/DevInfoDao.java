package daos;

import pojo.DevInfo;

import java.util.ArrayList;

public interface DevInfoDao {
    public ArrayList<DevInfo> getAllDevInfo();
    public DevInfo getDevInfo(int id);
    public void createDevInfo(DevInfo devInfo);
}
