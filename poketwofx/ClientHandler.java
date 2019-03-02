
package poketwofx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
class ClientHandler extends Thread 
{
    Socket socket=null;
    ClientHandler(Socket socket){
        this.socket=socket;
    }
    public void run(){
        Message message=null;
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            while((message=(Message)ois.readObject())!=null){
                doSomething(message);
                oos.writeObject(message);
            }
        socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    private void doSomething(Message message){
        message.setResult(message.getFirstNumber()*message.getSecondNumber());
    }
}
