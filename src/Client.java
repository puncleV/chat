import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by dart on 01.09.15.
 */
public class Client {
    private int _serverPort;
    private InetAddress _serverAddress;
    private Socket _socket;
    private DataInputStream _in;
    private DataOutputStream _out;
    private BufferedReader _keyboard;
    private String _name;
    public Client( int port, String address){
        this._serverPort = port;
        this.setAddress(address);
    }
    public void setName(String name){
        this._name = name;
    }
    public void setAddress(String address) {
        try {
            this._serverAddress = InetAddress.getByName(address);
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }

    public String getAddress() {
        return this._serverAddress.toString();
    }

    public void setPort(int port){
        this._serverPort = port;
    }

    public void setSocket() {
        try {
            this._socket = new Socket(this._serverAddress, this._serverPort);
        }catch(IOException err){
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
    public void set_keyboard() {
        this._keyboard = new BufferedReader(new InputStreamReader(System.in));
    }
    public void startClient(){
        this.setSocket();
        this.setOutputStream();
        this.setInputStream();
        this.set_keyboard();
        System.out.println("Server address:" + this.getAddress() + " port: " + this._serverPort);
        String line = null;
        try {
            System.out.println("Type something");
            while (true) {
                line = _keyboard.readLine();
                if(line.equalsIgnoreCase("exit")){
                    break;
                }
                System.out.println("Sending...");
                this._out.writeUTF(this._name + ": " + line);
                this._out.flush();
                line = _in.readUTF();
                System.out.println("You: " + line);
            }
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    public static void main(String[] args){
        Client client = new Client(1734,"127.0.0.1");
        client.setName( args[0] );
        client.startClient();
    }
}