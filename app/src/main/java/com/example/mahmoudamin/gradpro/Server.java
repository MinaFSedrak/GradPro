package com.example.mahmoudamin.gradpro;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Lobna on 09-Jun-18.
 */

public class Server implements Runnable{

    Socket recive;
    ServerSocket reciver;
    InputStreamReader isr;
    BufferedReader bufferedReader;
    Handler h = new Handler();
    String msg;
    Context ctx;

    Server(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try{
            reciver = new ServerSocket(7801);
            while (true){
                recive = reciver.accept();
                isr = new InputStreamReader(recive.getInputStream());
                bufferedReader = new BufferedReader(isr);
                msg = bufferedReader.readLine();
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
