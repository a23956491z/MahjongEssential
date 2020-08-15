import java.net.*;
import java.io.*;

public class ClientSocketCode extends Thread
{
    private Socket cs;
    
    public ClientSocketCode(String ip, int port)
    {
        try
        {
            cs = new Socket(ip, port);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        try
        {
            if (cs != null)
            {
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                System.out.println("Server:" + reader.readLine());
            
                cs.close();
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}