package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GetLogin {

    public static String[] getLoginInfo() throws IOException {
        File loginFile = new File("src/login/login.txt");

        String[] loginVars = null;

        if (loginFile.isFile()) {
            FileInputStream iFile = new FileInputStream(loginFile);
            byte[] loginBytes = iFile.readAllBytes();
            String loginString = new String(loginBytes);
            loginVars = loginString.split(",");
            System.out.println(loginVars[1]);
        }

        return loginVars;
    }
}
