import java.net.*;
import java.io.*;

public class client_socket
{
	public static void main(String[] args)
	{
		ClientLogin("abc123","123123");
	
	}
	public static boolean ClientLogin( String account, String password)
	{
		try(Socket cs = new Socket("127.0.0.1", 8080))
        {
        	int timeout = 2000;
        	SocketAddress sa = new InetSocketAddress("127.0.0.1", 8080);
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
					return false;
			}
			
        }catch(SocketTimeoutException e)
        {
			System.out.println("Timeout");
		}
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}
}