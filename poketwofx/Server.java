/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketwofx;

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
 
// Server class
public class Server 
{
    public static final int PORT=4444;
    public static void main(String[] args) throws IOException 
    {
        new Server().runServer();
    }
    public void runServer() throws IOException{
        ServerSocket ss=new ServerSocket(PORT);
        System.out.println("El sistema está listo para nuevas conexiones");
        while(true){
        Socket socket=ss.accept();
        new ClientHandler(socket).start();
        }
    }
}