import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by dart on 01.09.15.
 */
class ServerThread extends Thread{
    protected Socket _socket;
    protected DataInputStream _in;
    protected DataOutputStream _out;
    private ArrayList<Socket> _socketList;
    public ServerThread( Socket socket, ArrayList<Socket>  socketList ) {
        this._socket = socket;
        this._socketList = socketList;
        start();
    }
    public void setInputStream(){
        try {
            this._in = new DataInputStream(this._socket.getInputStream());
        }catch(IOException err){
            System.out.println(err.getMessage());
        }
    }
    public void run(){
        this.setInputStream();
        String lineFromClient;
        try {
            while ((lineFromClient = this._in.readUTF()) != null){
                System.out.println(lineFromClient);
                for (Socket a_socketList : this._socketList) {
                    try {
                        this._out = new DataOutputStream(a_socketList.getOutputStream());
                        this._out.writeUTF(lineFromClient);
                        this._out.flush();
                    } catch (IOException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
        }catch(IOException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Somebody IS GONE!");
    }
}
