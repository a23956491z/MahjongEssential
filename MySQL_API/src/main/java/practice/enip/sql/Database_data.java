package practice.enip.sql;

import java.sql.Connection;

public class Database_data {

	protected Connection connection;
	public Database_data(Account account) {
		connection = account.connection; 
	}
	public Database_data() {
		
	}

}
