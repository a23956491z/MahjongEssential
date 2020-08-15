import java.net.*;
import java.io.*;

public class ServerSocketCode extends Thread
{
    private ServerSocket ss;
    private Socket cs;
    
    public ServerSocketCode(int port)
    {
        try
        {
            ss = new ServerSocket(port);
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
            System.out.println("等待連線......");
            cs = ss.accept();
            System.out.println("連線成功！");
            
            ss.close();
            
            BufferedReader reader;            
            reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));            
            System.out.println("Client: " + reader.readLine());
           
            cs.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}