import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by dart on 01.09.15.
 */
public class ClientThread extends Thread{
    private DataInputStream _in;
    private Socket _socket;
    public ClientThread(Socket socket){
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
    public void run(){
        String line;
        this.setInputStream();
        try {
            while ((line = this._in.readUTF()) != null) {
                System.out.println(line);
            }
        }catch (IOException err){
            System.out.println("kek");
        }
    }
}
