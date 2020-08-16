import java.net.*;
import java.io.*;

public class ClientSocketCode
{
	public static void main(String[] args)
    {
        try
        {
        	Socket cs = new Socket("127.0.0.1", 8080);//連線IP之後得更改，現在是本機連線
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(cs.getOutputStream()));
        	BufferedReader reader = new BufferedReader(new InputStreamReader(cs.getInputStream()));
        	while(true)
        	{
        		String str = reader.readLine();
        		writer.write(str);
        		writer.write("\n");
        		writer.flush();
        	}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}