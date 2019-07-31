package com.wooruang.jnng.jni;

public class NNG {

    public native static int nng_listen(NNGSocket socket, String addr, long nng_listener, int flags);

    public native static int nng_dial(NNGSocket socket, String addr, long nng_dialer, int flags);

    public native static int nng_send(NNGSocket socket,  byte[] buf, int flags);
    public native static int nng_recv(NNGSocket socket, byte[] buf, long[] len, int flags);

    public native static void nng_free(long buf, long sz);

    public native static int nng_close(NNGSocket socket);

    public native static String nng_strerror(int num);

}
