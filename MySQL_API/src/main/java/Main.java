import practice.enip.sql.Account;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

  
        try(Account sql = new Account();){
        	try(Scanner scanner = new Scanner(System.in)){
        	
        	String account, password, name;
        	//new_account(scanner, sql);
        	
            while(true){
                System.out.print("account : ");
                if((account = scanner.nextLine()) == null){break;}

                System.out.print("password : ");
                password = scanner.nextLine();


                switch (sql.authenticate(account, password)) {
                    case 0:
                    	
                        System.out.print("password is wrong!\n\n");
                        break;
                    case 1:
                    	
                        System.out.print("correct\n\n");
                        
                        name = sql.get_name();
                        if("".equals(name)) {
                        	System.out.println("Has no name!");
                        }else {
                        	System.out.println("Name is " + name);
                        }
                        
                        //sql.print_role();
                        
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
                        
                        System.out.println("change name !");
                        System.out.print("name : ");
                        new_name = scanner.nextLine();
                        if(sql.change_name(new_name) == 1){
                        	System.out.println("successfully changed name");
                        }
                        
                        break;
                    default:
                    	
                        System.out.print("account not found !\n\n");
                }
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
