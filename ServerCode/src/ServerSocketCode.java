import java.net.*;
import java.io.*;

public class ServerSocketCode
{
	public static void main(String[] args)throws IOException
	{
		ServerSocket ss = new ServerSocket(8080);
    
		while(true)
		{
			System.out.println("waiting connect......");
			Socket cs= ss.accept();
			System.out.println("connect accept¡I");
        
			new Thread(new Runnable()
			{
				@Override
			    public void run()
			    {
					BufferedReader reader = null;            
			        try
			        {
			            //ss.close();
			            reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			            String str=reader.readLine();
			            System.out.println("Client: " + str);
			            
			            cs.close();
			        }
			        catch (IOException e)
			        {
			            e.printStackTrace();
			        }
			    }
			}).start();						
		}
	}
}