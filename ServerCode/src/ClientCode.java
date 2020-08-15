public class ClientCode
{
    public static void main(String[] argv)
    {
        new ClientSocketCode("127.0.0.1", 8080).start();//連線IP之後得更改，現在是本機連線
    }
}