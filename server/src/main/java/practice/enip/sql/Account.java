package practice.enip.sql;

import java.awt.color.ProfileDataException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class can handle the information and behavior of account
 * between database and users, for instance, logging in, registering
 * or looking up some user data.
 * 
 * @author Enip
 *
 */
public class Account extends Database_connector{
	
	private String login_account;
	private String login_password;
	private String account_name;
	
	public Account() {
		super();
	}
	
	/**
	 * Practically, only authenticated account would store the login account and password.
	 * It's won't store any user information until the account-password set is exists in database.
	 * 
	 * @return Boolean value tells user this account is authenticated or not.
	 */
	public Boolean is_authenticated() {
		return login_account!=null;
	}
	
	/**
	 * return user's login account.
	 * @return A String which is account;
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */
	public String get_account() throws ProfileDataException{
		if(login_account == null)
			throw new ProfileDataException("This account is not authenticated !");
		return login_account;
	}
	
	/**
	 * return user's login password.
	 * @return A String which is password;
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */
	public String get_password() throws ProfileDataException{
		if(login_password == null)
			throw new ProfileDataException("This account is not authenticated !");
		return login_password;
	}
	
	/**
	 * Accounts have a column call "account_name" (not the name of player)
	 * which can be null, and when this account is exists and its name is actually null,
	 * returning a empty String instead of null.
	 * 
	 * 
	 * @return a String which is the name of account.
	 * When the name of account is empty, returning a empty String.
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */
	public String get_name() throws ProfileDataException{
			
		if(is_authenticated()) {
		    String get_password_by_account = ("SELECT account_name FROM user_attribute WHERE login_account = \'"+login_account + "\'");
	        
	        try (Statement statement = connection.createStatement()) {

	            ResultSet rs = statement.executeQuery(get_password_by_account);
	            if (rs.next()) {
	            	account_name = rs.getString("account_name");
	            	if(account_name == null) account_name = "";
	            	
	            	return account_name;
	            }
	        }catch(SQLException e){

	            e.printStackTrace();

	        }
			return account_name;
		}else {
			throw new ProfileDataException("This account is not authenticated !");
		}
	}

	/**
	 * Notice if the authentication failed, the account and password would not store in object
     * @param account login account
     * @param password login password
	 * @return int value to make user check the result of authenticating.
	 *  <LI> 1 stands for successfully log in.
	 *  <LI> 0 stands for log in failed, but the login account is correct.
	 *  <LI> -1 stands for could not find the account in database or occurs some exception
	 *  		from connecting or selecting(<code>SQLException</code>)
	 */
    public int authenticate(String account, String password){

        String get_password_by_account = ("SELECT password FROM user_attribute WHERE login_account = \'"+account + "\'");
        
        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(get_password_by_account);
            if (rs.next()) {
            	Boolean if_authenticated = password.equals(rs.getString("password")) ? true : false;
            	
            	if(if_authenticated) {
            		login_account = account;
            		login_password = password;
            	}
            	return if_authenticated ? 1 : 0;
            }


        }catch(SQLException e){
        	if(e.toString().equals("java.sql.SQLException: Column 'login_account' not found.")) {
        		return -1;
        	}else {
            	e.printStackTrace();
        	}
        }
        return -1;
    }
    
    /**
     * 
     * @param account login account
     * @param password login password
     * @param name this is the name of account not the player
     * @return int value to make user check the result of registering.
     * 	<LI> 1 stands for successfully registered.
     * 	<LI> 0 stands for registration failed by the account is aleardy exists.
     * 	<LI> -1 stands for some exception from connecting or selecting(<code>SQLException</code>) occur
     */
    public int new_account(String account, String password, String name) {
		
		try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE)){
			
			ResultSet rs = statement.executeQuery("SELECT * FROM user_attribute WHERE login_account = '"+account+"\'");
			
			if(rs.next()) return 0;
			else {
				rs = statement.executeQuery("SELECT * FROM user_attribute");
				
				rs.moveToInsertRow();
				rs.updateString("login_account", account);
				rs.updateString("password", password);
				rs.updateString("account_name", name);
				
				rs.insertRow();

				return 1;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return -1;
	}
    
	
}
