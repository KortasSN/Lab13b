package com.josh;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

//Assistance with this lab 13 - I referenced your MovieRatings program as well
//as assistance from Jake Boline while working on this//

//This is incomplete as I ran out of time//


public class CubesDatabase {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "cubes";

    static final String USER = "root";
    static final String PASSWORD = "itecitec";
    static boolean exit = false;
    static Connection conn;
    static Statement statement;
    static ResultSet rs = null;

    public final static String CUBE_TABLE = "Cube_Times";
    public final static String PK_COLUMN = "id";
    public final static String NAME_COLUMN = "name";
    public final static String TIME_COLUMN = "time";

    private static CubesDataModel cubesDataModel;

    public static void main(String[] args) {

        if (!checkCubesDatabase()) {
            System.exit(-1);
        }

        if (!loadAllCubesTimes()) {
            System.exit(-1);
        }
        CubesGUI cubesGUI = new CubesGUI(cubesDataModel);

    }

    public static boolean loadAllCubesTimes() {

        try {
            if (rs != null) {
                rs.close();
            }
            String getAllData = "SELECT * FROM" + CUBE_TABLE;
            rs = statement.executeQuery(getAllData);

            if (cubesDataModel == null) {
                cubesDataModel = new CubesDataModel(rs);
            } else {
                cubesDataModel.updateResultSet(rs);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public static boolean checkCubesDatabase() {
        try {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Not found");
                return false;
            }

            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if (!cubesDatabaseExists()) {

                String createTableSQL = "CREATE TABLE" + CUBE_TABLE + " ( " + PK_COLUMN + " int NOT NULL AUTO_INCREMENT, " +
                        NAME_COLUMN + " varchar(40), " + TIME_COLUMN + "double,";
                System.out.println("CHECKING CREATE TABLE STRING: " + createTableSQL);
//                try {
                    statement.executeUpdate(createTableSQL);
                    System.out.println("Created Cubes table");

                    String addDataSQL = "INSERT INTO " + CUBE_TABLE + "(" + NAME_COLUMN + "," + TIME_COLUMN + ")" + " VALUES (" + "Cubestormer II robot" + ", " + 5.270 + ")";
                    //statement.executeUpdate(addDataSQL);
                    statement.executeUpdate(addDataSQL);
                    addDataSQL = "INSERT INTO " + CUBE_TABLE + "(" + NAME_COLUMN + "," + TIME_COLUMN + ")" + " VALUES (" + "Fakhri Raihaan" + "," + 27.93 + ")";
                    statement.executeUpdate(addDataSQL);
                    addDataSQL = "INSERT INTO " + CUBE_TABLE + "(" + NAME_COLUMN + "," + TIME_COLUMN + ")" + " VALUES (" + "Ruxin Liu" + "," + 99.33 + ")";
                    statement.executeUpdate(addDataSQL);
                    addDataSQL = "INSERT INTO " + CUBE_TABLE + "(" + NAME_COLUMN + "," + TIME_COLUMN + ")" + " VALUES (" + "Mats Valk" + "," + 6.27 + ")";
                    statement.executeUpdate(addDataSQL);

//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    return false;
//                }

            }
        } catch (SQLException sQLE) {
            sQLE.getMessage();
            return false;
        }
        return false; //OR TRUE?
    }

    private static boolean cubesDatabaseExists() {
        return false;
    }


    //statement.executeUpdate("DROP TABLE cubes");
//        String cube2Table = "CREATE TABLE IF NOT EXISTS cubes2 (cube_solver varchar(50), time_seconds double)";
//        statement.executeUpdate(cube2Table);

            //statement.execute("CREATE TABLE IF NOT EXISTS cubes (cube_solver varchar(50), time_seconds double)");
//        String solver1 = "INSERT INTO cubes2 VALUES ('Cubestormer II robot', 5.270)";
//        statement.executeUpdate(solver1);

//        String solver2 = "INSERT INTO cubes2 VALUES ('Fakhri Raihaan (using his feet)', 27.93)";
//        statement.executeUpdate(solver2);


//        String solver3 = "INSERT INTO cubes2 VALUES ('Ruxin Liu (age 3)', 99.33)";
//        statement.executeUpdate(solver3);

            //statement.execute("INSERT INTO cubes VALUES ('Ruxin Liu', 99.33)");
//        String solver4 = "INSERT INTO cubes2 VALUES ('Mats Valk (human record holder)', 6.27)";
//        statement.executeUpdate(solver4);

            //statement.execute("INSERT INTO cubes VALUES ('Mats Valk', 6.27)");

//        while (exit == false) {
//            System.out.println("1. Add Time");
//            System.out.println("2. Display Times");
//            System.out.println("3. Change Time");
//            System.out.println("4. Exit");
//
//            Scanner inputScanner = new Scanner(System.in);
//            int input = inputScanner.nextInt();
//            if (input == 1) {
//                addTime();
//            }
//            if (input == 2) {
//                displayTimes();
//            }
//            if (input == 3) {
//                changeTime();
//            }
//
//            if (input == 4) {
//                    exit = true;
//            }
//
//        }   //End of while loop


            // User interaction with program has finished, done with DB connection
            // Now close statement, prepared statement, result set, connections.
        public static void shutdown () {
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("Result set closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (statement != null)
                    statement.close();
                System.out.println("Statement closed");

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Database connection closed");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //TO DO add try/catch for all


            //changing time of an existing name in database//
//    private static void changeTime() throws SQLException {
//        System.out.println("Enter name:");
//        Scanner searchScanner = new Scanner(System.in);
//        String searchName = searchScanner.nextLine();       //getting name/time of user//
//        System.out.println("Enter new time:");
//        Scanner newtimeScanner = new Scanner(System.in);
//        double newTime = newtimeScanner.nextDouble();
//        String checkname = searchName;
//        Statement statement = conn.createStatement();
//
//        //updating the database with the current information//
//        //statement.executeUpdate("UPDATE cubes SET time_seconds = "+newTime+" WHERE cube_solver ='"+searchName+"'");
//
//        PreparedStatement updateStatement = conn.prepareStatement("UPDATE cubes SET time_seconds = ? WHERE cube_solver = ?");
//        updateStatement.setDouble(1, newTime);
//        updateStatement.setString(2, searchName);
//        updateStatement.execute();
            //statement.executeUpdate("UPDATE cubes SET time_seconds = "+newTime+" WHERE cube_solver ='"+searchName+"'");


            //receiving an error that my SQL syntax is not correct.


//            String prepStatInsert = "INSERT INTO cubes VALUES ( ?, ? )";
//            PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
//            psInsert.setString(1, searchName);
//            psInsert.setDouble(2, newTime);
//            psInsert.executeUpdate();
//
//            psInsert.close();


            //display times in database//
//    private static void displayTimes() throws SQLException {
//
//        String tableOutput = ("SELECT * FROM cubes");  //REMOVE paranthesis
//        ResultSet rs;
//        rs = statement.executeQuery(tableOutput);
//        while (rs.next()) {
//            String name = rs.getString("cube_solver");
//            double times = rs.getDouble("time_seconds");
//            System.out.println("Solver " + name + " in " + times);
//        }
//
//    }
            //add a new time and person to the database//
//    private static void addTime() throws Exception {
//        Scanner nameOfSolverScanner = new Scanner(System.in);
//        System.out.println("Enter your name: ");
//        String nameOfSolver = nameOfSolverScanner.next();             //getting new person's name/time
//        System.out.println("Enter your time: ");
//        Scanner timeOfSolverScanner = new Scanner(System.in);
//        Double timeOfSolver = timeOfSolverScanner.nextDouble();
//
//        String prepStatInsert = "INSERT INTO cubes VALUES ( ?, ? )";
//        PreparedStatement psInsert = conn.prepareStatement(prepStatInsert);
//        psInsert.setString(1, nameOfSolver);                                    //writing person's name/time to database
//        psInsert.setDouble(2, timeOfSolver);
//        psInsert.executeUpdate();
//
//        //psInsert.close();

        }
        }





