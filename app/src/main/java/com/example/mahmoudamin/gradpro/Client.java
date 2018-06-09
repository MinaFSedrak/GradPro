package com.example.mahmoudamin.gradpro;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Lobna on 09-Jun-18.
 */

public class Client extends AsyncTask<byte[],Void,Void> {

    Socket send;
    Handler handler = new Handler();
    DataOutputStream dos;
    PrintWriter pw;
    String ip = "192.168.1.28";
    int port = 7755;
    Context ctx;
    ProgressDialog mProgress;


    Client(Context ctx, ProgressDialog mProgress){
        this.ctx = ctx;
        this.mProgress = mProgress;

    }

    @Override
    protected Void doInBackground(byte[]... voids) {


        try {
            send = new Socket(ip,port);
            OutputStream outputStream = send.getOutputStream();
            dos = new DataOutputStream(outputStream);
            dos.writeInt(voids[0].length);
            dos.write(voids[0], 0, voids[0].length);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx, "image Sent", Toast.LENGTH_SHORT).show();
                    mProgress.dismiss();
                }
            });
            dos.close();
            outputStream.close();
            send.close();
//            pw = new PrintWriter(send.getOutputStream());
//            pw.write(msg);
//            pw.flush();
//            pw.close();
            send.close();
        } catch (IOException e) {
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx, "image error ", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return null;
    }
}
