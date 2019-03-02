/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 
// Client class
public class Client 
{
    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException
    {
        Socket socket=new Socket("localhost", Server.PORT);
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        while(true){
            Message message=new Message(2,4);
            oos.writeObject(message);
            Message returnMessage=(Message)ois.readObject();
            System.out.println(returnMessage.getResult());
        }
    
    }

}