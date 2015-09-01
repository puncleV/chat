import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by dart on 01.09.15.
 * Поток для принятия отправляемых от сервера сообщений, нужен для того что бы мы в реальном времени получали сообщения
 * других пользователей
 * @author punkkk
 */
class ClientThread extends Thread{
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
