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

     public int setOption(NNG.NNGOptions option, byte[] buf, long size) {
          return NNG.nng_setopt(socket, option.getText(), buf, size);
     }
     public int setOptionAsBool(NNG.NNGOptions option, boolean val) {
          return NNG.nng_setopt_bool(socket, option.getText(), val);
     }
     public int setOptionAsInt(NNG.NNGOptions option, int val) {
          return NNG.nng_setopt_int(socket, option.getText(), val);
     }
     public int setOptionAsMs(NNG.NNGOptions option, int milli) {
          return NNG.nng_setopt_ms(socket, option.getText(), milli);
     }
     public int setOptionAsPtr(NNG.NNGOptions option, long ptr) {
          return NNG.nng_setopt_ptr(socket, option.getText(), ptr);
     }
     public int setOptionAsSize(NNG.NNGOptions option, long val) {
          return NNG.nng_setopt_size(socket, option.getText(), val);
     }
     public int setOptionAsString(NNG.NNGOptions option, String str) {
          return NNG.nng_setopt_string(socket, option.getText(), str);
     }
     public int setOptionAsUint64(NNG.NNGOptions option, long val) {
          return NNG.nng_setopt_unit64(socket, option.getText(), val);
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
