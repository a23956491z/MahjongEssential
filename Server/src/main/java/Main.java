import practice.enip.sql.Account;
import practice.enip.sql.Match;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public final static boolean TESTING = true;

    public static void main(String[] args) {


        try(Account sql = new Account()){
        	try(Scanner scanner = new Scanner(System.in)){
        	
        	String account, password, name;
        	//new_account(scanner, sql);

            while(true){

                if(TESTING){
                    account = "test_acc_1";
                    password = "30678";

                    System.out.println("Default account : " + account);
                }else{


                System.out.print("account : ");
                if((account = scanner.nextLine()) == null){break;}

                System.out.print("password : ");
                password = scanner.nextLine();

                }
                switch (sql.authenticate(account, password)) {
                    case 0:
                    	
                        System.out.print("password is wrong!\n\n");
                        break;
                    case 1:
                    	
                        System.out.print("login successfully\n\n");
                        
                        name = sql.get_name();
                        if("".equals(name)) {
                        	System.out.println("Has no name!");
                        }else {
                        	System.out.println("Name is " + name);
                        }


                        System.out.println("Role names : ");
                        List<String> arr= sql.get_role_nameList();
                        for(var i : arr){
                            System.out.println("\t" + i);
                        }


/*
                        Match match = new Match();
                        match.new_round_auto_increase( Stream.of(
                                new AbstractMap.SimpleEntry<>("coddy", 2),
                                new AbstractMap.SimpleEntry<>("handy", -2))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        );
                        match.new_round_auto_increase( Stream.of(
                                new AbstractMap.SimpleEntry<>("leo", 5),
                                new AbstractMap.SimpleEntry<>("mikey", -5))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        );
                        match.new_round_auto_increase( Stream.of(
                                new AbstractMap.SimpleEntry<>("handy", 11),
                                new AbstractMap.SimpleEntry<>("mikey", -5),
                                new AbstractMap.SimpleEntry<>("coddy", -3),
                                new AbstractMap.SimpleEntry<>("leo", -3))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        );
                        match.new_round_auto_increase( Stream.of(
                                new AbstractMap.SimpleEntry<>("handy", 4),
                                new AbstractMap.SimpleEntry<>("leo", -4))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        );
                        match.new_round_auto_increase( Stream.of(
                                new AbstractMap.SimpleEntry<>("mikey", 3),
                                new AbstractMap.SimpleEntry<>("leo", -3))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        );

                        System.out.println(match.get_round());


                        int status = sql.new_match(match);
                        System.out.println(status);
*/
                        sql.get_match();


/*
                        if(sql.new_role("Jessy2")==1){
                           // sql.print_role();
                            System.out.println("new role successfully!");
                        }else{
                            System.out.println("new role FAILED!");
                        }

                        if(sql.change_role_name("Jessy2", "Jessy3") == 1){
                           // sql.print_role();
                            System.out.println("change name successfully!");
                        }else{
                            System.out.println("change name FAILED!");
                        }


                        if(sql.delete_role("Jessy3")==1){
                            //sql.print_role();
                            System.out.println("delete role successfully!");
                        }else{
                            System.out.println("delete role FAILED!");
                        }
*/

/*

                        String old_password, new_password, new_name;
                        System.out.println("change password !");
                        
                        System.out.print("old password : ");
                        old_password = scanner.nextLine();
                        
                        System.out.print("new password : ");
                        new_password = scanner.nextLine();
                        
                        System.out.println(sql.get_password());
                        switch(sql.change_password(old_password, new_password)) {
                        case 0:
                        	
                        	System.out.println("the old password is not correct");
                        	break;
                        case 1:
                        	
                        	System.out.println("successfully changed password");
                        	break;
                        default:
                        	
                        	System.out.println("Error!");
                        }
                        System.out.println("");
*/

                        /*
                        System.out.println("change name !");
                        System.out.print("name : ");
                        new_name = scanner.nextLine();
                        if(sql.change_name(new_name) == 1){
                        	System.out.println("successfully changed name");
                        }
                        */
                        
                        break;
                    default:
                    	
                        System.out.print("account not found !\n\n");
                }
                if(TESTING) break;
            }}


        }catch(NoSuchElementException e){

            System.out.println("terminated....");
            
            
        }catch(Exception e) {
        	e.printStackTrace();
        }


    }
    
    @SuppressWarnings("unused")
	private static void new_account(Scanner scanner, Account account_connector) {

    	String account, password, name;
    	
    	
    	System.out.println("add new account!");
    	
    	System.out.print("account :");
    	account = scanner.nextLine();
    	
        System.out.print("password : ");
        password = scanner.nextLine();
        
    	System.out.print("name :");
    	name = scanner.nextLine();
    	
    	
    	if((account_connector.new_account(account, password, name)) == 0){
            System.out.println("account already exists!! ");
    	}
    	
    }
}
