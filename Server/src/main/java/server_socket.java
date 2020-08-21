import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.*;

public class server_socket
{
	final private static int port = 8080;
	private ServerSocket sc;


	public static void main(String[] args)throws IOException
	{

		new server_socket().startServer();
	}

	public void startServer(){
		final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

		Runnable serverTask = new Runnable() {
			@Override
			public void run() {
				try{
					ServerSocket serverSocket = new ServerSocket(port);
					System.out.println("Waiting connection...");

					while(true){
						Socket clientSocket = serverSocket.accept();
						clientProcessingPool.submit(new ClientTask(clientSocket));
					}

				}catch(BindException e){
					System.err.println("This connection port already in use \n" +
										"\tuse another port or stop the existing server!");
					e.printStackTrace();
				} catch(IOException e){
					System.err.println("Unable to process client request");
					e.printStackTrace();
				}
			}
		};

		Thread serverThread = new Thread(serverTask);
		serverThread.start();


	}

	private class ClientTask implements Runnable{
		private final Socket clientSocket;
		private String identityCode;
		private InetAddress address;


		private ClientTask(Socket clientSocket){

			this.clientSocket = clientSocket;
			address =  clientSocket.getInetAddress();
		}

		@Override
		public void run(){

			try {

				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

				while(true) {

					String identityCode = in.readUTF();

					String in_str = in.readUTF();
					System.out.println("\n" + clientSocket.getInetAddress() +" as identityCode : "+ identityCode);

					System.out.println("\tget : " + in_str);
				}
			}catch(SocketException e) {
				System.out.println("A connection break from "+ address +" !!");
			}
			catch(IOException e){

				e.printStackTrace();
			}




			try{
				clientSocket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
