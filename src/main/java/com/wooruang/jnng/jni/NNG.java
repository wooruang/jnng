package com.wooruang.jnng.jni;

public class NNG {

    public native static int nng_listen(NNGSocket socket, String addr, long nng_listener, int flags);

    public native static int nng_dial(NNGSocket socket, String addr, long nng_dialer, int flags);

    public native static int nng_send(NNGSocket socket,  byte[] buf, int flags);
    public native static int nng_recv(NNGSocket socket, byte[] buf, long[] len, int flags);

    public native static void nng_free(long buf, long sz);

    public native static int nng_close(NNGSocket socket);

    // NNG socket options.

    public enum NNGOptions {
        NNG_OPT_SOCKNAME("socket-name"),
        NNG_OPT_RAW("raw"),
        NNG_OPT_PROTO("protocol"),
        NNG_OPT_PROTONAME("protocol-name"),
        NNG_OPT_PEER("peer"),
        NNG_OPT_PEERNAME("peer-name"),
        NNG_OPT_RECVBUF("recv-buffer"),
        NNG_OPT_SENDBUF("send-buffer"),
        NNG_OPT_RECVFD("recv-fd"),
        NNG_OPT_SENDFD("send-fd"),
        NNG_OPT_RECVTIMEO("recv-timeout"),
        NNG_OPT_SENDTIMEO("send-timeout"),
        NNG_OPT_LOCADDR("local-address"),
        NNG_OPT_REMADDR("remote-address"),
        NNG_OPT_URL("url"),
        NNG_OPT_MAXTTL("ttl-max"),
        NNG_OPT_RECVMAXSZ("recv-size-max"),
        NNG_OPT_RECONNMINT("reconnect-time-min"),
        NNG_OPT_RECONNMAXT("reconnect-time-max");

        private String text;

        NNGOptions(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static NNGOptions fromString(String text) {
            for (NNGOptions n : NNGOptions.values()) {
                if (n.text.equalsIgnoreCase(text)) {
                    return n;
                }
            }
            return null;
        }
    }

    public native static int nng_setopt(NNGSocket socket, String opt, byte[] buf, long sz);
    public native static int nng_setopt_bool(NNGSocket socket, String opt, boolean val);
    public native static int nng_setopt_int(NNGSocket socket, String opt, int val);

    /**
     *  This function is used to configure time durations (such as timeouts) using type nng_duration.
     *  The duration dur is an integer number of milliseconds.
     *
     *
     * @param socket nng socket.
     * @param opt option name <a href="https://nanomsg.github.io/nng/man/v1.1.0/nng_options.5">nng_options</a>.
     * @param val nng_duraion
     * [nng_duration]
     * #include <nng/nng.h>
     *
     * typedef int32_t nng_duration;
     *
     * #define NNG_DURATION_INFINITE (-1)
     * #define NNG_DURATION_DEFAULT  (-2)
     * #define NNG_DURATION_ZERO     (0)
     *
     * @return These functions return 0 on success, and non-zero otherwise.
     */
    public native static int nng_setopt_ms(NNGSocket socket, String opt, int val);
    public native static int nng_setopt_ptr(NNGSocket socket, String opt, long ptr);
    public native static int nng_setopt_size(NNGSocket socket, String opt, long size);
    public native static int nng_setopt_string(NNGSocket socket, String opt, String str);
    public native static int nng_setopt_unit64(NNGSocket socket, String opt, long u64);

    public native static long nng_getopt(NNGSocket socket, String opt, byte[] buf, long[] len);
    public native static int nng_getopt_int(NNGSocket socket, String opt, int[] val);
    public native static int nng_getopt_ms(NNGSocket socket, String opt, int[] val);
    public native static int nng_getopt_size(NNGSocket socket, String opt, long[] val);

    public native static String nng_strerror(int num);

}
