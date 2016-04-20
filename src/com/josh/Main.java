package com.josh;

import java.sql.*;
import java.util.Scanner;

//create database in mySQL shell
//create table cubes (cube_solver varchar(50), time_seconds float);



public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";

    static final String USER = "root";
    static final String PASSWORD = "itecitec";

static Connection conn;
static Statement statement;

    public static void main(String[] args) throws Exception {

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        statement = conn.createStatement();

//        String cube2Table = "CREATE TABLE IF NOT EXISTS cubes2 (cube_solver varchar(50), time_seconds double)";
//        statement.executeUpdate(cube2Table);

        statement.execute("CREATE TABLE IF NOT EXISTS cubes (cube_solver varchar(50), time_seconds double)");
//        String solver1 = "INSERT INTO cubes2 VALUES ('Cubestormer II robot', 5.270)";
//        statement.executeUpdate(solver1);
        statement.execute("INSERT INTO cubes VALUES ('Cubestormer II robot', 5.270)");
//        String solver2 = "INSERT INTO cubes2 VALUES ('Fakhri Raihaan (using his feet)', 27.93)";
//        statement.executeUpdate(solver2);

        statement.execute("INSERT INTO cubes VALUES ('Fakhri Raihaan (using his feet)', 27.93)");
//        String solver3 = "INSERT INTO cubes2 VALUES ('Ruxin Liu (age 3)', 99.33)";
//        statement.executeUpdate(solver3);

        statement.execute("INSERT INTO cubes VALUES ('Ruxin Liu (age 3)', 99.33)");
//        String solver4 = "INSERT INTO cubes2 VALUES ('Mats Valk (human record holder)', 6.27)";
//        statement.executeUpdate(solver4);

        statement.execute("INSERT INTO cubes VALUES ('Mats Valk (human record holder)', 6.27)");

        addTime();

        displayTimes();

        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn !=null) conn.close();
            }
        catch (SQLException e)  {
            e.printStackTrace();
        }
        //TO DO add try/catch for all

    }

    private static void displayTimes() throws SQLException {

        String tableOutput = ("SELECT * FROM cubes");  //REMOVE paranthesis
        ResultSet rs;
        rs = statement.executeQuery(tableOutput);
        while (rs.next()) {
            String name = rs.getString("cube_solver");
            double times = rs.getDouble("time_seconds");
            System.out.println("Solver " + name + " in " + times);
                    }
        //statement.executeQuery(tableOutput);
    }

    private static void addTime() throws Exception {
        Scanner nameOfSolverScanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String nameOfSolver = nameOfSolverScanner.next();
        System.out.println("Enter your time: ");
        Scanner timeOfSolverScanner = new Scanner(System.in);
        Double timeOfSolver = timeOfSolverScanner.nextDouble();

        String prepStatInsert = "INSERT INTO cubes VALUES ( ?, ? )";
        PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
        psInsert.setString(1, nameOfSolver);
        psInsert.setDouble(2, timeOfSolver);
        psInsert.executeUpdate();

        psInsert.close();

    }
}


