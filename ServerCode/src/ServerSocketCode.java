import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.*;

public class ServerSocketCode implements Runnable
{
	private int port;
	private ServerSocket sc;


	public static void main(String[] args)throws IOException
	{
		Thread socket_thread = new Thread(new ServerSocketCode(8080));
		socket_thread.start();
	}
	public ServerSocketCode(int port) throws IOException{
		this.port  = port;
		this.sc = new ServerSocket(port);
	}

	public void run(){

		while(true){
			System.out.println("\nwaiting connection...");
			try(Socket connection = this.sc.accept()){


				System.out.println(connection.getInetAddress());

				System.out.println("get data : ");
				DataInputStream in = new DataInputStream(connection.getInputStream());

				String in_str = in.readUTF();
				System.out.println("\t" + in_str);

				Pattern account_password = Pattern.compile("\\!(\\w+)\\+(\\w+)");

				StringTokenizer st = new StringTokenizer(in_str, "\\!(\\w+)\\+(\\w+)");
				if(st.hasMoreTokens()){
					String account = st.nextToken();
					String password = st.nextToken();

					DataOutputStream out = new DataOutputStream(connection.getOutputStream());

					if(account.equals("test_acc_1") &&
					   password.equals("30678")){

						out.writeUTF("account & password is correct!");

					}else{

						out.writeUTF("account or password is wrong!");
					}


					out.flush();
				}


			}catch(IOException e){
				e.printStackTrace();

			}
		}
	}

}
