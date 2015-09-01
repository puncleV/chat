import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dart on 01.09.15.
 */
public class Server {
    private int _port;
    private ServerSocket _sSocket;
    private Socket _socket;
    private DataInputStream _in;
    private DataOutputStream _out;

    public Server(int port) {
        setPort(port);
        setSocket();
    }

    public int getPort(){
        return this._port;
    }
    public void setPort(int port){
        this._port = port;
        System.out.println("New server port is: " + port);
    }
    public ServerSocket getSocket(){
        return this._sSocket;
    }
    public void setSocket(){
        try{
            this._sSocket = new ServerSocket(this._port);
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void setInputStream(){
        try {
            this._in = new DataInputStream(this._socket.getInputStream());
        }catch(IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void setOutputStream(){
        try {
            this._out = new DataOutputStream(this._socket.getOutputStream());
        }catch(IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void acceptServer( ){
        try {
            this._socket = this._sSocket.accept();
            System.out.println("Somebody is here...");
        }catch(IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void startServer(){
        this.acceptServer();
        this.setInputStream();
        String lineFromClient;
        try {
            while ((lineFromClient = this._in.readUTF()) != null){
                    System.out.println("Client: " + lineFromClient);
            }
        }catch(IOException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Somebody IS GONE!");
        this.startServer();
    }

    public static void main(String[] args){
        System.out.println("kek");
        Server server = new Server(1734);
        server.startServer();
    }
}
