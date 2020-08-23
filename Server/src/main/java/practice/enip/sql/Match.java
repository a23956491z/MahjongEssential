package practice.enip.sql;

import java.util.*;

public class Match {

	List<Map<String, Integer>> round_record = new ArrayList<Map<String, Integer>>();
	Set<String> role_name_set = new HashSet<String>();

	private int have_round;
	String account;
	String match_id;

	public Match( ) {

		have_round = 0;
		round_record.add(0, new HashMap<String, Integer>());
		// TODO Auto-generated constructor stub
	}

	public void import_match(List<Map<String, Integer>> record){

		for(var mp : record){
			for(String i : mp.keySet()){
				role_name_set.add(i);
			}
		}

		round_record = record;
		have_round = record.size();
	}
	public void new_round_auto_increase(Map<String, Integer> name_score){

		for(String i : name_score.keySet()){
			role_name_set.add(i);
		}
		have_round++;
		round_record.add(have_round, name_score);
	}

	public int round_add_player_score(int round, String role, int score){
		if(round == round_record.size()){
			new_round_auto_increase(new HashMap<String, Integer>());
		}else if(round > round_record.size()){
			return 0;
		}

		round_record.get(round).put(role, score);
		role_name_set.add(role);
		return 1;
	}

	public String get_id(){
		return match_id;
	}
	public int total_round(){ return have_round;}

	public Map<String, Integer> get_round(int round){
		return round_record.get(round);
	}

	public List<Map<String, Integer>> get_match(){
		return round_record;
	}

	public void print(){
		System.out.println("match_serial_number : " + match_id);

		for(var round = 1; round <= have_round; round++){
			System.out.println("\t" + round + " :  ");

			for(Map.Entry entry : round_record.get(round).entrySet()) {
				System.out.println("\t\t"+ entry.getKey() + " : "+ entry.getValue() + "  ");

			}
			//System.out.println("");
		}
	}
}
