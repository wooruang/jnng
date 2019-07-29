package com.wooruang.jnng.jni.protocol.reqrep0;

public class Rep {
    public native static int nng_rep0_open(long nng_socket);
    public native static int nng_rep0_open_raw(long nng_socket);
}
