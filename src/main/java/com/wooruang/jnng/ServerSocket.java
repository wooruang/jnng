package com.wooruang.jnng;

import com.wooruang.jnng.jni.NNG;

public class ServerSocket extends Socket {

    public int listen(String url) {
        return NNG.nng_listen(socket, url, 0, 0);
    }


}
