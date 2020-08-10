import java.net.*;
import java.io.*;

public class ServerSocketCode
{
	public static void main (String[] args) throws IOException
	{
		ServerSocket ss = new ServerSocket(8080);

		while (true)
		{
			Socket cs = ss.accept();

			new Thread( new Runnable() 
			{
				@Override

				public void run()
				{
					try
					{
						cs.close();
					}
					catch(Exception e){e.printStackTrace();}
				}

			}).start();

		ss.close();
		}
		
		
	}
	
}