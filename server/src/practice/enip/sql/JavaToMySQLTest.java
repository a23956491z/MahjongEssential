package practice.enip.sql;

import java.sql.*;


public class JavaToMySQLTest{

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "test_user";
    static final String PASS = "test1234";


    public JavaToMySQLTest(){

        try{

            Class.forName(JDBC_DRIVER); //register JDBC dirver
            System.out.println("Successfully registered Mysql");

        }catch(Exception e){
            System.out.println("registering failed!!");
            //might be jar file didn't import correctly
        }

        try{
            Connection connection = getConnection(); //connecting

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM city WHERE CountryCode = \'AFG\'";

            try (ResultSet rs = statement.executeQuery(sql)) { //try-with-resouce(JAVA SE 7)
                while (rs.next()) {

                    System.out.printf("%-20s %-5s %s%n",rs.getString("Name"),
                      rs.getString("CountryCode"), rs.getString("District"));
                    }

            }
        }
        catch (SQLException e) {
            System.out.println("Connecting to database failed!!");
        }
    }

    private static Connection getConnection() throws SQLException {
          String serverName = "localhost";
          String database = "world";
          String url = "jdbc:mysql://" + serverName + "/" + database;

          return DriverManager.getConnection(url, USER, PASS);
    }
}
