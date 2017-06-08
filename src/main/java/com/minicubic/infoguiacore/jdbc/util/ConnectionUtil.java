package com.minicubic.infoguiacore.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xergio
 */
public class ConnectionUtil {

    private static final String DB = "jdbc:mysql://45.79.159.123:3306/infoguiadb?autoReconnect=true&useSSL=false";
    private static final String USER_ID = "infoguia";
    private static final String PASSWORD = ".infoguia.3306*";
    private final Connection conn;

    public ConnectionUtil() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        conn = DriverManager.getConnection(DB, USER_ID, PASSWORD);
    }

    public Connection getConnection() {
        return conn;
    }

    public void test() {
        Statement statement;
        ResultSet rs;
        
        String queryString = "select * from sysobjects where type='u'";
        
        try {

            statement = conn.createStatement();
            rs = statement.executeQuery(queryString);

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
