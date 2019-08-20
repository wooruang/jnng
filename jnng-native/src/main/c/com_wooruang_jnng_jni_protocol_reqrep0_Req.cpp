#include <com_wooruang_jnng_jni_protocol_reqrep0_Req.h>

#include <nng/nng.h>
#include <nng/protocol/reqrep0/req.h>
#include <nng_util.h>

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Req_nng_1req0_1open
  (JNIEnv * env, jclass obj, jobject socket)
{
  nng_socket soc;
  int ret = nng_req0_open(&soc);
  setNngSocket(env, socket, soc);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_protocol_reqrep0_Req_nng_1req0_1open_1raw
  (JNIEnv * env, jclass obj, jobject socket)
{
  nng_socket soc;
  int ret = nng_req0_open_raw(&soc);
  setNngSocket(env, socket, soc);
  return (jint)ret;
}
