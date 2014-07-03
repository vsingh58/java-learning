package com.netty.chd02;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created on 7/3/14.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                // Using default value
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecuter = new TimeServerHandlerExecutePool(
                    50, 10000);
            while(true){
                socket = server.accept();
                singleExecuter.execute(new TimeServerHandler(socket));
            }
        }finally {
            if (server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
