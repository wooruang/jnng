package com.wooruang.jnng;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class JNNG {

    public static final String LIBNAME = "jnng";

    public static String getFullLibName(String name) {
        return System.mapLibraryName(name);
    }

    public static String getLibSuffix(String libname) {
        return libname.substring(libname.lastIndexOf('.'));
    }

    public static void initialize() {
        try {

            // Get input stream from jar resource
            //InputStream inputStream = new JNNG().getClass().getResource("/libs/" + getFullLibName(LIBNAME)).openStream();
            URL url = JNNG.class.getResource("/libs/" + getFullLibName(LIBNAME));
            InputStream inputStream = url.openStream();

            // Copy resource to filesystem in a temp folder with a unique name
            File temporaryDll = File.createTempFile(LIBNAME, getLibSuffix(getFullLibName(LIBNAME)));
            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
            byte[] array = new byte[8192];
            int read = 0;
            while ((read = inputStream.read(array)) > 0) {
                outputStream.write(array, 0, read);
            }

            outputStream.close();

            // Delete on exit the dll
            temporaryDll.deleteOnExit();

            // Finally, load the dll
            System.load(temporaryDll.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//    nng_aio_abort(3)
//    nng_aio_alloc(3)
//    nng_aio_begin(3)
//    nng_aio_cancel(3)
//    nng_aio_count(3)
//    nng_aio_defer(3)
//    nng_aio_finish(3)
//    nng_aio_free(3)
//    nng_aio_get_input(3)
//    nng_aio_get_msg(3)
//    nng_aio_get_output(3)
//    nng_aio_result(3)
//    nng_aio_set_input(3)
//    nng_aio_set_iov(3)
//    nng_aio_set_msg(3)
//    nng_aio_set_output(3)
//    nng_aio_set_timeout(3)
//    nng_aio_stop(3)
//    nng_aio_wait(3)
//
//    nng_alloc(3)
//
//    nng_bus_open(3)
//
//    nng_close(3)
//
//    nng_ctx_close(3)
//    nng_ctx_getopt(3)
//    nng_ctx_id(3)
//    nng_ctx_open(3)
//    nng_ctx_recv(3)
//    nng_ctx_send(3)
//    nng_ctx_setopt(3)
//
//    nng_device(3)
//
//    nng_dial(3)
//    nng_dialer_close(3)
//    nng_dialer_create(3)
//    nng_dialer_getopt(3)
//    nng_dialer_id(3)
//    nng_dialer_setopt(3)
//    nng_dialer_start(3)
//
//    nng_free(3)
//
//    nng_getopt(3)
//
//    nng_inproc_register(3)
//
//    nng_ipc_register(3)
//
//    nng_listen(3)
//    nng_listener_close(3)
//    nng_listener_create(3)
//    nng_listener_getopt(3)
//    nng_listener_id(3)
//    nng_listener_setopt(3)
//    nng_listener_start(3)
//
//    nng_msg_alloc(3)
//    nng_msg_append(3)
//    nng_msg_body(3)
//    nng_msg_chop(3)
//    nng_msg_clear(3)
//    nng_msg_dup(3)
//    nng_msg_free(3)
//    nng_msg_get_pipe(3)
//    nng_msg_header(3)
//    nng_msg_header_append(3)
//    nng_msg_header_chop(3)
//    nng_msg_header_clear(3)
//    nng_msg_header_insert(3)
//    nng_msg_header_len(3)
//    nng_msg_header_trim(3)
//    nng_msg_insert(3)
//    nng_msg_len(3)
//    nng_msg_realloc(3)
//    nng_msg_set_pipe(3)
//    nng_msg_trim(3)
//
//    nng_pair_open(3)
//
//    nng_pipe_close(3)
//    nng_pipe_dialer(3)
//    nng_pipe_getopt(3)
//    nng_pipe_id(3)
//    nng_pipe_listener(3)
//    nng_pipe_notify(3)
//    nng_pipe_socket(3)
//
//    nng_pub_open(3)
//
//    nng_pull_open(3)
//
//    nng_push_open(3)
//
//    nng_recv(3)
//    nng_recv_aio(3)
//    nng_recvmsg(3)
//
//    nng_rep_open(3)
//    nng_req_open(3)
//
//    nng_respondent_open(3)
//
//    nng_send(3)
//    nng_send_aio(3)
//    nng_sendmsg(3)
//
//    nng_setopt(3)
//
//    nng_sleep_aio(3)
//
//    nng_socket_id(3)
//
//    nng_stat_child(3)
//    nng_stat_desc(3)
//    nng_stat_name(3)
//    nng_stat_next(3)
//    nng_stat_string(3)
//    nng_stat_timestamp(3)
//    nng_stat_type(3)
//    nng_stat_unit(3)
//    nng_stat_value(3)
//    nng_stats_free(3)
//    nng_stats_get(3)
//
//    nng_strdup(3)
//
//    nng_strerror(3)
//
//    nng_strfree(3)
//
//    nng_sub_open(3)
//
//    nng_surveyor_open(3)
//
//    nng_tcp_register(3)
//
//    nng_tls_register(3)
//
//    nng_url_clone(3)
//    nng_url_free(3)
//    nng_url_parse(3)
//
//    nng_version(3)
//    nng_ws_register(3)
//    nng_wss_register(3)
//    nng_zt_register(3)