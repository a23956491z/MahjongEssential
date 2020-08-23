package practice.enip.sql;

import java.awt.color.ProfileDataException;
import java.io.EOFException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
	
	private final Role_list role_list = new Role_list();
	private final Map<String, Match> match_map = new HashMap<>();
	
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
	 * @return A <code>String</code> which is account;
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */

	@SuppressWarnings("UnusedReturnValue")
	public String get_account() throws ProfileDataException{
		if(login_account == null)
			throw new ProfileDataException("This account is not authenticated !");

		return login_account;
	}
	
	/**
	 * return user's login password.
	 * @return A <code>String</code> which is password;
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */
	@SuppressWarnings("unused")
	public String get_password() throws ProfileDataException{
		if(login_password == null)
			throw new ProfileDataException("This account is not authenticated !");
		return login_password;
	}
	
	/**
	 * Accounts have a column call <code>account_name</code> (not the name of player)
	 * which can be null, and when this account is exists and its name is actually null,
	 * returning a empty <code>String</code> instead of null.
	 * 
	 * 
	 * @return a <code>String</code> which is the name of account.
	 * When the name of account is empty, returning a empty String.
	 * @throws ProfileDataException appears, someone trying to get information of
	 *  this instance(user) from database or itself when it is not authenticated.
	 */

	public String get_name() throws ProfileDataException{
			
		if(is_authenticated()) {
		    String get_password_by_account = ("SELECT account_name FROM user_attribute WHERE login_account = '"+login_account + "'");
	        
	        try (Statement statement = connection.createStatement();
	        	 ResultSet rs = statement.executeQuery(get_password_by_account)) {

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

        String get_password_by_account = ("SELECT password FROM user_attribute WHERE login_account = '"+account + "'");
        
        try (Statement statement = connection.createStatement();
        	 ResultSet rs = statement.executeQuery(get_password_by_account)) {

           
            if (rs.next()) {
            	boolean if_authenticated = password.equals(rs.getString("password"));
            	
            	if(if_authenticated) {
            		login_account = account;
            		login_password = password;
            		get_roles();
            		get_match();
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
     * Register a new account
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
			
			//check the account is not exists
			ResultSet rs = statement.executeQuery("SELECT * FROM user_attribute WHERE login_account = '"+account+"'");
			
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

	@SuppressWarnings("unused")
    public int change_password(String old_password, String new_password) throws ProfileDataException{
    	
    	try {
    		
    		get_account();
    	}catch(ProfileDataException p){
    		
    		p.printStackTrace();
    		Throwable t = p.fillInStackTrace();
    		throw (ProfileDataException)t;
    		//print exception stack by set stack begin as this re-throw
    	}
    	
    	if(!old_password.equals(login_password) ) {
    		return 0;
    	}
    	
    	try(Statement statement = connection.createStatement(
    									ResultSet.TYPE_SCROLL_SENSITIVE,
    									ResultSet.CONCUR_UPDATABLE);
    		ResultSet rs = statement.executeQuery(get_sql_select_by_account(login_account))){
    		
    		rs.last();
			rs.updateString("password", new_password);
			rs.updateRow();

			return 1;
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
    	
    	return -1;
    }

	@SuppressWarnings("unused")
    public int change_name(String name) throws ProfileDataException{
    	
    	try {
    		
    		get_account();
    	}catch(ProfileDataException p){
    		
    		p.printStackTrace();
    		Throwable t = p.fillInStackTrace();
    		throw (ProfileDataException)t;
    		//print exception stack by set stack begin as this re-throw
    	}
    	
    	try(Statement statement = connection.createStatement(
    									ResultSet.TYPE_SCROLL_SENSITIVE,
    									ResultSet.CONCUR_UPDATABLE);
    		ResultSet rs = statement.executeQuery(get_sql_select_by_account(login_account))){
    		
    		rs.last();
			rs.updateString("account_name", name);
			rs.updateRow();

			return 1;
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
    	
    	return 0;
    }

	@SuppressWarnings("UnusedReturnValue")
    public int get_roles() throws ProfileDataException {
    	try {
    		
    		get_account();
    	}catch(ProfileDataException p){
    		
    		p.printStackTrace();
    		Throwable t = p.fillInStackTrace();
    		throw (ProfileDataException)t;
    		//print exception stack by set stack begin as this re-throw
    	}

    	try(Statement statement = connection.createStatement();
    		ResultSet rs = statement.executeQuery
    				("SELECT * FROM role_attribute WHERE login_account = '"+login_account+"'"))
    	{
    		
    		while(rs.next()) {
    			int role_id = rs.getInt("role_id");
    			String role_name = rs.getString("role_name");
    			role_list.add_role(role_id, role_name);
    			
    		}
    		
			return 1;
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
    	
    	
    	return 0;
    }

	@SuppressWarnings("unused")
    public int new_role(String name) throws ProfileDataException {
    	try{
    		get_account();
		}catch(ProfileDataException p){

			p.printStackTrace();
			Throwable t = p.fillInStackTrace();
			throw (ProfileDataException)t;
			//print exception stack by set stack begin as this re-throw
		}


		try(Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE))
		{
			ResultSet rs = statement.executeQuery("SELECT * FROM role_attribute WHERE login_account = '"+login_account+"' && role_name = '"+name +"'");

			if(rs.next()) return 0;
			else {
				rs = statement.executeQuery("SELECT * FROM role_attribute");

				rs.moveToInsertRow();
				rs.updateString("login_account", login_account);
				rs.updateString("role_name", name);

				rs.insertRow();

				rs = statement.executeQuery("SELECT * FROM role_attribute WHERE login_account = '"+login_account+"' && role_name = '"+name +"'");
				if(rs.next()){

					int role_id = rs.getInt("role_id");
					role_list.add_role(role_id,name);

/*
					System.out.print("id : ");
					System.out.println(role_list.get_id_by_name(name));
					role_list.print();
*/

					return 1;
				}else{
					return 0;
				}
			}

		}catch(SQLException e){
			e.printStackTrace();
		}

		return 0;
	}

	@SuppressWarnings("unused")
	public int delete_role(String name){

		try{
			get_account();
		}catch(ProfileDataException p){

			p.printStackTrace();
			Throwable t = p.fillInStackTrace();
			throw (ProfileDataException)t;
			//print exception stack by set stack begin as this re-throw
		}


		try(Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery("SELECT * FROM role_attribute WHERE login_account = '"+login_account+"' && role_name = '"+name +"'"))
		{
			if(rs.next()){

				rs.last();
				rs.deleteRow();

				if(role_list.remove_role(name) == 0){
					System.out.println("Role : "+name +" not Exists");

				}else{
					//role_list.print();
					return 1;
				}


			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		return 0;
	}


    public int change_role_name(String old_name, String new_name) {

		try {
			get_account();
		}catch(ProfileDataException p){

			p.printStackTrace();
			Throwable t = p.fillInStackTrace();
			throw (ProfileDataException)t;
			//print exception stack by set stack begin as this re-throw
		}



		try(Statement statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery("SELECT * FROM role_attribute WHERE login_account = '"+login_account+"' && role_name = '"+old_name +"'")){

			if(rs.next()) {
				rs.last();
				rs.updateString("role_name", new_name);
				rs.updateRow();

				if(role_list.change_name(old_name, new_name) == 1){

					//role_list.print();
					return 1;
				}else{
					System.out.println("Role : "+old_name +" not Exists");
				}

			}

		}catch(SQLException e){
			e.printStackTrace();
		}

		return 0;
    }

	@SuppressWarnings("unused")
    public List<String> get_role_nameList(){
    	return role_list.get_name_list();
	}

	@SuppressWarnings("unused")
	public void print_role(){
    	role_list.print();
	}

	public int new_match(Match match){

		try{
			get_account();
		}catch(ProfileDataException p){

			p.printStackTrace();
			Throwable t = p.fillInStackTrace();
			throw (ProfileDataException)t;
			//print exception stack by set stack begin as this re-throw
		}

		int match_serial_number = 0;

		String SQL = "INSERT INTO match_information(login_account) " +
				"    VALUE(?)";

		try(PreparedStatement pstmt =  connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {


			pstmt.setString(1, login_account);

			int affectedRows = pstmt.executeUpdate();

			if(affectedRows > 0){
				try(ResultSet rs = pstmt.getGeneratedKeys()){
					if(rs.next()){
						match_serial_number = rs.getInt(1);
					}
				}catch(SQLException e){

					System.err.println(e.getMessage());
					return 0;
				}
			}

			match.match_id = ((Integer)match_serial_number).toString();
			match.account = login_account;
		}catch(SQLException e){
			e.printStackTrace();
		}


		for (int round = 1; round <= match.total_round(); round++) {
			for (Map.Entry entry : match.round_record.get(round).entrySet()) {

				SQL = "INSERT INTO match_record(match_serial_number, round_number, role_name, score_change)"
						+ "VALUES(?,?,?,?)";
				try(PreparedStatement pstmt = connection.prepareStatement(SQL)) {

					if(match_serial_number == 0) return 0;
					pstmt.setInt(1, match_serial_number);
					pstmt.setInt(2, round);
					pstmt.setString(3, (String)entry.getKey());
					pstmt.setInt(4,(int)entry.getValue());

					int affectedRows = pstmt.executeUpdate();

					System.out.println("affected rows : " + ((Integer)affectedRows).toString());

				}catch(SQLException e){

					e.printStackTrace();
					return 0;
				}
			}
		}

		match_map.put(match.match_id, match);

		return 1;
	}

	@SuppressWarnings("unused")
	public int get_match(){
		try {

			get_account();
		}catch(ProfileDataException p){

			p.printStackTrace();
			Throwable t = p.fillInStackTrace();
			throw (ProfileDataException)t;
			//print exception stack by set stack begin as this re-throw
		}


		try(Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery
					("SELECT * FROM match_record "+
							"WHERE "+
								"match_serial_number IN "+
								"(SELECT match_serial_number FROM match_information "+
									"WHERE login_account = '" + login_account + "')")
		   )
		{

			while(rs.next()) {

				String match_serial_number = ((Integer)rs.getInt("match_serial_number")).toString();
				int round_number = rs.getInt("round_number");
				String role_name = rs.getString("role_name");
				int score_change = rs.getInt("score_change");

				if(!match_map.containsKey(match_serial_number)){
					Match m = new Match();
					m.account = login_account;
					m.match_id = match_serial_number;

					match_map.put(match_serial_number, m);
				}
				match_map.get(match_serial_number).round_add_player_score(round_number,role_name, score_change);


			}
/*
			for(var entry : match_map.entrySet()){
				entry.getValue().print();
			}
*/
			return 1;


		}catch(SQLException e){
			e.printStackTrace();
		}


		return 0;

	}
    
    private String get_sql_select_by_account(String account) {
    	return "SELECT * FROM user_attribute WHERE login_account = '"+account+"'";
    }

    
	
}
