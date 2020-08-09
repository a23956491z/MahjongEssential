package practice.enip.sql;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;


/**
 * @author a2395
 *
 */

class Role{
	
	public String role_id;
	public String role_name;

	//store serial number
	public List<String> match = new ArrayList<String>();
	
	/**
	 * 
	 */
	public Role(Account account) {
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
	public List<String> get_match() {
		return match;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		
		if(o == null) return false;
		
		if(getClass() != o.getClass()) return false;
		
		Role role = (Role) o;
		return Objects.equals(role_id, role.role_id) 
			&& Objects.equals(role_name, role.role_name);
	}

}


public class Role_list{
	
	private List<Role> role_list = new ArrayList<Role>();
	private List<String> name_list = new ArrayList<String>();
	
	public Role_list() {
	
	}
	
	public int add_role(String id, String name) {
		
		if(if_exists(name)) {
			return 0;
		}
		
		role_list.add(new Role(id, name));
		name_list.add(id);
		return 1;
	}
	
	public List<String> get_name_list(){
		return name_list;
	}
	
	public String get_id_by_name(String name) throws NoSuchElementException {
		
		try {
			
			return get_role_by_name(name).get().get_id();
		}catch(NoSuchElementException e){
			
			e.printStackTrace();
			Throwable t = e.fillInStackTrace();
			throw (NoSuchElementException)t;
		}
		
	}
	
	public List<String> get_matchString_by_name(String name) throws NoSuchElementException {
		try {
			
			return get_role_by_name(name).get().get_match();
		}catch(NoSuchElementException e){
			
			e.printStackTrace();
			Throwable t = e.fillInStackTrace();
			throw (NoSuchElementException)t;
		}

	}
	
	private boolean if_exists(String name) {
		if(name_list.indexOf(name) !=  -1) {
			return true;
		}
		return false;
	}
	
	private Optional<Role> get_role_by_name(String name) {
		
		List<Role> result_list = role_list.stream()
				.filter(a -> a.role_name.equals(name))
				.collect(Collectors.toList());

		
		return result_list.isEmpty() ? Optional.empty() : Optional.of(result_list.get(0));
		
	}
	
	
}

