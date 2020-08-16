import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

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

			while(true) {

				var scanner = new Scanner(System.in);
				System.out.print("account : ");
				String account = scanner.nextLine();
				System.out.print("password : ");
				String password = scanner.nextLine();

				String account_password_format = "!" + account + "+" + password;
				out.writeUTF(account_password_format);
				out.flush();

				String response = in.readUTF();

				var pattern = Pattern.compile("\\!([01])");
				var matcher = pattern.matcher(response);


				if(matcher.find()){
					boolean if_authenticated = matcher.group(1).equals("1") ? true : false;

					if(if_authenticated) {
						System.out.println("Login successful!");
						break;
					}else{
						System.out.println("Login Failed!");
					}
				}
			}


		}catch(SocketTimeoutException e){
			System.out.println("Timeout!");
		}
		catch(IOException e){
			System.out.println("wtf here");
			e.printStackTrace();
		}
	}

}
