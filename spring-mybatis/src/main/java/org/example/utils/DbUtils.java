package org.example.utils;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

public class DbUtils {

    /**h2数据库链接信息*/
    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:file:../db/test";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "";
    /**建表语句*/
    public static final String CREATE_TABLE_SQL = "DROP TABLE IF EXISTS PERSON; CREATE TABLE PERSON(id int primary key, name varchar(255))";
    /**insert语句*/
    public static final String INSERT_SQL = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
    /**select语句*/
    public static final String SELECT_SQL = "SELECT * FROM PERSON";

    /**
     * 数据库生成数据
     */
    public static void generateData(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement createPreparedStatement = connection.prepareStatement(CREATE_TABLE_SQL);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            System.out.println("建表成功");

            PreparedStatement insertPreparedStatement = connection.prepareStatement(INSERT_SQL);
            insertPreparedStatement.setInt(1, 1);
            insertPreparedStatement.setString(2, "hello");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.setInt(1, 2);
            insertPreparedStatement.setString(2, "world");
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            System.out.println("插入数据成功");

            System.out.println("数据如下：");
            PreparedStatement selectPreparedStatement = connection.prepareStatement(SELECT_SQL);
            ResultSet rs = selectPreparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }
            selectPreparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
