import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by dart on 01.09.15.
 */
public class ServerThread extends Thread{
    protected Socket _socket;
    protected DataInputStream _in;
    protected DataOutputStream _out;
    public ServerThread( Socket socket ) {
        this._socket = socket;
        start();
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
    public void run(){
        this.setInputStream();
        this.setOutputStream();
        String lineFromClient;
        try {
            while ((lineFromClient = this._in.readUTF()) != null){
                System.out.println(lineFromClient);
                this._out.writeUTF(lineFromClient);
                this._out.flush();
            }
        }catch(IOException err) {
            System.out.println(err.getMessage());
        }
        System.out.println("Somebody IS GONE!");
    }
}
