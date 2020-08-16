import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientSocketCode
{

	public static void main(String[] args)
    {
    	startClient(8080);

    }

    public static void startClient(int port){

		try(Socket sc = new Socket())
		{

			int timeout = 2000;
			InetAddress address = InetAddress.getLocalHost();
			SocketAddress sc_new_connect = new InetSocketAddress(address, port);
			System.out.println("Connecting");

			sc.connect(sc_new_connect, timeout);

			DataInputStream in = new DataInputStream(sc.getInputStream());
			DataOutputStream out = new DataOutputStream(sc.getOutputStream());

			System.out.println(sc.getLocalAddress()+ " connected to " + sc.getInetAddress());

			Scanner scanner = new Scanner(System.in);
			System.out.print("account : ");
			String account = scanner.nextLine();
			System.out.print("password : ");
			String password = scanner.nextLine();

			String account_password_format = "!" + account + "+" + password;
			out.writeUTF(account_password_format);
			out.flush();

			String response = in.readUTF();
			System.out.println("response : " + response);


		}catch(SocketTimeoutException e){
			System.out.println("Timeout!");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}