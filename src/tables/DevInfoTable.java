package tables;

import daos.DevInfoDao;
import database.DBConst;
import database.Database;
import pojo.Category;
import pojo.DevInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DevInfoTable implements DevInfoDao {
    //Connects to the data base
    Database database = Database.getInstance();
    //Creates a arrayList of devInfo
    ArrayList<DevInfo> devInfos;

    public DevInfoTable() throws IOException {
    }

    /**
     *This function will grab all DevInfo form the database
     * @return And return Them
     */
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

    /**
     *
     * @param id Using id users can search for a DevInfo by entering in a id and the function will compare the id's
     * @return returns a DevInfo or a null if there is not DevInfo
     */
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

    @Override
    public void createDevInfo(DevInfo devInfo) {
        String sql = "INSERT INTO " + DBConst.TABLE_DEV_INFO +
                "(" + DBConst.DEV_INFO_COLUMN_DEVELOPER_NAME + "," +
                DBConst.DEV_INFO_COLUMN_PUBLISHER_NAME + ") VALUES ('" +
                devInfo.getDeveloperName() + "','" + devInfo.getPublisherName() + "')";
        try {
            database.getConnection().createStatement().execute(sql);
            System.out.println("Inserted Record");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
