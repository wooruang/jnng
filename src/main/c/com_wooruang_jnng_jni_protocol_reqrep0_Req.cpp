#include <com_wooruang_jnng_jni_protocol_reqrep0_Req.h>

#include <nng/nng.h>
#include <nng/protocol/reqrep0/req.h>

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Req_nng_1req0_1open
  (JNIEnv * env, jclass obj, jlong socket)
{
  return (jint)nng_req0_open((nng_socket *)socket);
}

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Req_nng_1req0_1open_1raw
  (JNIEnv * env, jclass obj, jlong socket)
{
  return (jint)nng_req0_open_raw((nng_socket *)socket);
}
