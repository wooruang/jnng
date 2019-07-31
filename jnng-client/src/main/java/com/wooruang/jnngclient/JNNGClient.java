package com.wooruang.jnngclient;

import com.wooruang.jnng.ClientSocket;
import com.wooruang.jnng.Message;
import com.wooruang.jnng.Socket;
import com.wooruang.jnng.jni.NNG;
import com.wooruang.jnng.jni.NNGSocket;
import com.wooruang.jnng.jni.protocol.reqrep0.Req;

public class JNNGClient {

    static {
        System.loadLibrary("jnng");
    }

    public static void main(String[] args) {
        System.out.println("Start nng client.");

        String url;
        if (args.length <= 0) {
            url = "tcp://127.0.0.1:55888";
            System.out.println("No argument for url.");
            System.out.println("ex) {protocol}://{ip}:{port}");
            System.out.println("Default: " + url);
        } else {
            url = args[0];
            System.out.println("URL: " + url);
        }

        clientJNNG(url);
//        clientNative(url);
    }

    public static void clientJNNG(String url) {
        ClientSocket client = new ClientSocket();

        int ret = client.openAsProtocol(Socket.Protocol.Req0);
        if (ret != 0) {
            System.out.println(String.format("client.openAsProtocol : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        ret = client.dial(url);
        if (ret != 0) {
            System.out.println(String.format("client.dial : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        Message sendMsg = new Message("Test Client");

        ret = client.send(sendMsg);
        if (ret != 0) {
            System.out.println(String.format("client.send : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        Message receiveMsg = new Message(50);

        ret = client.recv(receiveMsg);
        if (ret != 0) {
            System.out.println(String.format("client.recv : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }
        System.out.println("Recv : " + receiveMsg.getString());

        client.close();
    }

    public static void clientNative(String url) {
        NNGSocket socket = new NNGSocket();
        System.out.println(String.format("socket : %x", socket.id));

        int ret = Req.nng_req0_open(socket);
        if (ret != 0) {
            System.out.println(String.format("nng_req0_open : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        ret = NNG.nng_dial(socket, url, 0, 0);
        if (ret != 0) {
            System.out.println(String.format("nng_dial : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        String data = "tteesstt";
        byte[] buffer;

        try {
            buffer = data.getBytes();
        } catch (Exception e) {
            System.out.println(e.toString());
            buffer = new byte[1];
        }

        ret = NNG.nng_send(socket, buffer, 0);
        if (ret != 0) {
            System.out.println(String.format("nng_send : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        byte[] recv_buf = new byte[50];
        long[] recv_len = new long[1];
        ret = NNG.nng_recv(socket, recv_buf, recv_len, 0);
        if (ret != 0) {
            System.out.println(String.format("nng_recv : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        for (int i = 0; i < recv_len[0]; ++i) {
            System.out.print(String.format("B %d ", recv_buf[i]));
        }
        System.out.println();
        System.out.println("Recv : " + new String(recv_buf));

        NNG.nng_close(socket);
    }
}
