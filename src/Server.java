import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by dart on 01.09.15.
 */
class Server{
    private int _port;
    private ServerSocket _sSocket;
    public Server(int port) {
        setPort(port);
        setSocket();
    }
    public void setPort(int port){
        this._port = port;
        System.out.println("New server port is: " + port);
    }
    public void setSocket(){
        try{
            this._sSocket = new ServerSocket(this._port);
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void runServer( ){
        ArrayList<Socket> _socketList = new ArrayList<>();
            while (true) {
                try {
                    Socket socket = this._sSocket.accept();
                    _socketList.add(socket);
                    new ServerThread(socket, _socketList);
                } catch(IOException err){
                    System.out.println(err.getMessage());
                }
            }
    }


    public static void main(String[] args){
        System.out.println("kek");
        Server server = new Server(1734);
        server.runServer();
    }
}
