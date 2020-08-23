import java.net.*;
import java.io.*;
import java.sql.SQLException;
import practice.enip.sql.Account;


public class server_socket
{
	final private static int port = 8081;
	private ServerSocket sc;


	public static void main(String[] args)throws IOException
	{
		try( ServerSocket ss = new ServerSocket(port))
		{
			while(true)
			{
				System.out.println("waiting connect......");
				Socket cs= ss.accept();
				System.out.println("connect accept�I");

				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							DataInputStream in = new DataInputStream(cs.getInputStream());
							DataOutputStream out = new DataOutputStream(cs.getOutputStream());

							String account = in.readUTF();
							String password = in.readUTF();
							String act = in.readUTF();

							try(Account acc = new Account())
							{
								Integer login_status = acc.authenticate(account, password);

								switch(act)
								{
									case "@1":

										if(login_status.equals(1)){
											System.out.println("\t There is a Valid Login for " + account);
										}else{
											System.out.println("\t There is a FAILED Login for " + account);
										}

										out.writeUTF(login_status.toString());
										break;
									default:
										System.err.println("No matching behaviour!!!!");
								}
							}
							catch(SQLException e)
							{
								e.printStackTrace();
							}


							cs.close();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}).start();
			}
		}catch(SocketException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

}
