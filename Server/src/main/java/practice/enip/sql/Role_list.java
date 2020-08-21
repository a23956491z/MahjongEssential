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
	
	public int role_id;
	public String role_name;

	//store serial number
	public List<String> match = new ArrayList<String>();
	
	/**
	 * 
	 */
	public Role(Account account) {
	}
	public Role(int id, String name) {
		role_id = id;
		role_name = name;
	}
	
	public int get_id(){
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

	
	public List<String> get_name_list(){
		return name_list;
	}
	
	public int get_id_by_name(String name) throws NoSuchElementException {
		
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

	public void print(){

		System.out.println("test name list");
		List<String> arr= get_name_list();
		for(var i : arr){
			System.out.println("\t" + i);
		}

		System.out.println("test role list");
		for(var i : role_list){
			System.out.print("\t");
			System.out.print(i.role_id);
			System.out.println("  " + i.role_name);
		}
	}

	public int add_role(int id, String name) {

		if(if_exists(name)) {
			return 0;
		}

		role_list.add(new Role(id, name));
		name_list.add(name);
		return 1;
	}

	public int remove_role(String name){

		if(if_exists(name) == false) {
			return 0;
		}

		role_list.removeIf(x -> x.role_name.equals(name));
		name_list.removeIf(x -> x.equals(name));
		return 1;
	}

	public int change_name(String old_name ,String new_name){
		if(if_exists(old_name) == false) {
			return 0;
		}

		int index = role_list.indexOf(role_list.stream().filter(x -> x.role_name.equals(old_name)).findFirst().get());
		role_list.set(index, new Role( get_id_by_name(old_name), new_name));

		index = name_list.indexOf(old_name);
		name_list.set(index, new_name);

	return 1;

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

