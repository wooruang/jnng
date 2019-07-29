#include <com_wooruang_jnng_jni_protocol_reqrep0_Rep.h>
#include <nng/nng.h>
#include <nng/protocol/reqrep0/rep.h>

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Rep_nng_1rep0_1open
  (JNIEnv * env, jclass obj, jlong socket)
{
  return (jint)nng_rep0_open((nng_socket *)socket);
}

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Rep_nng_1rep0_1open_1raw
  (JNIEnv * env, jclass obj, jlong socket)
{
  return (jint)nng_rep0_open_raw((nng_socket *)socket);
}