package working;
import java.io.*;
import java.net.*;
import java.util.regex.*;

/*
Student Name - Priyanka Penikalapati, Student ID- 999991075
Class - MCIS 6163 - Section 032	- Project 1

HttpRequest class implements Runnable interface. 
This enables multiple instances of HttpRequest classes to be executed by a thread. 
Overridden run method to include source code for Server client communication. 
*/

final class HttpRequest implements Runnable {

    private static final Pattern REQUEST_PATTERN = Pattern.compile("^GET (/.*) HTTP/1.[01]$");
    private final File documentDir;
    private final Socket socket;
    public HttpRequest(Socket socket, File documentDir) {
        this.socket = socket;
        this.documentDir = documentDir;
    }
    //readRequest Method reads Response from Web Server over HTTP
    private String readRequest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.socket.getInputStream()));
        String firstLine = reader.readLine();
        if (firstLine == null) {
            return null;
        }
        Matcher matcher = REQUEST_PATTERN.matcher(firstLine);
        System.out.println("Host Address "+this.documentDir.toString());
        System.out.println("Port "+socket.getLocalPort());
        System.out.println(socket.getLocalSocketAddress().toString());
        return matcher.matches() ? matcher.group(1) : null;
    }   
    //sendResponse Method send Response to Web Server over HTTP
    private OutputStream sendResponse(int requeststatus, String responsemessage, long len) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.0 ");
        response.append(requeststatus).append(' ').append(responsemessage).append("\r\n");
        response.append("Content-Length: ").append(len).append("\r\n\r\n");
        OutputStream out = this.socket.getOutputStream();
        out.write(response.toString().getBytes());
        out.flush();
        System.out.println("Host Address "+this.documentDir.toString());
        System.out.println("Port "+socket.getLocalPort());
        System.out.println(socket.getLocalSocketAddress().toString());
        System.out.println("Request Status "+requeststatus);
        System.out.println("Server Response"+responsemessage);

        return out;
    }
    //sendError Method send Error Response to Web Server over HTTP
    private int sendError(int requeststatus, String responsemessage) throws IOException {
        OutputStream out = sendResponse(requeststatus, responsemessage, responsemessage.length());
        out.write((responsemessage + "\nerror number :" + requeststatus).getBytes());
        out.flush();
        System.out.println("Host Address "+this.documentDir.toString());
        System.out.println("Port "+socket.getLocalPort());
        System.out.println(socket.getLocalSocketAddress().toString());
        System.out.println("Request Status "+ requeststatus);
        System.out.println("Server Response"+responsemessage);
        return requeststatus;
    }
    //fileCommunication Method handle File Processing 
    private long fileCommunication(File requestfile) throws IOException {
        long len = requestfile.length();
        OutputStream out = sendResponse(200, "OK", len);
        InputStream in = new FileInputStream(requestfile);
        try {
            byte[] buffer = new byte[1024];
            int nread = 0;
            while ((nread = in.read(buffer)) > 0) {
                out.write(buffer, 0, nread);
            }
        } finally {
            in.close();
        }
        System.out.println(out);
        out.flush();
        return len;
    }
    // Overridden run method to enable Server to handle multiple requests using Multithreading 
    public void run() {
        int status;
        status = 200;
        long len;
        len = 0;
        String path;
        path = null;
        try {
            path = readRequest();
            if (path.equals("/")) {
                path = path + "welcome.txt";
                System.out.println("RETTEIVING FILE IS " + this.documentDir.getAbsolutePath() + path);
            }
            if (path == null) {
                status = sendError(400, "Bad Request");
            } else {
                File file = new File(this.documentDir, path);
                if (!file.getAbsolutePath().startsWith(this.documentDir.getAbsolutePath()) || (file.exists() && (!file.isFile() || !file.canRead()))) {
                    status = sendError(403, "Forbidden Access ");
                } else if (!file.exists()) {
                    status = sendError(404, "File Not Found");
                } else {
                    len = fileCommunication(file);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while serving request for " + e.getMessage());
        } finally {
            try {
                //Close Network Socket 
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Error while closing socket to " + e.getMessage());
            }
        }
    }
}