package com.wooruang.jnng;

import com.wooruang.jnng.jni.NNG;
import com.wooruang.jnng.jni.NNGSocket;
import com.wooruang.jnng.jni.protocol.reqrep0.Rep;
import com.wooruang.jnng.jni.protocol.reqrep0.Req;


public class Socket {

     public enum Protocol {
          Req0,
          Req0_RAW,
          Rep0,
          Rep0_RAW,
     }

     protected NNGSocket socket;

     Socket() {
          socket = new NNGSocket();
     }

     public int openAsProtocol(Protocol protocol) {
          int ret;
          switch (protocol) {
               case Req0:
                    ret = Req.nng_req0_open(socket);
                    break;
               case Req0_RAW:
                    ret = Req.nng_req0_open_raw(socket);
                    break;
               case Rep0:
                    ret = Rep.nng_rep0_open(socket);
                    break;
               case Rep0_RAW:
                    ret = Rep.nng_rep0_open_raw(socket);
                    break;
               default:
                    ret = -1;
          }
          return ret;
     }

     public int close() {
          return NNG.nng_close(socket);
     }

     public int send(Message msg) {
          return NNG.nng_send(socket, msg.getBytes(), 0);
     }

     public int recv(Message msg) {
          msg.setReceivedDataSize(0);
          byte[] data = msg.getBytes();
          long[] len = new long[1];
          int ret = NNG.nng_recv(socket, data, len, 0);
          msg.setReceivedDataSize(len[0]);
          return ret;
     }
}
