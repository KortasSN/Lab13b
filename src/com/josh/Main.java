package com.company;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;


public class Main {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";

    static final String USER = "root";
    static final String PASSWORD = "itecitec";
    static boolean exit = false;
    static Connection conn;
    static Statement statement;

    public static void main(String[] args) throws Exception {

        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        statement = conn.createStatement();
        statement.executeUpdate("DROP TABLE cubes");
//        String cube2Table = "CREATE TABLE IF NOT EXISTS cubes2 (cube_solver varchar(50), time_seconds double)";
//        statement.executeUpdate(cube2Table);

        statement.execute("CREATE TABLE IF NOT EXISTS cubes (cube_solver varchar(50), time_seconds double)");
//        String solver1 = "INSERT INTO cubes2 VALUES ('Cubestormer II robot', 5.270)";
//        statement.executeUpdate(solver1);
        statement.execute("INSERT INTO cubes VALUES ('Cubestormer II robot', 5.270)");
//        String solver2 = "INSERT INTO cubes2 VALUES ('Fakhri Raihaan (using his feet)', 27.93)";
//        statement.executeUpdate(solver2);

        statement.execute("INSERT INTO cubes VALUES ('Fakhri Raihaan', 27.93)");
//        String solver3 = "INSERT INTO cubes2 VALUES ('Ruxin Liu (age 3)', 99.33)";
//        statement.executeUpdate(solver3);

        statement.execute("INSERT INTO cubes VALUES ('Ruxin Liu', 99.33)");
//        String solver4 = "INSERT INTO cubes2 VALUES ('Mats Valk (human record holder)', 6.27)";
//        statement.executeUpdate(solver4);

        statement.execute("INSERT INTO cubes VALUES ('Mats Valk', 6.27)");

        while (exit == false) {
            System.out.println("1. Add Time");
            System.out.println("2. Display Times");
            System.out.println("3. Change Time");
            System.out.println("4. Exit");

            Scanner inputScanner = new Scanner(System.in);
            int input = inputScanner.nextInt();
            if (input == 1) {
                addTime();
            }
            if (input == 2) {
                displayTimes();
            }
            if (input == 3) {
                changeTime();
            }

            if (input == 4) {
                    exit = true;
            }

        }   //End of while loop


            // User interaction with program has finished, done with DB connection
            // Now close statement, prepared statement, result set, connections.

                try {
                    if (statement != null) statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //TO DO add try/catch for all

    }
    //changing time of an existing name in database//
    private static void changeTime() throws SQLException {
        System.out.println("Enter name:");
        Scanner searchScanner = new Scanner(System.in);
        String searchName = searchScanner.nextLine();       //getting name/time of user//
        System.out.println("Enter new time:");
        Scanner newtimeScanner = new Scanner(System.in);
        double newTime = newtimeScanner.nextDouble();
        String checkname = searchName;
        Statement statement = conn.createStatement();

        //updating the database with the current information//
        //statement.executeUpdate("UPDATE cubes SET time_seconds = "+newTime+" WHERE cube_solver ='"+searchName+"'");

        PreparedStatement updateStatement = conn.prepareStatement("UPDATE cubes SET time_seconds = ? WHERE cube_solver = ?");
        updateStatement.setDouble(1, newTime);
        updateStatement.setString(2, searchName);
        updateStatement.execute();
        //statement.executeUpdate("UPDATE cubes SET time_seconds = "+newTime+" WHERE cube_solver ='"+searchName+"'");


        //receiving an error that my SQL syntax is not correct.


//            String prepStatInsert = "INSERT INTO cubes VALUES ( ?, ? )";
//            PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
//            psInsert.setString(1, searchName);
//            psInsert.setDouble(2, newTime);
//            psInsert.executeUpdate();
//
//            psInsert.close();

    }
    //display times in database//
    private static void displayTimes() throws SQLException {

        String tableOutput = ("SELECT * FROM cubes");  //REMOVE paranthesis
        ResultSet rs;
        rs = statement.executeQuery(tableOutput);
        while (rs.next()) {
            String name = rs.getString("cube_solver");
            double times = rs.getDouble("time_seconds");
            System.out.println("Solver " + name + " in " + times);
        }

    }
    //add a new time and person to the database//
    private static void addTime() throws Exception {
        Scanner nameOfSolverScanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String nameOfSolver = nameOfSolverScanner.next();             //getting new person's name/time
        System.out.println("Enter your time: ");
        Scanner timeOfSolverScanner = new Scanner(System.in);
        Double timeOfSolver = timeOfSolverScanner.nextDouble();

        String prepStatInsert = "INSERT INTO cubes VALUES ( ?, ? )";
        PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
        psInsert.setString(1, nameOfSolver);                                    //writing person's name/time to database
        psInsert.setDouble(2, timeOfSolver);
        psInsert.executeUpdate();

        //psInsert.close();

    }
}

