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


				while(true) {

					System.out.println("\n"+connection.getInetAddress());
					DataInputStream in = new DataInputStream(connection.getInputStream());

					String in_str = in.readUTF();

					System.out.println("\tget : " + in_str);

					var pattern = Pattern.compile("\\!(\\w+)\\+(\\w+)");
					var matcher = pattern.matcher(in_str);

					if (matcher.find()) {
						String account = matcher.group(1);
						String password = matcher.group(2);

						DataOutputStream out = new DataOutputStream(connection.getOutputStream());

						String out_str;
						if (account.equals("test_acc_1") &&
								password.equals("30678")) {


							out_str = "!1";
						} else {

							out_str = "!0";
						}

						System.out.println("\treturn : " + out_str);
						out.writeUTF(out_str);
						out.flush();
					}

				}
			}catch(SocketException | EOFException e){
				System.out.println("\tconnection breaked!");

			}catch(IOException e){
				e.printStackTrace();

			}
		}
	}

}
