import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class client_socket
{
	final private static int port = 8081;
	private static Integer identityCode;

	public static void main(String[] args)
	{
		ClientLogin("abc123","123123");

	}
	public static boolean ClientLogin( String account, String password)
	{
		try(Socket cs = new Socket("127.0.0.1", port))
        {
        	int timeout = 2000;
        	SocketAddress sa = new InetSocketAddress("127.0.0.1", port);
        	System.out.println("waiting connect......");
        	cs.connect(sa, timeout);

        	DataInputStream in = new DataInputStream(cs.getInputStream());
			DataOutputStream out = new DataOutputStream(cs.getOutputStream());

			String act = new String("@1");
			out.writeUTF(account);
			out.writeUTF(password);
			out.writeUTF(act);

			String result = in.readUTF();
			switch(result)
			{
				case "1":
					System.out.println("Login Successful");
					return true;
				default:
					System.out.println("Login fail");
			}

        }catch(SocketTimeoutException e)
        {
			System.out.println("Timeout");
		}
        catch (IOException e)
        {
            e.printStackTrace();
        }

		return false;
	}
}
