package com.monitor.BleUtils;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import com.vise.log.ViseLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wjg on 2017/6/11.
 */

public class ConnectedThread extends Thread {

    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private Handler mHandler;

    public final static int MESSAGE_READ = 1;

    public ConnectedThread(BluetoothSocket socket, Handler handler) {
        mmSocket = socket;
        mHandler = handler;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = socket.getInputStream();
        } catch (IOException e) {
        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buff = new byte[1024*32];
        int bytes;
        while (true) {
            try {
                bytes = mmInStream.read(buff);
                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buff)
                        .sendToTarget();
                ViseLog.d(bytes+"个,"+System.currentTimeMillis());
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }

    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            mmOutStream.write(bytes);
        } catch (IOException e) {
        }
    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
        }
    }

}
