package practice.enip.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {

	List<Map<String, Integer>> round_record = new ArrayList<Map<String, Integer>>();
	int have_round;
	public Match() {
		have_round = 0;
		round_record.add(0, new HashMap<String, Integer>());
		// TODO Auto-generated constructor stub
	}

	public void new_round_auto_increase(Map<String, Integer> name_score){

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
		return 1;
	}

}
