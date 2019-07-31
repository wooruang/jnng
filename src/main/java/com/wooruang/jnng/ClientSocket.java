package com.wooruang.jnng;

import com.wooruang.jnng.jni.NNG;

public class ClientSocket extends Socket {

    public int dial(String url) {
        return NNG.nng_dial(socket, url, 0, 0);
    }
}
