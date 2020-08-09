package practice.enip.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class deal with basic operations with JDBC_driver or database connections.
 * It wont actually change or lookup data in database.
 * 
 * @see Account
 * @author Enip
 * 
 * */
public class Database_connector implements AutoCloseable{

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static final String USER = "test_user";
    static final String PASS = "test1234";
    
   
    /* !!! notice !!! a static connection will keep single connection
     * lasting too long resulting memory leaking problem and it's not threadsafe.
     * */ 
    protected Connection connection; 


    static {
    	
        try{
        	/*register JDBC dirver by try to loading JDBC_DRIVER class*/
            Class.forName(JDBC_DRIVER); 
            
            System.out.println("Successfully registered Mysql");
        }catch(ClassNotFoundException e){
        	
        	/* Most of time we didn't set out -cp correctly
        	 * so java can't find the JDBC_DRIVER class.
        	 * Make sure you imported "mysql-connector-java-*.*.**.jar"
        	 * in right directory*/
            System.out.println("registering failed!!");
            
        }
    }
    private static Connection getConnection() throws SQLException {
        String serverName = "localhost";
        String database = "database_mje_test";
        String url = "jdbc:mysql://" + serverName + "/" + database;

        return DriverManager.getConnection(url, USER, PASS);
    }
    
    public Database_connector(){
    	super();
    	
        try {
        	connection = getConnection(); //connecting
        }catch (SQLException e) {
            System.out.println("Connecting to database failed!!");
        }
    }
    
    public Database_connector(Database_connector db) {
    	super();
    	connection = db.connection;
    }

    /**
     * Implements the AutoCloseable interface to make Connection can be
     * closed by java try-with-resource feature
     * 
     * @throws SQLException if a database access error occurs
     * or this method is called on a closed connection.
     */
    @Override
    public void close() throws SQLException{
    	connection.close();
    }
}
