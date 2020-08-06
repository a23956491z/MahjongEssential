import practice.enip.sql.Account;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

  
        try(Account sql = new Account();){
        	try(Scanner scanner = new Scanner(System.in)){
        	
        	String account, password, name;
        	
        	System.out.println("add new account!");
        	
        	System.out.print("account :");
        	account = scanner.nextLine();
        	
            System.out.print("password : ");
            password = scanner.nextLine();
            
        	System.out.print("name :");
        	name = scanner.nextLine();
        	
        	if((sql.new_account(account, password, name)) == 0){
                System.out.println("account already exists!! ");
        	}
        	
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
}
