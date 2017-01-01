package working;

import java.io.*;
import java.net.*;
/*
Web Server Class to invoke Multithreaded HTTP Server Communication
*/
public class WebServer {
    public static void main(String[] args)
        {
        String hostname = "localhost"; // Host Name to be used by the Server Instance
        int port = 9000; // Port Number to be used by Server Instance 
        File documentDir = new File(hostname); // 
        if (!documentDir.exists()&&!documentDir.isDirectory()) 
        {
            System.out.println("Host Directory Does not exist. Make directory ["+ documentDir.getAbsolutePath()+ " ] localhost to test Server Communication ");
        }
        else 
        {
            System.out.println("Web Server Instance is Live");
            try (ServerSocket serverTest = new ServerSocket(port)) { 
                while (true)
                {
                    Socket socket = serverTest.accept();
                    //Invoke new 
                    new Thread(new HttpRequest(socket, documentDir)).start(); 
                }
            }
            catch (IOException e) 
            {
                System.err.println("Error Accepting Connection on port "+ port);
            }
        }
    }
}
