package com.example.xxknapp;

import java.io.OutputStream;
import java.net.Socket; // Import corretto per Socket
import java.net.UnknownHostException;
import java.io.IOException;
public class KNXClient {
    public interface KNXCallback {
        void onSuccess();
        void onError(Exception e);
    }

    public static void sendCommand(byte control, byte address, byte payload, KNXCallback callback) {
        new Thread(() -> {
            try (Socket socket = new Socket("192.168.0.47", 3671)) {
                OutputStream outputStream = socket.getOutputStream();
                byte[] telegram = createKNXTelegram(control, address, payload);
                outputStream.write(telegram);
                outputStream.flush();
                callback.onSuccess();
            } catch (Exception e) {
                callback.onError(e);
            }
        }).start();
    }

    private static byte[] createKNXTelegram(byte control, byte address, byte payload) {
        return new byte[]{
                (byte) 0x06, // Header
                (byte) 0x10, // Versione KNX
                (byte) 0x04, (byte) 0x20, // Servizio Data
                (byte) 0x00, (byte) 0x0B, // Lunghezza totale
                control,
                address,
                (byte) 0x00, (byte) 0x01,
                payload,
                (byte) 0x0F // Checksum
        };
    }
}
