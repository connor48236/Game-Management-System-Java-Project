package tables;

import daos.PlatformDao;
import database.DBConst;
import database.Database;
import pojo.DevInfo;
import pojo.Platform;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class platformTable implements PlatformDao {
    Database database = Database.getInstance();
    ArrayList<Platform> platforms;


    @Override
    public ArrayList<Platform> getAllPlatforms() {
        String sql = "SELECT * FROM " + DBConst.TABLE_PLATFORM;
        platforms = new ArrayList<Platform>();
        try{
            Statement getPlatform = database.getConnection().createStatement();
            ResultSet data = getPlatform.executeQuery(sql);

            while (data.next()){
                platforms.add(new Platform(data.getInt(DBConst.PLATFORM_COLUMN_ID), data.getString(DBConst.PLATFORM_COLUMN_NAME)));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return platforms;
    }

    @Override
    public Platform getPlatform(int id) {
        String sql = "SELECT * FROM " + DBConst.TABLE_PLATFORM + " WHERE " + DBConst.PLATFORM_COLUMN_ID + " = " + id;
        Platform platform = null;

        try {
            Statement getPlatform = database.getConnection().createStatement();
            ResultSet data = getPlatform.executeQuery(sql);

            while (data.next()){
                platform = new Platform(data.getInt(DBConst.PLATFORM_COLUMN_ID), data.getString(DBConst.PLATFORM_COLUMN_NAME));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        return platform;
    }
}

