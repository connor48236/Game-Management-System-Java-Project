package tables;

import daos.DevInfoDao;
import database.DBConst;
import database.Database;
import pojo.Category;
import pojo.DevInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DevInfoTable implements DevInfoDao {
    Database database = Database.getInstance();
    ArrayList<DevInfo> devInfos;


    @Override
    public ArrayList<DevInfo> getAllDevInfo() {
        String sql = "SELECT * FROM " + DBConst.TABLE_DEV_INFO;
        devInfos = new ArrayList<DevInfo>();
        try{
            Statement getDevInfo = database.getConnection().createStatement();
            ResultSet data = getDevInfo.executeQuery(sql);

            while (data.next()){
                devInfos.add(new DevInfo(data.getInt(DBConst.DEV_INFO_COLUMN_ID), data.getString(DBConst.DEV_INFO_COLUMN_DEVELOPER_NAME),
                        data.getString(DBConst.DEV_INFO_COLUMN_PUBLISHER_NAME)));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return devInfos;
    }

    @Override
    public DevInfo getDevInfo(int id) {
        String sql = "SELECT * FROM " + DBConst.TABLE_DEV_INFO + " WHERE " + DBConst.DEV_INFO_COLUMN_ID + " = " + id;
        DevInfo devInfo = null;

        try {
            Statement getDevInfo = database.getConnection().createStatement();
            ResultSet data = getDevInfo.executeQuery(sql);

            while (data.next()){
                devInfo = new DevInfo(data.getInt(DBConst.DEV_INFO_COLUMN_ID), data.getString(DBConst.DEV_INFO_COLUMN_DEVELOPER_NAME),
                        data.getString(DBConst.DEV_INFO_COLUMN_PUBLISHER_NAME));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return devInfo;
    }
}
