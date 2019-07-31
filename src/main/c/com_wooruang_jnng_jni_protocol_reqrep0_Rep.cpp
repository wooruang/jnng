#include <com_wooruang_jnng_jni_protocol_reqrep0_Rep.h>

#include <nng/nng.h>

#include <nng/protocol/reqrep0/rep.h>

#include <nng_util.h>

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Rep_nng_1rep0_1open
  (JNIEnv * env, jclass obj, jobject socket)
{
  nng_socket soc;
  int ret = nng_rep0_open(&soc);
  setNngSocket(env, socket, soc);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Rep_nng_1rep0_1open_1raw
  (JNIEnv * env, jclass obj, jobject socket)
{
  nng_socket soc;
  int ret = nng_rep0_open_raw(&soc);
  setNngSocket(env, socket, soc);
  return (jint)ret;
}