package org.example;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    private static final String jdbcUrl = "jdbc:mysql://localhost/shop";
    private static final String username = "root";
    private static final String password = "qwerty666";
    private static final String READ_ALL_FROM_USER = "SELECT * FROM user";
    private static final String READ_ALL_FROM_SWEAT = "SELECT * FROM sweat";
    private static final String DELETED_BY_ID_USER = "DELETE FROM user WHERE id = ?";
    private static final String DELETED_BY_ID_SWEAT = "DELETE FROM sweat WHERE id = ?";


    public static void main(String[] args) {
//        deletedByIdUser(1);
        deletedByIdSweat(1);
        readUserAndSweat();
    }

    public static void readUserAndSweat() {
        try {
            // Загрузка драйвера JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединение с БД
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Создание обьекта Statement
//            Statement statement = connection.createStatement();
            PreparedStatement preparedStatementUser = connection.prepareStatement(READ_ALL_FROM_USER);
            PreparedStatement preparedStatementSweat = connection.prepareStatement(READ_ALL_FROM_SWEAT);


            // Выполнение SQL - запроса
            ResultSet resultSetUser = preparedStatementUser.executeQuery();
            ResultSet resultSetSweat = preparedStatementSweat.executeQuery();

            while (resultSetUser.next()) {
                String name = resultSetUser.getString("name");
                String email = resultSetUser.getString("email");
                String password = resultSetUser.getString("password");
                System.out.println("Name: " + name + " , Email: " + email + " , Password: " + password);
            }

            while (resultSetSweat.next()) {
                String name = resultSetSweat.getString("name");
                String user_id = resultSetSweat.getString("user_id");
                System.out.println("Name: " + name + " , User Id: " + user_id);
            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deletedByIdUser(int id) {
        try {
            // Загрузка драйвера JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединение с БД
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Создание обьекта Statement
            PreparedStatement preparedStatementUser = connection.prepareStatement(DELETED_BY_ID_USER);
            preparedStatementUser.setInt(1, id);
            preparedStatementUser.execute();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deletedByIdSweat(int id) {
        try {
            // Загрузка драйвера JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединение с БД
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Создание обьекта Statement
            PreparedStatement preparedStatementUser = connection.prepareStatement(DELETED_BY_ID_SWEAT);
            preparedStatementUser.setInt(1, id);
            preparedStatementUser.execute();


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }



}


