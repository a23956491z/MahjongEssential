package practice.enip.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a2395
 *
 */
public class Role extends Database_data{
	
	private String role_id;
	private String role_name;

	//store serial number
	private List<String> match = new ArrayList<String>();
	
	/**
	 * 
	 */
	public Role(Account account) {
		super(account);
	}
	public Role(String id, String name) {
		role_id = id;
		role_name = name;
	}
	
	public String get_id(){
		return role_id;
	}
	public String get_name(){
		return role_name;
	}

}
