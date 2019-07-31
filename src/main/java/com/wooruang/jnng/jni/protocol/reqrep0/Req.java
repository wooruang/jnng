package com.wooruang.jnng.jni.protocol.reqrep0;

import com.wooruang.jnng.jni.NNGSocket;

public class Req {
    public native static int nng_req0_open(NNGSocket socket);
    public native static int nng_req0_open_raw(NNGSocket socket);
}
