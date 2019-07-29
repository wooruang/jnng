#include <com_wooruang_jnng_jni_NNG.h>
#include <nng/nng.h>

// #include <memory>
#include<algorithm>

jlong Java_com_wooruang_jnng_jni_NNG_new_1nng_1socket(JNIEnv * env, jclass obj)
{
  return (jlong)new nng_socket();
}

JNIEXPORT jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1listen
  (JNIEnv * env, jclass obj, jlong socket, jstring addr, jlong listener, jint flags)
{
  const char * addr_str = env->GetStringUTFChars(addr, nullptr);

  nng_socket & soc = *(nng_socket *)socket;
  int ret = nng_listen(soc, addr_str, (nng_listener *)listener, flags);

  env->ReleaseStringUTFChars(addr, addr_str);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1dial
  (JNIEnv * env, jclass obj, jlong socket, jstring addr, jlong dialer, jint flags)
{
  const char * addr_str = env->GetStringUTFChars(addr, nullptr);
  nng_socket & soc = *(nng_socket *)socket;
  int ret = nng_dial(soc, addr_str, (nng_dialer *)dialer, flags);
  env->ReleaseStringUTFChars(addr, addr_str);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1send
  (JNIEnv * env, jclass obj, jlong socket, jbyteArray _buf, jint flags)
{
  jbyte * buf = env->GetByteArrayElements(_buf, nullptr);
  size_t buf_size = (size_t)env->GetArrayLength(_buf);

  nng_socket & soc = *(nng_socket *)socket;
  printf("Java_com_wooruang_jnng_jni_NNG_nng_1send socket: %p buf_size: %zu, flags1: %d\n", &soc, buf_size, flags);
  int ret = nng_send(soc, buf, buf_size, 0);

  env->ReleaseByteArrayElements(_buf, buf, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1recv
  (JNIEnv * env, jclass obj, jlong socket, jbyteArray _buf, jlongArray _len, jint flags)
{
  jbyte * buf = env->GetByteArrayElements(_buf, nullptr);
  size_t buf_size = (size_t)env->GetArrayLength(_buf);

  jlong * len = env->GetLongArrayElements(_len, nullptr);
  size_t len_size = (size_t)env->GetArrayLength(_len);

  if (len_size <= 0) {
    return nng_errno_enum::NNG_ENOARG;
  } 
  
  nng_socket & soc = *(nng_socket *)socket;

  size_t size_t_len = 0;
  int ret = nng_recv(soc, buf, &size_t_len, flags);

  len[0] = (jlong)size_t_len;

  env->ReleaseByteArrayElements(_buf, buf, 0);
  env->ReleaseLongArrayElements(_len, len, 0);
  return (jint)ret;
}

jint JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1close
  (JNIEnv * env, jclass obj, jlong socket)
{
  nng_socket & soc = *(nng_socket *)socket;
  return (jint)nng_close(soc);
}

jstring JNICALL Java_com_wooruang_jnng_jni_NNG_nng_1strerror(JNIEnv * env, jclass obj, jint num)
{
  const char * buf = nng_strerror(num);
  return env->NewStringUTF(buf);
}
