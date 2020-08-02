package practice.enip.sql;

import java.sql.*;


public class Database_connector{

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "test_user";
    static final String PASS = "test1234";

    private Connection connection;

    public Database_connector(){

        try{
            Class.forName(JDBC_DRIVER); //register JDBC dirver
            System.out.println("Successfully registered Mysql");

        }catch(Exception e){
            System.out.println("registering failed!!");
            //might be jar file didn't import correctly
        }

        try{
            connection = getConnection(); //connecting
        }
        catch (SQLException e) {
            System.out.println("Connecting to database failed!!");
        }
    }

    private static Connection getConnection() throws SQLException {
          String serverName = "localhost";
          String database = "database_mje_test";
          String url = "jdbc:mysql://" + serverName + "/" + database;

          return DriverManager.getConnection(url, USER, PASS);
    }

    public int authenticate(String account, String password){

        try{
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT password FROM user_attribute WHERE account_number = \'%s\'", account);

            try (ResultSet rs = statement.executeQuery(sql)) { //try-with-resouce(JAVA SE 7)

                if (rs.next()) {
                    return (rs.getString("password").equals(password)) ? 1 : 0;
                }
            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }


}
