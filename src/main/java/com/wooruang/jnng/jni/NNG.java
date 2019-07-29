package com.wooruang.jnng.jni;

public class NNG {

    public native static long new_nng_socket();

    public native static int nng_listen(long nng_socket, String addr, long nng_listener, int flags);

    public native static int nng_dial(long nng_socket, String addr, long nng_dialer, int flags);

    public native static int nng_send(long nng_socket,  byte[] buf, int flags);
    public native static int nng_recv(long nng_socket, byte[] buf, long[] len, int flags);

    public native static void nng_free(long buf, long sz);

    public native static int nng_close(long nng_socket);


    public native static String nng_strerror(int num);

}
