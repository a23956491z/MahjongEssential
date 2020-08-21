import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class client_socket
{
	private static Integer identityCode;

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

			identityCode = new Date().hashCode() + sc.getLocalAddress().hashCode();

			System.out.println(InetAddress.getLocalHost()+ " connected to " + sc.getInetAddress());
			System.out.println("IdentityCode is " + identityCode);
			while(true){

				Scanner scanner = new Scanner(System.in);

				out.writeUTF(identityCode.toString());

				String str_in = scanner.nextLine();
				out.writeUTF(str_in);
				out.flush();

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
