package practice.enip.sql;

import java.awt.color.ProfileDataException;
import java.util.*;

public interface Server_method {

    /* Account */
    default int authenticate(String account, String password) {
        return 0;
    }

    default int new_account(String account, String password, String name){
        return 0;
    }

    default int change_password(String old_password, String new_password) {
        return 0;
    }

    default int change_name(String name) {
        return 0;
    }

    /* Roles */
    default List<String> get_role(){
        return new ArrayList<>();
    }

    default int new_role(String name){
        return 0;
    }

    default int delete_role(String name){
        return 0;
    }

    default int change_role_name(String old_name, String new_name) {
        return 0;
    }


    /* Match */
    default int new_match(Match match){
        return 0;
    }

    default int get_match(){
        return 0;
    }
}
