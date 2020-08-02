import practice.enip.sql.Database_connector;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Database_connector sql = new Database_connector();

        Scanner scanner = new Scanner(System.in);

        try{
            while(true){
                System.out.print("account : ");
                String account = scanner.nextLine();
                System.out.print("password : ");
                String password = scanner.nextLine();


                switch (sql.authenticate(account, password)) {
                    case 0:
                        System.out.print("password is wrong!\n\n");
                        break;
                    case 1:
                        System.out.print("correct\n\n");
                        break;
                    default:
                        System.out.print("account not found !\n\n");
                }
            }
        }catch(Exception e){
            System.out.println("terminated....");
        }
    }
}
