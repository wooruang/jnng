package com.wooruang.jnngserver;

import com.wooruang.jnng.Message;
import com.wooruang.jnng.ServerSocket;
import com.wooruang.jnng.Socket;
import com.wooruang.jnng.jni.NNG;
import com.wooruang.jnng.jni.NNGSocket;
import com.wooruang.jnng.jni.protocol.reqrep0.Rep;

public class JNNGServer {

    static {
        System.loadLibrary("jnng");
    }

    public static void main(String[] args) {
        System.out.println("Start nng server.");

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

        serverJNNG(url);
//        serverNative(url);
    }

    static void serverJNNG(String url) {
        ServerSocket server = new ServerSocket();

        int ret = server.openAsProtocol(Socket.Protocol.Rep0);
        if (ret != 0) {
            System.out.println(String.format("server.openAsProtocol : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        ret = server.listen(url);
        if (ret != 0) {
            System.out.println(String.format("server.listen : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        server.setOptionAsMs(NNG.NNGOptions.NNG_OPT_RECVTIMEO, 1010);
        int[] a = new int[]{0};
        server.getOptionAsMs(NNG.NNGOptions.NNG_OPT_RECVTIMEO, a);

        System.out.println("aaaaa " + a[0]);
        System.out.flush();

        for (;;) {
            Message msg = new Message(50);

            ret = server.recv(msg);
            if (ret != 0) {
                System.out.println(String.format("server.recv : %d %d %s", msg.getReceivedDataSize(), ret, NNG.nng_strerror(ret)));
            }
            System.out.println("Recv : " + msg.getString());

            Message sendMsg = new Message(50);
            sendMsg.setString("Obj-test");
            ret = server.send(sendMsg);
            if (ret != 0) {
                System.out.println(String.format("server.send : %d %s", ret, NNG.nng_strerror(ret)));
            }
        }

    }

    static void serverNative(String url) {

        NNGSocket socket = new NNGSocket();
        System.out.println(String.format("socket : %x", socket.id));

        int ret = Rep.nng_rep0_open(socket);
        if (ret != 0) {
            System.out.println(String.format("nng_rep0_open : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }
        System.out.println(String.format("nng_rep0_open socket : %x", socket.id));

        ret = NNG.nng_listen(socket, url, 0, 0);

        if (ret != 0) {
            System.out.println(String.format("nng_listen : %d %s", ret, NNG.nng_strerror(ret)));
            System.exit(1);
        }

        for (;;) {

            byte[] buffer = new byte[50];
            long[] buffer_len = new long[1];

            ret = NNG.nng_recv(socket, buffer, buffer_len,0);
            System.out.println(String.format("nng_recv : %d %d %s", buffer_len[0], ret, NNG.nng_strerror(ret)));
            for (int i = 0; i < buffer_len[0]; ++i) {
                System.out.println("byte " + buffer[i]);
            }
            System.out.println("Recv : " + new String(buffer));

            String test = "test";
            byte[] bb;
            try {
                bb = test.getBytes();
            } catch (Exception e) {
                System.out.println(e.toString());
                bb = new byte[5];
            }
            ret = NNG.nng_send(socket, bb, 1);

            System.out.println(String.format("nng_send : %d %s", ret, NNG.nng_strerror(ret)));
        }
    }
}
