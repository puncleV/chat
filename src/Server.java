import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by dart on 01.09.15.
 */
public class Server{
    private int _port;
    private ServerSocket _sSocket;
    private ArrayList<Socket> _socketList;
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
    public void runServer( ){
        _socketList = new ArrayList<Socket>();
        try {
            while (true) {
                Socket socket = this._sSocket.accept();
                _socketList.add(socket);
                new ServerThread (socket, _socketList);
            }
        }catch(IOException err){
            System.out.println(err.getMessage());
        }
    }


    public static void main(String[] args){
        System.out.println("kek");
        Server server = new Server(1734);
        server.runServer();
    }
}
